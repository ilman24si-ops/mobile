package com.example.kfcracing.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kfcracing.MainActivity
import com.example.kfcracing.data.AppDatabase
import com.example.kfcracing.data.entity.UserEntity
import com.example.kfcracing.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)
        
        lifecycleScope.launch {
            if (db.userDao().getUserCount() == 0) {
                db.userDao().insert(UserEntity(username = "user", password = "user", fullName = "Default User"))
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                val user = db.userDao().getUserByUsername(username)
                if (user != null && user.password == password) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
