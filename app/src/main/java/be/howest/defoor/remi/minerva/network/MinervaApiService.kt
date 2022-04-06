package be.howest.defoor.remi.minerva.network

import be.howest.defoor.remi.minerva.model.UserBook
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val MINERVA_BASE_URL = "https://laravel.minerva.com/api"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(MINERVA_BASE_URL)
    .build()

interface MinervaApiService {

    @GET("/users/{userId}/books")
    suspend fun getUserBooks(@Path("userId") userId: Int): List<UserBook>

    @POST("/users/{userId}/books")
    suspend fun postUserBook(@Path("userId") userId: Int, @Body userBook: UserBook) // TODO specify return type

}

object MinervaApi {
    val retrofitService: MinervaApiService by lazy { retrofit.create(MinervaApiService::class.java) }
}
