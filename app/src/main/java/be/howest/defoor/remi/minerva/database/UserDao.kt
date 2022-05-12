package be.howest.defoor.remi.minerva.database

import androidx.lifecycle.LiveData
import androidx.room.*
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createUser(user: User)

     @Query("SELECT * FROM user LIMIT 1")
     fun readUser(): LiveData<User>

     @Query("DELETE FROM user")
     fun deleteUser()

}
