package be.howest.defoor.remi.minerva.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import be.howest.defoor.remi.minerva.AuthActivity
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.databinding.FragmentProfileBinding
import be.howest.defoor.remi.minerva.model.view_models.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.fragment = this@ProfileFragment
        binding = fragmentBinding
        return fragmentBinding.root
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNavMenu: BottomNavigationView? = view?.findViewById<BottomNavigationView>(R.id.books_bottom_nav_menu)
        bottomNavMenu?.setupWithNavController(navController)
    }

    private fun navigateToBooksFragment() {
        val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToFragmentBooks()
        findNavController().navigate(action)
    }

    fun logOff() {
        // TODO implement
        // navigateToAuthActivity()
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
