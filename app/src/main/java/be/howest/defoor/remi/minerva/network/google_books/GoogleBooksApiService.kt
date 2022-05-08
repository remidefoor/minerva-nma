package be.howest.defoor.remi.minerva.network.google_books

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GoogleBooksApiService {

    @GET("volumes?q=isbn:{isbn}")
    suspend fun getBook(@Path("isbn") isbn: String): Volume

}

object GoogleBooksApi {
    val retrofitService: GoogleBooksApiService by lazy { retrofit.create(GoogleBooksApiService::class.java) }
}
