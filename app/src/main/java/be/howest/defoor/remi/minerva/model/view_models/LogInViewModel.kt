package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.MinervaApi
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    
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
        if(!this.email.equals(email)) {
            _email.value = email
        }
    }

    fun setPassword(password: String) {
        if (!this.password.equals(password)) {
            _password.value = password
        }
    }

    fun logIn() {
        viewModelScope.launch {
            try {
                _email.value?.let { email ->
                    _password.value?.let { password ->
                        val userId: Int = MinervaApi.retrofitService.logIn(User("harry.potter@hogwarts.wiz", "Nimbus2000"))
                        println(userId)
                    }
                }
            } catch (ex: Exception) {
                // TODO NYI
                resetCredentials()
            }
        }
    }

    private fun resetCredentials() {
        _email.value = ""
        _password.value = ""
    }

}
