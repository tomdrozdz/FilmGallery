// Tomasz Drozdz, 246718
// Testowane na emulatorze + Samsung Galaxy Note 9

package com.drozdztomasz.filmgallery.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drozdztomasz.filmgallery.databinding.ListActivityBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
