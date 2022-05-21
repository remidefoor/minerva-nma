package be.howest.defoor.remi.minerva.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import be.howest.defoor.remi.minerva.database.BookDao
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.google_books.GoogleBooksApi
import be.howest.defoor.remi.minerva.network.google_books.Volume
import be.howest.defoor.remi.minerva.network.google_books.VolumeInfo
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import be.howest.defoor.remi.minerva.network.minerva.UserBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class BookRepository(private val userRepository: UserRepository, private val bookDao: BookDao) {

    val books: LiveData<List<Book>> = bookDao.readBooks().asLiveData()

    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            val user: User = userRepository.user.first()
            val userBooks: List<UserBook> = MinervaApi.retrofitService.getUserBooks(user.id)
            val books: MutableList<Book> = mutableListOf()
            for (userBook: UserBook in userBooks) {
                val volume: Volume = GoogleBooksApi.retrofitService.getBook("isbn:${userBook.isbn}")
                val book: Book = mapVolumeToBook(userBook.isbn, volume)
                books.add(book)
            }
            bookDao.createBooks(books)
        }
    }

    private fun mapVolumeToBook(isbn: String, volume: Volume): Book {
        val volumeInfo: VolumeInfo = volume.items.first().volumeInfo
        return Book(
            isbn,
            volumeInfo.imageLinks.thumbnail,
            volumeInfo.title
        )
    }

}
