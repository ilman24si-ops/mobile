package com.example.kfcracing.pembangunan

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kfcracing.R
import com.example.kfcracing.data.entity.PembangunanEntity
import com.example.kfcracing.databinding.ItemPembangunanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.NumberFormat
import java.util.Locale

class PembangunanAdapter(
    private val list: List<PembangunanEntity>,
    private val fragment: PembangunanFragment
) : RecyclerView.Adapter<PembangunanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPembangunanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPembangunanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvNamaProyek.text = item.namaProyek
        holder.binding.tvLokasi.text = item.lokasi
        
        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        holder.binding.tvAnggaran.text = formatter.format(item.anggaran)
        
        holder.binding.tvStatus.text = item.status
        
        if (!item.imagePath.isNullOrEmpty()) {
            try {
                holder.binding.ivProyek.setImageURI(Uri.parse(item.imagePath))
            } catch (e: Exception) {
                holder.binding.ivProyek.setImageResource(R.drawable.village_bg)
            }
        } else {
            holder.binding.ivProyek.setImageResource(R.drawable.village_bg)
        }
        
        val context = holder.itemView.context
        val statusColor = when {
            item.status.contains("Selesai", true) -> R.color.status_completed
            item.status.contains("Jalan", true) || item.status.contains("Proses", true) -> R.color.status_ongoing
            else -> R.color.status_planned
        }
        holder.binding.tvStatus.backgroundTintList = ContextCompat.getColorStateList(context, statusColor)

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle("Hapus Proyek")
                .setMessage("Yakin ingin menghapus data pembangunan ini?")
                .setPositiveButton("Hapus") { _, _ ->
                    fragment.deletePembangunan(item)
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        holder.binding.btnReminder.setOnClickListener {
            fragment.showReminderDialog(item)
        }
    }

    override fun getItemCount(): Int = list.size
}
