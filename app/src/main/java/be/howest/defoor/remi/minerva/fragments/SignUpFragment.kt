package be.howest.defoor.remi.minerva.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import be.howest.defoor.remi.minerva.MainActivity
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.databinding.FragmentSignUpBinding
import be.howest.defoor.remi.minerva.model.view_models.SignUpViewModel

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null
    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.fragment = this@SignUpFragment
        binding = fragmentBinding
        return fragmentBinding.root
    }

    fun signUp() {
        viewModel.postUser()
        // navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val intent: Intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    fun navigateToLogInFragment() {
        val action: NavDirections = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
