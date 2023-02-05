package com.shoppi.shoppi.retrofit_clone_coding


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//싱글톤으로 만들어지도록
object RetrofitApi {
    private const val BASE_URL = "https://openapi.gg.go.kr/"

    //lazy를 활용해 사용되는 순간에 만들어짐
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    val emgMedService: EmgMedService by lazy {
        retrofit.create(EmgMedService::class.java)
    }
}