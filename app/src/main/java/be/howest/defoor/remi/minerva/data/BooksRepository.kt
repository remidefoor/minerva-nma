package be.howest.defoor.remi.minerva.data

import be.howest.defoor.remi.minerva.model.Book

interface BooksRepository {

    fun getBooks(userId: Int): List<Book>

}