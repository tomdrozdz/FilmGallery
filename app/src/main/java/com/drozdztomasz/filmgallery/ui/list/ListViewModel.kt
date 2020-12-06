package com.drozdztomasz.filmgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drozdztomasz.filmgallery.data.Film
import com.drozdztomasz.filmgallery.data.FilmData

class ListViewModel : ViewModel() {

    var favourite: Boolean = false
        set(value) {
            field = value
            filter()
        }

    var genre: Film.Companion.GENRE? = null
        set(value) {
            field = value
            filter()
        }

    private val _films: MutableList<Film> = FilmData.getFilms()
    private var currentIndices: List<Int> = _films.indices.toList()
    private val filmsData = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = filmsData

    init {
        filmsData.value = _films.toList()
    }

    fun removeFilm(pos: Int) {
        _films.removeAt(currentIndices[pos])
        filter()
    }

    private fun filter() {
        if (!favourite && genre == null) {
            filmsData.value = _films.toList()
            currentIndices = _films.indices.toList()
            return
        }

        var indexedValues = _films.zip(_films.indices)
        if (favourite) indexedValues = indexedValues.filter { it.first.favourite }
        genre?.let { indexedValues = indexedValues.filter { it.first.genre == genre } }

        val lists = indexedValues.unzip()
        filmsData.value = lists.first
        currentIndices = lists.second
    }
}
