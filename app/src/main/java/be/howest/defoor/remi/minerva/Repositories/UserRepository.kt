package be.howest.defoor.remi.minerva.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import be.howest.defoor.remi.minerva.database.UserDao
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User> = userDao.readUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createUser(user: User) {
        userDao.createUser(user)
    }

}
