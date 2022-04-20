package be.howest.defoor.remi.minerva.network

import be.howest.defoor.remi.minerva.model.Note
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.model.UserBook
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://192.168.56.56/api/"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MinervaApiService {

    @POST("users")
    suspend fun postUser(@Body user: User): Int // TODO specify return type

    @POST("users/log-in")
    suspend fun logIn(@Body user: User): Int // TODO specify return type

    @GET("users/{userId}/books")
    suspend fun getUserBooks(@Path("userId") userId: Int): List<UserBook> // TODO alter return type

    @POST("users/{userId}/books")
    suspend fun postUserBook(@Path("userId") userId: Int, @Body userBook: UserBook) // TODO specify return type & alter body type

    @GET("users/{userId}/books/{isbn}/notes")
    suspend fun getNotes(@Path("userId") userId: Int, @Path("isbn") isbn: String): List<Note>

    @POST("users/{userId}/books/{isbn}/notes")
    suspend fun postNote(@Path("userId") userId: Int, @Path("isbn") isbn: String, @Body note: String) // TODO specify return type && alter body type

}

object MinervaApi {
    val retrofitService: MinervaApiService by lazy { retrofit.create(MinervaApiService::class.java) }
}
