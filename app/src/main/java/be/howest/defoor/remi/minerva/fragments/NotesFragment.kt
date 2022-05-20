package be.howest.defoor.remi.minerva.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.MinervaApplication
import be.howest.defoor.remi.minerva.adapters.NoteAdapter
import be.howest.defoor.remi.minerva.databinding.FragmentNotesBinding
import be.howest.defoor.remi.minerva.model.view_models.NotesViewModel
import be.howest.defoor.remi.minerva.model.view_models.NotesViewModelFactory
import java.lang.Exception

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as MinervaApplication).userRepository,
            NotesFragmentArgs.fromBundle(requireArguments()).book
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel
        fragmentBinding.fragment = this
        binding = fragmentBinding

        val recyclerView: RecyclerView = fragmentBinding.notesRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter()

        return fragmentBinding.root
    }

    /**
     * This functionality is to show I know how to use implicit intents.
     * The Camera class is deprecated and usage of CameraX instead is recommended.
     * CameraX, however, makes use of builders and does not enable me to
     * demonstrate implicit intent usage.
     */
    fun dispatchTakePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivity(takePictureIntent)
        } catch (ex: Exception) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // hide keyboard
        val inputMethodManager: InputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE)
            as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }

}
