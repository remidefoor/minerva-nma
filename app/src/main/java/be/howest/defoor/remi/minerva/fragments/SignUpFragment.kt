package be.howest.defoor.remi.minerva.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import be.howest.defoor.remi.minerva.MainActivity
import be.howest.defoor.remi.minerva.MinervaApplication
import be.howest.defoor.remi.minerva.databinding.FragmentSignUpBinding
import be.howest.defoor.remi.minerva.model.view_models.SignUpViewModel
import be.howest.defoor.remi.minerva.model.view_models.SignUpViewModelFactory

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null
    private val viewModel: SignUpViewModel by activityViewModels{
        SignUpViewModelFactory((activity?.application as MinervaApplication).userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel
        fragmentBinding.fragment = this
        binding = fragmentBinding
        return fragmentBinding.root
    }

    fun signUp() {
        viewModel.postUser()
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    fun navigateToLogInFragment() {
        val action: NavDirections = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
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
