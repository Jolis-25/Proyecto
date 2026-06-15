package com.example.brickmilio1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.brickmilio1.ui.LoginActivity
import com.example.brickmilio1.ui.fragments.ArmadosFragment
import com.example.brickmilio1.ui.fragments.PorArmarFragment
import com.example.brickmilio1.ui.fragments.WishlistFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("brickmilio_prefs", MODE_PRIVATE)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ArmadosFragment()
                    1 -> PorArmarFragment()
                    else -> WishlistFragment()
                }
            }
        }

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "✅ Armados"
                1 -> "🔨 Por armar"
                else -> "⭐ Wishlist"
            }
        }.attach()
    }


}
