package com.drozdztomasz.filmgallery.ui.list.film_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drozdztomasz.filmgallery.R
import com.drozdztomasz.filmgallery.data.Film
import com.drozdztomasz.filmgallery.databinding.FilmEntryBinding
import com.drozdztomasz.filmgallery.ui.list.ListFragmentDirections

class FilmAdapter(private val films: LiveData<List<Film>>) :
    ListAdapter<Film, FilmAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: FilmEntryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.apply {
                nameTv.text = context.getString(film.title)
                setButton(film)

                genreTv.text = context.getString(film.genre.stringId)
                posterIv.setImageDrawable(context.getDrawable(film.poster))

                favouriteBtn.setOnClickListener {
                    film.favourite = !film.favourite
                    setButton(film)
                }
            }

            this.itemView.setOnClickListener { v ->
                val action = ListFragmentDirections.actionListFragmentToDetailsActivity(film)
                v.findNavController().navigate(action)
            }
        }

        private fun setButton(film: Film) {
            val buttonImg =
                if (film.favourite) R.drawable.favourites_on else R.drawable.favourites_off
            binding.favouriteBtn.setImageDrawable(
                ContextCompat.getDrawable(context, buttonImg)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmEntryBinding.inflate(inflater)
        val context = parent.context
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films.value!![position])
    }
}

class DiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
