package be.howest.defoor.remi.minerva.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import be.howest.defoor.remi.minerva.databinding.FragmentAddBookBinding
import be.howest.defoor.remi.minerva.model.view_models.AddBookViewModel

class AddBookFragment : Fragment() {

    private var binding: FragmentAddBookBinding? = null
    private val viewModel: AddBookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentAddBookBinding = FragmentAddBookBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
