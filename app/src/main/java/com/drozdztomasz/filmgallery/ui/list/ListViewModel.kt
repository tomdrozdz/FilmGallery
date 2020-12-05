package com.drozdztomasz.filmgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drozdztomasz.filmgallery.data.FilmData
import com.drozdztomasz.filmgallery.ui.list.film.Film

class ListViewModel() : ViewModel() {

    var favourite: Boolean = false
        set(value) {
            field = value
            filter()
        }

    var category: Film.Companion.CATEGORY? = null
        set(value) {
            field = value
            filter()
        }

    private val _films: MutableList<Film> = FilmData.getFilms()
    private val _filmsData = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = _filmsData

    init {
        _filmsData.value = _films.toList()
    }

    fun removeFilm(pos: Int) {
        _films.removeAt(pos)
        filter()
    }

    private fun filter() {
        var list: List<Film> = _films
        if (favourite) list = list.filter { film -> film.favourite }
        category?.let { list = list.filter { film -> film.category == category } }
        _filmsData.value = list
    }
}
