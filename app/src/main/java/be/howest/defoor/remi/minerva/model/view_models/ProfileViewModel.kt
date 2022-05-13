package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.User
import java.lang.IllegalArgumentException

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    val user: LiveData<User> = userRepository.user.asLiveData()

}

class ProfileViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("unkown view model class")
    }

}
