package com.scan.e_commerce.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scan.e_commerce.databinding.ImageItemBinding

class ImageAdapter(
    private val imageList: List<String>,
    val onImageClicked: (item: String) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.image = imageUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onImageClicked(imageList[position])
        }
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}