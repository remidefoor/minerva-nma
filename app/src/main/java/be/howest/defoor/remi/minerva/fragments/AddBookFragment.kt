package be.howest.defoor.remi.minerva.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import be.howest.defoor.remi.minerva.MinervaApplication
import be.howest.defoor.remi.minerva.databinding.FragmentAddBookBinding
import be.howest.defoor.remi.minerva.model.view_models.AddBookViewModel
import be.howest.defoor.remi.minerva.model.view_models.AddBookViewModelFactory
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class AddBookFragment : Fragment() {

    private var binding: FragmentAddBookBinding? = null
    private val viewModel: AddBookViewModel by activityViewModels {
        val app: MinervaApplication = activity?.application as MinervaApplication
        AddBookViewModelFactory(app.userRepository, app.bookRepository)
    }

    private val scanner: ActivityResultLauncher<ScanOptions> = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) viewModel.setIsbn(result.contents)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentAddBookBinding = FragmentAddBookBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel
        fragmentBinding.fragment = this
        binding = fragmentBinding

        viewModel.bookAdded.observe(viewLifecycleOwner) { bookAdded ->
            if (bookAdded) navigateToBooks()
        }

        return fragmentBinding.root
    }

    fun scanBook() {
        val scannerOptions = ScanOptions()
        scannerOptions.setDesiredBarcodeFormats(ScanOptions.EAN_13)
        scannerOptions.setOrientationLocked(false)
        scannerOptions.setPrompt("Scan an ISBN")
        scannerOptions.setBeepEnabled(false)

        scanner.launch(scannerOptions)
    }

    private fun navigateToBooks() {
        val action: NavDirections = AddBookFragmentDirections.actionAddBookFragmentToFragmentBooks()
        findNavController().navigate(action)
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
