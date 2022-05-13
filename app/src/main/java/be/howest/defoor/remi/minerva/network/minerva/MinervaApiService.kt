package be.howest.defoor.remi.minerva.network.minerva

import be.howest.defoor.remi.minerva.model.*
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
    suspend fun postUser(@Body credentials: Credentials): Id

    @POST("users/log-in")
    suspend fun logIn(@Body credentials: Credentials): Id

    @GET("users/{userId}/books")
    suspend fun getUserBooks(@Path("userId") userId: Int): List<UserBook>

    @POST("users/{userId}/books")
    suspend fun postUserBook(@Path("userId") userId: Int, @Body userBook: UserBook)

    @GET("users/{userId}/books/{isbn}/notes")
    suspend fun getNotes(@Path("userId") userId: Int, @Path("isbn") isbn: String): List<Note>

    @POST("users/{userId}/books/{isbn}/notes")
    suspend fun postNote(@Path("userId") userId: Int, @Path("isbn") isbn: String, @Body note: NoteText): Id

}

object MinervaApi {
    val retrofitService: MinervaApiService by lazy { retrofit.create(MinervaApiService::class.java) }
}
