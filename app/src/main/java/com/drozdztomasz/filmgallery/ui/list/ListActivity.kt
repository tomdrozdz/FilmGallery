// Tomasz Drozdz, 246718
// Testowane na emulatorze + Samsung Galaxy Note 9

package com.drozdztomasz.filmgallery.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drozdztomasz.filmgallery.R

class ListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.list_activity)
	}
}
