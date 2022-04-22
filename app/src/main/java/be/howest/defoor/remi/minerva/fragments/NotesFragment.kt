package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.adapters.NoteAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentNotesBinding
import be.howest.defoor.remi.minerva.model.view_models.BooksViewModel

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null
    private val sharedViewModel: BooksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = fragmentBinding.notesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter(emptyList())

        binding = fragmentBinding
        return fragmentBinding.root
    }

    fun addNote() {
        /**
         * TODO
         * post note
         * add note to notes
         * update recycler view
         */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@NotesFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}