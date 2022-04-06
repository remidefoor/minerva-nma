package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.databinding.FragmentBooksBinding
import be.howest.defoor.remi.minerva.model.view_models.BookViewModel

class BooksFragment : Fragment() {

    private var binding: FragmentBooksBinding? = null
    private val sharedViewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
        TODO("boilerplate code")
//        val books: List<BookDetail> = DataSource().loadBooks()
//        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.books_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapters = BookAdapter(this, books)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun navigateToNotes() {
        val action = BooksFragmentDirections.actionBooksFragmentToNotesFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}