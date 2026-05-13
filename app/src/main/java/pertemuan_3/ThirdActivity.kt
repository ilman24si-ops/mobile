package pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kfcracing.R
import com.example.kfcracing.databinding.ActivityThirdBinding
import com.google.android.material.textfield.TextInputLayout

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var nameLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize views
        nameLayout = binding.nameLayout

        // Setup window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup submit button
        setupSubmitButton()
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val name = binding.inputNama.text.toString().trim()

            // Validation
            if (validateInput(name)) {
                // Log the action
                android.util.Log.d("ThirdActivity", "Submit clicked. Name: $name")

                // Show success message
                Toast.makeText(this, "Welcome, $name!", Toast.LENGTH_SHORT).show()

                // Navigate to result activity with data
                val intent = Intent(this, ThirdResultActivity::class.java).apply {
                    putExtra("USER_NAME", name)
                }
                startActivity(intent)
            }
        }
    }

    private fun validateInput(name: String): Boolean {
        return when {
            name.isEmpty() -> {
                nameLayout.error = "Please enter your name"
                false
            }
            name.length < 3 -> {
                nameLayout.error = "Name must be at least 3 characters"
                false
            }
            else -> {
                nameLayout.error = null
                true
            }
        }
    }
}
