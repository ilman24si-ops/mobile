package com.example.kfcracing.Home.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kfcracing.R
import com.example.kfcracing.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab Capture"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_capture)
                }
                1 -> {
                    tab.text = "Tab QR Code"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_qrcode)
                }
                2 -> {
                    tab.text = "Tab Scan"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_scan)
                }
            }
        }.attach()
    }
}
