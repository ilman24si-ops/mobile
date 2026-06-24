package pertemuan_3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kfcracing.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil data yang dikirim dari Intent (baik dari Activity asal maupun dari Notifikasi)
        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")
        val userName = intent.getStringExtra("USER_NAME")

        if (userName != null) {
            binding.textView.text = "Halo, $userName!"
        } else if (title != null) {
            binding.textView.text = title
        }

        if (message != null) {
            binding.textView2.text = message
        }
    }
}
