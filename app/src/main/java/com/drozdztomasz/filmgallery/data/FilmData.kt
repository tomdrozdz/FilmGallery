package com.drozdztomasz.filmgallery.data

import com.drozdztomasz.filmgallery.ui.list.film.Film

object FilmData {
    fun getFilms(): MutableList<Film> {
        return mutableListOf(
            Film("Torque", Film.Companion.CATEGORY.ACTION, true),
            Film("Another one", Film.Companion.CATEGORY.COMEDY)
        )
    }
}
