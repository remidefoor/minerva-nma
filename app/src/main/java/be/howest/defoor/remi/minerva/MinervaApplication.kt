package be.howest.defoor.remi.minerva

import android.app.Application
import be.howest.defoor.remi.minerva.Repositories.BookRepository
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.database.MinervaRoomDatabase

class MinervaApplication: Application() {

    val database: MinervaRoomDatabase by lazy { MinervaRoomDatabase.getDatabase(this) }
    val userRepository: UserRepository by lazy {  UserRepository(database.userDao()) }
    val bookRepository: BookRepository by lazy { BookRepository(userRepository, database.bookDao()) }

}
