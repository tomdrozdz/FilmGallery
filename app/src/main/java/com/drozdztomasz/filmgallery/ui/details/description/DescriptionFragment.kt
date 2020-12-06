package com.drozdztomasz.filmgallery.ui.details.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.drozdztomasz.filmgallery.databinding.DescriptionFragmentBinding
import com.drozdztomasz.filmgallery.ui.details.DetailsViewModel

class DescriptionFragment : Fragment() {
    private lateinit var binding: DescriptionFragmentBinding
    private val viewModel: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DescriptionFragmentBinding.inflate(layoutInflater)

        binding.titleTv.text = getString(viewModel.film.title)
        binding.descriptionTv.text = getString(viewModel.film.description)

        return binding.root
    }
}
