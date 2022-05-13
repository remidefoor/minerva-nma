package be.howest.defoor.remi.minerva.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import be.howest.defoor.remi.minerva.database.UserDao
import be.howest.defoor.remi.minerva.model.User

class UserRepository(private val userDao: UserDao) {

    val user: LiveData<User> = userDao.readUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createUser(user: User) {
        userDao.createUser(user)
    }

}
