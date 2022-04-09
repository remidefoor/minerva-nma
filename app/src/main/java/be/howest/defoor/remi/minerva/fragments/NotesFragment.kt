package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.adapters.NoteAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentNotesBinding
import be.howest.defoor.remi.minerva.model.Note
import be.howest.defoor.remi.minerva.model.view_models.BookViewModel

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null
    private val sharedViewModel: BookViewModel by activityViewModels()

    // TODO remove
    private val mockData: List<Note> = listOf(
        Note(1, "Excited!!!"),
        Note(2, "Perron 9 3/4"),
        Note(3, "Ron and Hermione"),
        Note(4, "Dumbledore"),
        Note(5, "Hogwarts"),
        Note(6, "The super long note to test the behavior of super long notes in my awesome application for taking notes on books."),
        Note(7, "Voldemort")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = fragmentBinding.notesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter(requireContext(), mockData)

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            model = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}