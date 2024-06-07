package com.ssccgl.pinnacle.testportal_1.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssccgl.pinnacle.testportal_1.R
import com.ssccgl.pinnacle.testportal_1.model.CarouselItem
import com.ssccgl.pinnacle.testportal_1.model.IconItem

class HomeViewModel : ViewModel() {

    private val _carouselItems = MutableLiveData<List<CarouselItem>>()
    val carouselItems: LiveData<List<CarouselItem>> get() = _carouselItems

    private val _iconItems = MutableLiveData<List<IconItem>>()
    val iconItems: LiveData<List<IconItem>> get() = _iconItems

    init {
        loadCarouselItems()
        loadIconItems()
    }

    private fun loadCarouselItems() {
        // Load your data here
        _carouselItems.value = listOf(
            CarouselItem(R.drawable.image1, "Item 1"),
            CarouselItem(R.drawable.image2, "Item 2"),
            CarouselItem(R.drawable.image3, "Item 3")
        )
    }

    private fun loadIconItems(){
        _iconItems.value = listOf(
            IconItem(R.drawable.ic_home,"Home"),
            IconItem(R.drawable.ic_tests, "Tests"),
            IconItem(R.drawable.ic_my_tests, "My Tests")
            // Add more icons as needed
        )
    }
}