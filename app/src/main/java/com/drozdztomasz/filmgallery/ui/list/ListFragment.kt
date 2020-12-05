package com.drozdztomasz.filmgallery.ui.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drozdztomasz.filmgallery.R
import com.drozdztomasz.filmgallery.databinding.ListFragmentBinding
import com.drozdztomasz.filmgallery.ui.list.film.Film
import com.drozdztomasz.filmgallery.ui.list.film.FilmAdapter
import com.drozdztomasz.filmgallery.ui.list.film.SwipeToDeleteCallback

class ListFragment : Fragment() {
    private lateinit var binding: ListFragmentBinding

    private lateinit var viewModel: ListViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var searchButton: MenuItem
    private val categories: Array<Film.Companion.CATEGORY> = Film.Companion.CATEGORY.values()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewManager = LinearLayoutManager(context)
        viewAdapter = FilmAdapter(viewModel.films)

        binding.recyclerView.layoutManager = viewManager
        binding.recyclerView.adapter = viewAdapter

        observeLiveData()
        attachDeleteHelper()

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
        var buttonIcon: Int
        var buttonString: Int

        if (viewModel.favourite) {
            buttonIcon = R.drawable.favourites_on
            buttonString = R.string.favourites_on
        }
        else {
            buttonIcon = R.drawable.favourites_off
            buttonString = R.string.favourites_off
        }

        item.setIcon(buttonIcon)
        item.setTitle(buttonString)
    }

    private fun onFilter() {
        if (viewModel.category == null) {
            showCategoryDialog()
        }
        else {
            viewModel.category = null
            searchButton.setIcon(R.drawable.search_off)
            searchButton.setTitle(R.string.search_off)
        }
    }

    private fun showCategoryDialog() {
        val builder = AlertDialog.Builder(context)
        val arr = arrayOf("Comedy", "String")
        builder.setTitle(resources.getString(R.string.search_title))
        builder.setItems(arr) { dialog, which ->
            viewModel.category = Film.Companion.CATEGORY.COMEDY
            updateSearchButton()
        }
        builder.show()
    }

    private fun updateSearchButton() {
        if (viewModel.category != null) {
            searchButton.setIcon(R.drawable.search_on)
            searchButton.setTitle(R.string.search_on)
        }
    }
}
