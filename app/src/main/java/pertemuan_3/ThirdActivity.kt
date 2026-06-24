package pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kfcracing.R
import com.example.kfcracing.databinding.ActivityThirdBinding
import com.example.kfcracing.utils.PermissionHelper
import com.example.kfcracing.utils.ReminderHelper
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var nameLayout: TextInputLayout

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize views
        nameLayout = binding.nameLayout

        // Request notification permission if required
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

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
                val calendar = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
                }

                ReminderHelper.setReminder(
                    context = this,
                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                    minute = calendar.get(Calendar.MINUTE),
                    title = "Reminder 1 Menit",
                    message = "Halo $name, reminder ini muncul 1 menit setelah tombol ditekan",
                    targetActivity = ThirdResultActivity::class.java
                )
                Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()
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
