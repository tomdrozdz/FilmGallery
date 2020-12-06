package com.drozdztomasz.filmgallery.data

import android.os.Parcelable
import com.drozdztomasz.filmgallery.R
import kotlinx.parcelize.Parcelize

@Parcelize
class Actor(val name: Int, val photo: Int = R.drawable.avatar_placeholder) : Parcelable
