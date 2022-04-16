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
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.adapters.BookAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentBooksBinding
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.view_models.BookViewModel

class BooksFragment : Fragment(), SearchView.OnQueryTextListener {

    private val books: MutableList<Book> = mutableListOf()

    private lateinit var bookAdapter: BookAdapter
    private var binding: FragmentBooksBinding? = null
    private val sharedViewModel: BookViewModel by activityViewModels()

    // TODO remove
    private val mockData: List<Book> = listOf(
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de geheime kamer", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de gevangene van Azkaban", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de vuurbeker", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de Orde van de Feniks", listOf("Joanne Kathleen Rowling")),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentBooksBinding = FragmentBooksBinding.inflate(inflater, container, false)

        books.addAll(mockData)
        bookAdapter = BookAdapter(books, this::handleClickOnBook)
        sharedViewModel.setBooks(books)

        val recyclerView: RecyclerView = fragmentBinding.booksRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = bookAdapter

        binding = fragmentBinding
        return fragmentBinding.root
    }

    private fun handleClickOnBook(book: Book) {
        sharedViewModel.setBook(book)
        navigateToNotes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BooksFragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_action_bar, menu)
        val menuItem: MenuItem = menu.findItem(R.id.search_action_bar_search_view)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.title_search)
        searchView.setOnQueryTextListener(this)
    }

    private fun navigateToNotes() {
        val action: NavDirections = BooksFragmentDirections.actionBooksFragmentToNotesFragment()
        findNavController().navigate(action)
    }

    fun navigateToAddBook() {
        val action: NavDirections = BooksFragmentDirections.actionFragmentBooksToAddBookFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            books.clear()
            books.addAll(sharedViewModel.getFilteredBooks(it))
            bookAdapter.notifyDataSetChanged()
        }
        return true
    }

}
