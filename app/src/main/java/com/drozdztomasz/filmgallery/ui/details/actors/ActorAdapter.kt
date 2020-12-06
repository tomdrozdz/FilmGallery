package com.drozdztomasz.filmgallery.ui.details.actors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drozdztomasz.filmgallery.data.Actor
import com.drozdztomasz.filmgallery.databinding.ActorEntryBinding

class ActorAdapter(private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ActorEntryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actor) {
            binding.apply {
                actorPhotoIv.setImageDrawable(context.getDrawable(item.photo))
                actorNameTv.text = context.getString(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActorEntryBinding.inflate(inflater)
        val context = parent.context
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}
