package com.drozdztomasz.filmgallery.ui.list.film

class Film(val name: String, val category: CATEGORY, var favourite: Boolean = false) {

    companion object {
        enum class CATEGORY {
            COMEDY, ACTION, HORROR
        }
    }

}
