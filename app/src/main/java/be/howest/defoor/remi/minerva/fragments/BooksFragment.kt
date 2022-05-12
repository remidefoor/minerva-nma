package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.adapters.BookAdapter
import be.howest.defoor.remi.minerva.adapters.BookListener
import be.howest.defoor.remi.minerva.databinding.FragmentBooksBinding
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.view_models.BooksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class BooksFragment : Fragment(), SearchView.OnQueryTextListener {

    private val bookAdapter: BookAdapter = BookAdapter(BookListener { book: Book ->  handleClickOnBook(book) })
    private var binding: FragmentBooksBinding? = null
    private val viewModel: BooksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentBooksBinding = FragmentBooksBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel
        fragmentBinding.fragment = this
        setHasOptionsMenu(true)
        binding = fragmentBinding

        val recyclerView: RecyclerView = fragmentBinding.booksRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = bookAdapter

        return fragmentBinding.root
    }

    private fun handleClickOnBook(book: Book) {
        viewModel.setSelectedBook(book)
        navigateToNotes(book)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNavMenu: BottomNavigationView? = view?.findViewById<BottomNavigationView>(R.id.books_bottom_nav_menu)
        bottomNavMenu?.setupWithNavController(navController)
    }

    private fun navigateToNotes(book: Book) {
        val action: NavDirections = BooksFragmentDirections.actionBooksFragmentToNotesFragment(book)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_action_bar, menu)
        val menuItem: MenuItem = menu.findItem(R.id.search_action_bar_search_view)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.hint_search)
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.filterBooks(it)
            bookAdapter.notifyDataSetChanged()
        }
        return true
    }

    fun navigateToAddBook() {
        val action: NavDirections = BooksFragmentDirections.actionFragmentBooksToAddBookFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
