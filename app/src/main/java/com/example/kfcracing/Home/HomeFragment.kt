package com.example.kfcracing.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kfcracing.Home.pertemuan_13.ThirteenthActivity
import com.example.kfcracing.Home.pertemuan_9.NinthActivity
import com.example.kfcracing.Home.photo.PhotoAdapter
import com.example.kfcracing.data.api.CatFactApiClient
import com.example.kfcracing.data.api.PhotoApiClient
import com.example.kfcracing.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import pertemuan_10.TenthActivity

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

        binding.btnPertemuan9.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        loadCatFact()
        loadPhoto()
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}