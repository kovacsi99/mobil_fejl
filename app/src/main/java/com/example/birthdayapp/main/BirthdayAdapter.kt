package com.example.birthdayapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.birthdayapp.database.Birthday
import com.example.birthdayapp.databinding.BirthdayItemBinding

class BirthdayAdapter(private val onClickListener: OnItemClickListener): ListAdapter<Birthday, BirthdayAdapter.ViewHolder>(BirthdayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(getItem(position))
        }
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: BirthdayItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Birthday) {
            binding.birthday = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BirthdayItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BirthdayDiffCallback : DiffUtil.ItemCallback<Birthday>() {
    override fun areItemsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
        return oldItem == newItem
    }
}