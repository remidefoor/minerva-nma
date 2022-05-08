package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.adapters.NoteAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentNotesBinding
import be.howest.defoor.remi.minerva.model.view_models.NotesViewModel
import be.howest.defoor.remi.minerva.model.view_models.NotesViewModelFactory

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null
    private lateinit var viewModelFactory: NotesViewModelFactory
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = NotesViewModelFactory(NotesFragmentArgs.fromBundle(requireArguments()).book)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        fragmentBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }

        val recyclerView: RecyclerView = fragmentBinding.notesRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter()

        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
