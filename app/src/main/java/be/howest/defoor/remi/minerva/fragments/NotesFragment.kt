package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.databinding.FragmentNotesBinding
import be.howest.defoor.remi.minerva.model.view_models.BookViewModel

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null
    private val sharedViewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
        TODO("boilerplate code")
//        val notes: List<Note> = DataSource().loadNotes()
//        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapters = NoteAdapter(this, notes)
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