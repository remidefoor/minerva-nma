package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    val user: LiveData<User> = userRepository.user.asLiveData()

    fun signOff() {
        viewModelScope.launch {
            userRepository.deleteUser(user.value!!)
        }
    }

}

class ProfileViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("unkown view model class")
    }

}
