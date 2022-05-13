package be.howest.defoor.remi.minerva

import android.app.Application
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.database.UserRoomDatabase

class MinervaApplication: Application() {

    val database: UserRoomDatabase by lazy { UserRoomDatabase.getDatabase(this) }
    val userRepository: UserRepository by lazy {  UserRepository(database.userDao()) }

}
