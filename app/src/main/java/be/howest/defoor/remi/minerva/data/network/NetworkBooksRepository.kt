package be.howest.defoor.remi.minerva.data.network

import be.howest.defoor.remi.minerva.data.BooksRepository
import be.howest.defoor.remi.minerva.model.Book

class NetworkBooksRepository: BooksRepository {
    
    override fun getBooks(userId: Int): List<Book> {
        TODO("Not yet implemented")
    }

}