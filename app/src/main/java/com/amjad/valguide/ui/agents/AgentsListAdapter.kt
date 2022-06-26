package com.amjad.valguide.ui.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.valguide.data.remote.response.AgentList
import com.amjad.valguide.databinding.AgentItemBinding
import com.bumptech.glide.Glide

class AgentsListAdapter :
    ListAdapter<AgentList, AgentsListAdapter.ListViewHolder>(DiffCallback()) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AgentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: AgentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AgentList) {
            Glide.with(itemView)
                .load(data.displayIcon)
                .into(binding.agentImg)
            Glide.with(itemView)
                .load(data.background)
                .into(binding.bgImg)
            binding.roleTv.text = data.role.displayName
            binding.agentTv.text = data.displayName
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AgentList>() {
        override fun areItemsTheSame(oldItem: AgentList, newItem: AgentList): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AgentList, newItem: AgentList): Boolean {
            return oldItem.uuid == newItem.uuid
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: AgentList)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}