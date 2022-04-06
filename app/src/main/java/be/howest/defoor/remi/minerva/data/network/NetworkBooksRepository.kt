package be.howest.defoor.remi.minerva.data.network

import be.howest.defoor.remi.minerva.data.BooksRepository
import be.howest.defoor.remi.minerva.model.BookDetail

class NetworkBooksRepository: BooksRepository {
    
    override fun getBooks(userId: Int): List<BookDetail> {
        TODO("Not yet implemented")
    }

}