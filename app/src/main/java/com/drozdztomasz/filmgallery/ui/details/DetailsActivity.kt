package com.drozdztomasz.filmgallery.ui.details

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.drozdztomasz.filmgallery.R
import com.drozdztomasz.filmgallery.databinding.DetailsActivityBinding
import com.drozdztomasz.filmgallery.ui.details.actors.ActorsFragment
import com.drozdztomasz.filmgallery.ui.details.description.DescriptionFragment
import com.drozdztomasz.filmgallery.ui.details.gallery.GalleryFragment
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var viewModelFactory: DetailsViewModelFactory

    companion object {
        private val fragments =
            listOf(DescriptionFragment::class, GalleryFragment::class, ActorsFragment::class)
        private val labels = listOf(R.string.description, R.string.gallery, R.string.actors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args: DetailsActivityArgs by navArgs()
        viewModelFactory = DetailsViewModelFactory(args.film)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.pager.adapter = DetailsAdapter(this, fragments)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, pos ->
            tab.text = getString(labels[pos])
        }.attach()
    }
}
