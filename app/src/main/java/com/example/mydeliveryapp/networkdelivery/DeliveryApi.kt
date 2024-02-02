package com.example.mydeliveryapp.networkdelivery

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "https://shift-backend.onrender.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface DeliveryApiService {
    @GET("delivery/points")
    suspend fun getPoints(): DeliveryPoints
    @GET("delivery/package/types")
    suspend fun getTypes(): DeliveryTypes
    @POST("delivery/calc")
    suspend fun getCalculation(
       @Body calculateBody:CalculateBody
    ):DeliveryCalculateList

}

object DeliveryApi {
    val retrofitService: DeliveryApiService by lazy {
        retrofit.create((DeliveryApiService::class.java))
    }
}