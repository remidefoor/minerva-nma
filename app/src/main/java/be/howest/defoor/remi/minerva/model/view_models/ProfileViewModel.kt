package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.defoor.remi.minerva.model.User

class ProfileViewModel : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        // _user.value = getUser()
    }

    private fun getUser(): User {
        TODO("TODO get user from room db")
    }

}
