package com.amjad.valguide.ui.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.valguide.data.remote.response.GunList
import com.amjad.valguide.data.remote.response.MapList
import com.amjad.valguide.databinding.GunItemBinding
import com.amjad.valguide.databinding.MapItemBinding
import com.bumptech.glide.Glide

class MapsListAdapter :
    ListAdapter<MapList, MapsListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = MapItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: MapItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MapList) {
            Glide.with(itemView)
                .load(data.listViewIcon)
                .into(binding.bgImg)
            binding.mapTv.text = data.displayName
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: MapList)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MapList>() {
            override fun areItemsTheSame(oldItem: MapList, newItem: MapList): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: MapList, newItem: MapList): Boolean {
                return oldItem == newItem
            }
        }
    }

}
