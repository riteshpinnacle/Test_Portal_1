package com.ssccgl.pinnacle.testportal_1.view

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.ssccgl.pinnacle.testportal_1.R
import com.ssccgl.pinnacle.testportal_1.viewmodel.HomeViewModel

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssccgl.pinnacle.testportal_1.view.adapter.CarouselAdapter
import com.ssccgl.pinnacle.testportal_1.view.adapter.IconAdapter

class HomePageActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var handler: Handler
    private lateinit var iconRecyclerView: RecyclerView
    private val scrollInterval = 3000L // Auto scroll interval in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setUpViews()
        setUpCarousel()
        setUpIcons()
        setUpAutoScroll()
    }

    fun setUpViews(){
        setUpDrawerLayout()
    }

    fun setUpDrawerLayout(){
        val appBar = findViewById<Toolbar>(R.id.appBar)
        val mainDrawer = findViewById<DrawerLayout>(R.id.mainDrawer)
        setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

private fun setUpCarousel() {
    viewPager = findViewById<ViewPager2>(R.id.viewPager)
    tabLayout = findViewById<TabLayout>(R.id.tabLayout)
    homeViewModel.carouselItems.observe(this, Observer { items ->
//        viewPager.adapter = CarouselAdapter(items)
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            // This can be used to configure the tab, if needed
//        }.attach()
        if (items != null && items.isNotEmpty()) {
            Log.d("HomePageActivity", "Carousel items loaded: ${items.size}")
            viewPager.adapter = CarouselAdapter(items)
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
        } else {
            Log.d("HomePageActivity", "No carousel items to display.")
        }
    })
}

    private fun setUpIcons() {
        iconRecyclerView = findViewById(R.id.iconRecyclerView)
        iconRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.iconItems.observe(this, Observer { items ->
            if (items != null && items.isNotEmpty()) {
                iconRecyclerView.adapter = IconAdapter(items)
            }
        })
    }

    private fun setUpAutoScroll() {
        handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                viewPager.adapter?.let { adapter ->
                    val itemCount = adapter.itemCount
                    Log.d("HomePageActivity", "Auto-scroll running. Item count: $itemCount")
                    if (itemCount > 0) {
                        val nextItem = (viewPager.currentItem + 1) % itemCount
                        Log.d("HomePageActivity", "Moving to next item: $nextItem")
                        viewPager.setCurrentItem(nextItem, true) // Smooth scroll to the next item
                        handler.postDelayed(this, scrollInterval)
                    } else {
                        Log.d("HomePageActivity", "Item count is zero, skipping auto-scroll.")
                    }
                }
            }
        }
        handler.postDelayed(runnable, scrollInterval)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Stop the handler to prevent memory leaks
        Log.d("HomePageActivity", "Handler callbacks removed.")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}