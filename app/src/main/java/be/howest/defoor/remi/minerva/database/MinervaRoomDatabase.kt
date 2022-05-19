package be.howest.defoor.remi.minerva.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MinervaRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: MinervaRoomDatabase? = null
        fun getDatabase(context: Context): MinervaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MinervaRoomDatabase::class.java,
                    "minerva"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
