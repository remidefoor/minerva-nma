package be.howest.defoor.remi.minerva.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import be.howest.defoor.remi.minerva.AuthActivity
import be.howest.defoor.remi.minerva.databinding.FragmentProfileBinding
import be.howest.defoor.remi.minerva.model.view_models.ProfileViewModel

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@ProfileFragment
        }
    }

    private fun navigateToBooksFragment() {
        val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToFragmentBooks()
        findNavController().navigate(action)
    }

    fun logOff() {
        // TODO implement
        navigateToAuthActivity()
    }

    private fun navigateToAuthActivity() {
        val intent: Intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
