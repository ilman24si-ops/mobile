package com.example.kfcracing.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kfcracing.data.AppDatabase
import com.example.kfcracing.data.entity.UserEntity
import com.example.kfcracing.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (fullName.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                lifecycleScope.launch {
                    val existingUser = db.userDao().getUserByUsername(username)
                    if (existingUser == null) {
                        val newUser = UserEntity(
                            username = username,
                            password = password,
                            fullName = fullName
                        )
                        db.userDao().insert(newUser)
                        Toast.makeText(this@RegisterActivity, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Username sudah digunakan!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvBackToLogin.setOnClickListener {
            finish()
        }
    }
}
