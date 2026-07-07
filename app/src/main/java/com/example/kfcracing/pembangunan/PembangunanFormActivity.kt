package com.example.kfcracing.pembangunan

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kfcracing.data.AppDatabase
import com.example.kfcracing.data.entity.PembangunanEntity
import com.example.kfcracing.databinding.ActivityPembangunanFormBinding
import kotlinx.coroutines.launch

class PembangunanFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembangunanFormBinding
    private lateinit var db: AppDatabase
    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            binding.ivPreview.setImageURI(selectedImageUri)
            binding.lytPlaceholder.visibility = android.view.View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembangunanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            pickImageLauncher.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            val nama = binding.etNamaProyek.text.toString()
            val lokasi = binding.etLokasi.text.toString()
            val anggaranStr = binding.etAnggaran.text.toString()
            val status = binding.etStatus.text.toString()

            if (nama.isNotBlank() && lokasi.isNotBlank() && anggaranStr.isNotBlank() && status.isNotBlank()) {
                lifecycleScope.launch {
                    val entity = PembangunanEntity(
                        namaProyek = nama,
                        lokasi = lokasi,
                        anggaran = anggaranStr.toDoubleOrNull() ?: 0.0,
                        status = status,
                        imagePath = selectedImageUri?.toString(),
                        tanggalMulai = System.currentTimeMillis()
                    )
                    db.pembangunanDao().insert(entity)
                    finish()
                }
            } else {
                Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
