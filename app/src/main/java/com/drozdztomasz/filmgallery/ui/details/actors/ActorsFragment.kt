package com.drozdztomasz.filmgallery.ui.details.actors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.drozdztomasz.filmgallery.databinding.ActorsFragmentBinding
import com.drozdztomasz.filmgallery.ui.details.DetailsViewModel

class ActorsFragment : Fragment() {
    private lateinit var binding: ActorsFragmentBinding
    private val viewModel: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActorsFragmentBinding.inflate(layoutInflater)

        binding.actorsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.actorsRecyclerView.adapter = ActorAdapter(viewModel.film.actors)

        if (viewModel.film.actors.isEmpty()) {
            binding.actorsRecyclerView.visibility = View.GONE
            binding.actorsEmptyMessage.visibility = View.VISIBLE
        }

        return binding.root
    }
}
