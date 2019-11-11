package stanevich.elizaveta.spbseeker.travel.database.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import stanevich.elizaveta.spbseeker.travel.database.model.Travel

const val BASE_URL = "http://185.91.53.30:8080/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface TravelApiService {

    @GET("point/{id}")
    fun getTask(@Path("id") id: Long): Deferred<Travel>

    @GET("point/nearest")
    fun getTaskByLocation(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double): Deferred<Travel>


}

object TaskApi {
    val retrofitService: TravelApiService by lazy { retrofit.create(TravelApiService::class.java) }
}