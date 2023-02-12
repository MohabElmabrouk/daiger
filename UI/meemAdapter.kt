package com.example.taskpost.UI

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.taskpost.databinding.MeemItemBinding
import com.example.taskpost.model.Meme

class meemAdapter  : ListAdapter<Meme, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meme>() {

            override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem.title == newItem.title || oldItem.subreddit == newItem.subreddit ||
                        oldItem.postLink == newItem.postLink
            }

        }
    }
    private lateinit var binding: MeemItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = MeemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }


    inner class ViewHolder(val itemBinding: MeemItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Meme) {
            Log.d("adapter", "bind $adapterPosition")
            itemBinding.apply {
               tvTitle.text = item.title
                tvContent.text = item.subreddit
                ivPicture.load(item.url)
            }
        }

    }
}