package com.ssccgl.pinnacle.testportal_1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssccgl.pinnacle.testportal_1.R
import com.ssccgl.pinnacle.testportal_1.model.CarouselItem

import androidx.databinding.DataBindingUtil
import com.ssccgl.pinnacle.testportal_1.databinding.ItemCarouselBinding

class CarouselAdapter(private val items: List<CarouselItem>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding: ItemCarouselBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_carousel, parent, false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = items[position]
        holder.binding.imageView.setImageResource(item.imageResId)
        holder.binding.textView.text = item.text
    }

    override fun getItemCount() = items.size
}