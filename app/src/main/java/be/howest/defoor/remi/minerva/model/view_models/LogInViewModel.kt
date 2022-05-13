package be.howest.defoor.remi.minerva.model.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.minerva.Credentials
import be.howest.defoor.remi.minerva.network.minerva.Id
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class LogInViewModel(private val userRepository: UserRepository) : ViewModel() {
    
    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: String
        get() = _email.value!!

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: String
        get() = _password.value!!

    init {
        _email.value = ""
        _password.value = ""
    }

    fun setEmail(email: String) {
        if (this.email != email) _email.value = email
    }

    fun setPassword(password: String) {
        if (this.password != password) _password.value = password
    }

    fun postLogIn() {
        viewModelScope.launch {
            try {
                validateCredentials()
                val credentials = Credentials(_email.value!!, _password.value!!)
                val id: Id = MinervaApi.retrofitService.logIn(credentials)
                val user = User(id.id, credentials.email, credentials.password)
                saveUser(user)
            } catch (ex: IllegalStateException) {
                // TODO display error message
            } catch (ex: Exception) {
                // TODO display api errors
                resetCredentials()
            }
        }
    }

    private fun validateCredentials() {
        if (credentialsAreIncomplete()) {
            throw IllegalStateException("An email and password must be provided")
        }
    }

    private fun credentialsAreIncomplete(): Boolean {
        return _email.value?.length!! == 0 || _password.value?.length!! == 0
    }

    private fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.createUser(user)
        }
    }

    private fun resetCredentials() {
        // TODO values are not reset
        setEmail("")
        setPassword("")
    }

}

class LogInViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(userRepository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }

}
