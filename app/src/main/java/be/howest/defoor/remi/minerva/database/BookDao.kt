package be.howest.defoor.remi.minerva.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.howest.defoor.remi.minerva.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun createBooks(books: List<Book>)

    @Query("SELECT * FROM books")
    fun readBooks(): Flow<List<Book>>

}