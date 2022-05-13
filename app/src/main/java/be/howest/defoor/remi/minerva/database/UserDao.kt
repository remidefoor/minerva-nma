package be.howest.defoor.remi.minerva.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createUser(user: User)

     @Query("SELECT * FROM user LIMIT 1")
     fun readUser(): Flow<User>

     @Query("DELETE FROM user")
     fun deleteUser()

}
