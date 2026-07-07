package com.example.kfcracing.pembangunan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kfcracing.MainActivity
import com.example.kfcracing.data.AppDatabase
import com.example.kfcracing.data.entity.PembangunanEntity
import com.example.kfcracing.databinding.FragmentPembangunanBinding
import com.example.kfcracing.utils.ReminderHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class PembangunanFragment : Fragment() {
    private var _binding: FragmentPembangunanBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: PembangunanAdapter
    private val listPembangunan = mutableListOf<PembangunanEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPembangunanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = PembangunanAdapter(listPembangunan, this)

        binding.rvPembangunan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPembangunan.adapter = adapter

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(requireContext(), PembangunanFormActivity::class.java))
        }

        fetchData()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val data = db.pembangunanDao().getAll()
            listPembangunan.clear()
            listPembangunan.addAll(data.reversed())
            adapter.notifyDataSetChanged()
        }
    }

    fun deletePembangunan(item: PembangunanEntity) {
        lifecycleScope.launch {
            db.pembangunanDao().delete(item)
            fetchData()
        }
    }

    fun showReminderDialog(item: PembangunanEntity) {
        val input = EditText(requireContext())
        input.hint = "Menit (contoh: 5)"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Set Pengingat Survei")
            .setMessage("Berapa menit lagi Anda ingin diingatkan untuk survei proyek '${item.namaProyek}'?")
            .setView(input)
            .setPositiveButton("Set Alaram") { _, _ ->
                val minutesStr = input.text.toString()
                if (minutesStr.isNotEmpty()) {
                    val minutes = minutesStr.toInt()
                    ReminderHelper.setReminderInMinutes(
                        requireContext(),
                        minutes,
                        "Jadwal Survei Proyek",
                        "Waktunya memeriksa proyek: ${item.namaProyek} di ${item.lokasi}",
                        MainActivity::class.java
                    )
                    Toast.makeText(requireContext(), "Pengingat diset untuk $minutes menit lagi", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
