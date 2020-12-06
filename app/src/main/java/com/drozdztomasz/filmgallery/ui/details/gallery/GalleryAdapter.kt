package com.drozdztomasz.filmgallery.ui.details.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drozdztomasz.filmgallery.databinding.GalleryEntryBinding

class GalleryAdapter(private val photos: List<Int>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: GalleryEntryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.apply {
                galleryIv.setImageDrawable(context.getDrawable(item))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryEntryBinding.inflate(inflater)
        val context = parent.context
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}
