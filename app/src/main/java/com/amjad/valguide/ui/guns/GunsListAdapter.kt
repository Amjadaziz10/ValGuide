package com.amjad.valguide.ui.guns

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.valguide.data.remote.response.GunList
import com.amjad.valguide.databinding.GunItemBinding
import com.bumptech.glide.Glide

class GunsListAdapter :
    ListAdapter<GunList, GunsListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = GunItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: GunItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GunList) {
            Glide.with(itemView)
                .load(data.displayIcon)
                .into(binding.bgImg)
            binding.gunTv.text = data.displayName
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: GunList)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GunList>() {
            override fun areItemsTheSame(oldItem: GunList, newItem: GunList): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: GunList, newItem: GunList): Boolean {
                return oldItem == newItem
            }
        }
    }

}
