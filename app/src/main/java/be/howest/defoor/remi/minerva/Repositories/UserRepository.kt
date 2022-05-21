package be.howest.defoor.remi.minerva.Repositories

import androidx.annotation.WorkerThread
import be.howest.defoor.remi.minerva.database.UserDao
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User> = userDao.readUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createUser(user: User) {
        userDao.createUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}
