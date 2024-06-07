package com.ssccgl.pinnacle.testportal_1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssccgl.pinnacle.testportal_1.R
import com.ssccgl.pinnacle.testportal_1.databinding.ItemIconBinding
import com.ssccgl.pinnacle.testportal_1.model.IconItem

class IconAdapter(private val items: List<IconItem>) :
    RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    class IconViewHolder(val binding: ItemIconBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val binding: ItemIconBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_icon, parent, false
        )
        return IconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}