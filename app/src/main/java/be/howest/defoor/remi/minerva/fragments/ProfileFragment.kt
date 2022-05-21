package be.howest.defoor.remi.minerva.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import be.howest.defoor.remi.minerva.AuthActivity
import be.howest.defoor.remi.minerva.MinervaApplication
import be.howest.defoor.remi.minerva.databinding.FragmentProfileBinding
import be.howest.defoor.remi.minerva.model.view_models.ProfileViewModel
import be.howest.defoor.remi.minerva.model.view_models.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels {
        val app: MinervaApplication = activity?.application as MinervaApplication
        ProfileViewModelFactory(app.userRepository, app.bookRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel
        binding = fragmentBinding

        viewModel.loggedIn.observe(viewLifecycleOwner) { loggedIn ->
            if (!loggedIn) navigateToAuthActivity()
        }

        if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES) {
            fragmentBinding.profileTheme.isChecked = true
        }

        fragmentBinding.profileTheme.setOnCheckedChangeListener { switch, checked ->
            if (checked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        return fragmentBinding.root
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
