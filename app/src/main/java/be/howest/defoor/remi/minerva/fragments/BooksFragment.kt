package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.adapters.BookAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentBooksBinding
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.view_models.BookViewModel

class BooksFragment : Fragment() {

    private var binding: FragmentBooksBinding? = null
    private val sharedViewModel: BookViewModel by activityViewModels()

    // TODO remove
    private val mockData: List<Book> = listOf(
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
        Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentBooksBinding = FragmentBooksBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = fragmentBinding.booksRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = BookAdapter(mockData) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BooksFragment
        }
    }

    fun navigateToNotes() {
        val action: NavDirections = BooksFragmentDirections.actionBooksFragmentToNotesFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
