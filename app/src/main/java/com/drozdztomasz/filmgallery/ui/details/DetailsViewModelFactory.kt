package com.drozdztomasz.filmgallery.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drozdztomasz.filmgallery.data.Film

class DetailsViewModelFactory(private val film: Film) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(film) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
