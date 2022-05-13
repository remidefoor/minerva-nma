package be.howest.defoor.remi.minerva.database

import androidx.room.*
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

     @Query("SELECT * FROM user LIMIT 1")
     fun readUser(): Flow<User>

}
