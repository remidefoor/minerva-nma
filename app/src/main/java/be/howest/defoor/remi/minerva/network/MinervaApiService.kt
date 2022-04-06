package be.howest.defoor.remi.minerva.network

import be.howest.defoor.remi.minerva.model.Book
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val MINERVA_BASE_URL = "https://laravel.minerva.com/api"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(MINERVA_BASE_URL)
    .build()

interface MinervaApiService {

    @GET("/users/:userId/books")
    suspend fun getUserBooks(): List<Book>

}

object MinervaApi {
    val retrofitService: MinervaApiService by lazy { retrofit.create(MinervaApiService::class.java) }
}
