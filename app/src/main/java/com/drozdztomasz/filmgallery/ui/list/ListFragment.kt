package com.drozdztomasz.filmgallery.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drozdztomasz.filmgallery.R
import com.drozdztomasz.filmgallery.data.Film
import com.drozdztomasz.filmgallery.databinding.ListFragmentBinding
import com.drozdztomasz.filmgallery.ui.list.film_adapter.FilmAdapter
import com.drozdztomasz.filmgallery.ui.list.film_adapter.SwipeToDeleteCallback

class ListFragment : Fragment() {
    private lateinit var binding: ListFragmentBinding
    private val viewModel: ListViewModel by viewModels()

    private lateinit var searchButton: MenuItem
    private lateinit var categories: List<Film.Companion.GENRE>
    private lateinit var categoriesStrings: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = FilmAdapter(viewModel.films)

        observeLiveData()
        attachDeleteHelper()
        loadCategories()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.films.observe(
            viewLifecycleOwner,
            { list ->
                if (list.isEmpty()) {
                    binding.recyclerView.visibility = View.GONE
                    binding.emptyMessage.visibility = View.VISIBLE
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.emptyMessage.visibility = View.GONE
                }

                (binding.recyclerView.adapter as FilmAdapter).submitList(list)
            })
    }

    private fun attachDeleteHelper() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.removeFilm(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun loadCategories() {
        categories = Film.Companion.GENRE.values().sortedBy { resources.getString(it.stringId) }
        categoriesStrings = categories.map { resources.getString(it.stringId) }.toTypedArray()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        searchButton = menu.findItem(R.id.filter_btn)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_favourites -> {
                onFavourite(item)
                true
            }
            R.id.filter_btn -> {
                onFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onFavourite(item: MenuItem) {
        viewModel.favourite = !viewModel.favourite
        val buttonIcon: Int
        val buttonString: Int

        if (viewModel.favourite) {
            buttonIcon = R.drawable.favourites_on
            buttonString = R.string.favourites_on
        } else {
            buttonIcon = R.drawable.favourites_off
            buttonString = R.string.favourites_off
        }

        item.setIcon(buttonIcon)
        item.setTitle(buttonString)
    }

    private fun onFilter() {
        if (viewModel.genre == null) {
            showGenreDialog()
        } else {
            viewModel.genre = null
            searchButton.setIcon(R.drawable.search_off)
            searchButton.setTitle(R.string.search_off)
        }
    }

    private fun showGenreDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(resources.getString(R.string.search_title))
        builder.setItems(categoriesStrings) { _, which ->
            viewModel.genre = categories[which]
            updateSearchButton()
        }
        builder.show()
    }

    private fun updateSearchButton() {
        if (viewModel.genre != null) {
            searchButton.setIcon(R.drawable.search_on)
            searchButton.setTitle(R.string.search_on)
        }
    }
}
