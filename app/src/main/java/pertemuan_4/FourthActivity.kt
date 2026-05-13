package pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kfcracing.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val tvTanggal = findViewById<TextView>(R.id.tvTanggal)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnSnackbar = findViewById<Button>(R.id.btnShowSnackbar)
        val btnDialog = findViewById<Button>(R.id.btnShowAlertDialog)
        val btn1 = findViewById<Button>(R.id.btn1)

        // Kalender
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val tanggal = "$dayOfMonth/${month + 1}/$year"
            tvTanggal.text = "Tanggal dipilih: $tanggal"
        }

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Ambil data intent
        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)

        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")

        // SNACKBAR
        btnSnackbar.setOnClickListener {
            Snackbar.make(it, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        // ALERT DIALOG
        btnDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        // BUTTON DI DALAM CARD
        btn1.setOnClickListener {
            Log.e("CARD", "Tombol di dalam card diklik")
        }
    }
}
