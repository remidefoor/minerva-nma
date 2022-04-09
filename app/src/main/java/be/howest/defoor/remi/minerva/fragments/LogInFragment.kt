package be.howest.defoor.remi.minerva.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import be.howest.defoor.remi.minerva.AuthActivity
import be.howest.defoor.remi.minerva.MainActivity
import be.howest.defoor.remi.minerva.databinding.FragmentLogInBinding
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.model.view_models.LogInViewModel
import be.howest.defoor.remi.minerva.network.MinervaApi

class LogInFragment : Fragment() {

    private var binding: FragmentLogInBinding? = null
    private val viewModel: LogInViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentLogInBinding = FragmentLogInBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@LogInFragment
        }
    }

    fun logIn() {
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val intent: Intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSignUpFragment() {
        val action: NavDirections = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}