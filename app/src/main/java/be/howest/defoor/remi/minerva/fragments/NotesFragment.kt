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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = fragmentBinding.notesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter(sharedViewModel.getNotes())

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