package be.howest.defoor.remi.minerva.data

import be.howest.defoor.remi.minerva.model.BookDetail

interface BooksRepository {

    fun getBooks(userId: Int): List<BookDetail>

}