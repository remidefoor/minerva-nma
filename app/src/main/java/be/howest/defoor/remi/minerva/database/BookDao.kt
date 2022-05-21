package be.howest.defoor.remi.minerva.database

import androidx.room.*
import be.howest.defoor.remi.minerva.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun createBooks(books: List<Book>)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun readBooks(): Flow<List<Book>>

}