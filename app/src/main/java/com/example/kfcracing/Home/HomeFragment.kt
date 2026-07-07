package com.example.kfcracing.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kfcracing.data.AppDatabase
import com.example.kfcracing.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStats()
    }

    private fun updateStats() {
        val db = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            val allProjects = db.pembangunanDao().getAll()
            binding.tvTotalProjects.text = allProjects.size.toString()
            binding.tvCompletedProjects.text = allProjects.count { it.status.contains("Selesai", ignoreCase = true) }.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
