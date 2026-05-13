package pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kfcracing.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi komponen
        val inputEmail: EditText = findViewById(R.id.inputEmail)
        val inputPassword: EditText = findViewById(R.id.inputPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            // Mengambil value dari inputEmail dan inputPassword
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            // Menampilkan di Logcat
            Log.e("Klik btnLogin", "Tombol Login berhasil di tekan")
            Log.e("Input Email", "Email: $email")
            Log.e("Input Password", "Password: $password")

            // Menampilkan Toast
            Toast.makeText(this, "Login dengan Email: $email", Toast.LENGTH_SHORT).show()
        }
    }
}
