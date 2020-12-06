package com.drozdztomasz.filmgallery.ui.details.gallery

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.drozdztomasz.filmgallery.databinding.GalleryFragmentBinding
import com.drozdztomasz.filmgallery.ui.details.DetailsViewModel

class GalleryFragment : Fragment() {
    private lateinit var binding: GalleryFragmentBinding
    private val viewModel: DetailsViewModel by activityViewModels()

    companion object {
        private const val LANDSCAPE_COLUMN_NUMBER: Int = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GalleryFragmentBinding.inflate(layoutInflater)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            binding.galleryRecyclerView.layoutManager =
                GridLayoutManager(context, LANDSCAPE_COLUMN_NUMBER)
        else
            binding.galleryRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.galleryRecyclerView.adapter = GalleryAdapter(viewModel.film.photos)

        if (viewModel.film.photos.isEmpty()) {
            binding.galleryRecyclerView.visibility = View.GONE
            binding.galleryEmptyMessage.visibility = View.VISIBLE
        }

        return binding.root
    }
}
