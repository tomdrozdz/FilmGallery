package com.drozdztomasz.filmgallery.data

import android.os.Parcelable
import com.drozdztomasz.filmgallery.R
import kotlinx.parcelize.Parcelize

@Parcelize
class Film(
    val title: Int,
    val genre: GENRE,
    val poster: Int,
    var favourite: Boolean = false,
    val description: Int,
    val photos: List<Int>,
    val actors: List<Actor>
) :
    Parcelable {
    companion object {
        enum class GENRE(val stringId: Int) {
            COMEDY(R.string.comedy), ACTION(R.string.action), HORROR(R.string.horror)
        }
    }

    override fun equals(other: Any?): Boolean =
        (other is Film) && title == other.title && genre == other.genre && poster == other.poster

    override fun hashCode(): Int {
        return title xor genre.stringId xor poster
    }
}
