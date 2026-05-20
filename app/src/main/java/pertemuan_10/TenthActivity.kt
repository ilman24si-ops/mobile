package pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kfcracing.R
import com.example.kfcracing.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Step 1: Initialize Binding
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 2: Setup Toolbar with Back Button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Step 3: Initialize Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // Step 4: Set adapter to ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // Step 5: Connect TabLayout & ViewPager2 and Customize (Icons & Badges)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    // Badge without number
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    // Badge with number
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    tab.text = "Tab C"
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_gallery)
                }
            }
        }.attach()
    }
}
