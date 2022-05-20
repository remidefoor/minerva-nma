package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.minerva.Credentials
import be.howest.defoor.remi.minerva.network.minerva.Id
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: String
        get() = _email.value!!

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: String
        get() = _password.value!!

    private val _confirmedPassword: MutableLiveData<String> = MutableLiveData<String>()
    val confirmedPassword: String
        get() = _confirmedPassword.value!!

    private val _loggedIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
         get() = _loggedIn

    init {
        _email.value = ""
        _password.value = ""
        _confirmedPassword.value = ""
        _loggedIn.value = false
    }

    fun setEmail(email: String) {
        if (this.email != email) _email.value = email
    }

    fun setPassword(password: String) {
        if (this.password != password) _password.value = password
    }

    fun setConfirmedPassword(confirmedPassword: String) {
        if (this.confirmedPassword != confirmedPassword) _confirmedPassword.value = confirmedPassword
    }

    fun postUser() {
        viewModelScope.launch {
            try {
                validateCredentials()
                val credentials = Credentials(_email.value!!, _password.value!!)
                val id: Id = MinervaApi.retrofitService.postUser(credentials)
                val user = User(id.id, credentials.email, credentials.password)
                saveUser(user)
            } catch (ex: IllegalStateException) {
                resetPasswords()
            } catch (ex: Exception) {
                resetCredentials()
            }
        }
    }

    private fun validateCredentials() {
        if (credentialsAreIncomplete()) {
            throw IllegalStateException("An email and password must be provided")
        }

        if (passwordsDiffer()) {
            throw IllegalStateException("The provided passwords must be the same.")
        }
    }

    private fun credentialsAreIncomplete(): Boolean {
        return _email.value?.length!! == 0 || _password.value?.length == 0
                || _confirmedPassword.value?.length == 0
    }

    private fun passwordsDiffer(): Boolean {
        return _password.value != _confirmedPassword.value
    }

    private fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.createUser(user)
            _loggedIn.value = true
        }
    }

    private fun resetPasswords() {
        setPassword("")
        setConfirmedPassword("")
    }

    private fun resetCredentials() {
        setEmail("")
        setPassword("")
        setConfirmedPassword("")
    }

}

class SignUpViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userRepository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }

}
