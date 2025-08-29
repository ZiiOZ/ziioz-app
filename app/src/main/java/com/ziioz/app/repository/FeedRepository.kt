// app/src/main/java/com/ziioz/app/repository/FeedRepository.kt
package com.ziioz.app.repository

import com.ziioz.app.model.FeedResponse
import com.ziioz.app.model.Post
import com.ziioz.app.network.ApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class FeedRepository(
    private val api: ApiService = provideApi() // 👈 inline provider, no ApiClient needed
) {

    suspend fun featured(): List<Post> = api.getFeatured()

    suspend fun feed(cursor: String?): FeedResponse = api.getFeed(cursor)

    suspend fun like(id: String)  = api.like(id)
    suspend fun boost(id: String) = api.boost(id)
    suspend fun pin(id: String)   = api.pin(id)
    suspend fun shout(id: String) = api.shout(id)

    companion object {
        private fun provideApi(): ApiService {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                .baseUrl("https://YOUR_BASE_URL/") // TODO: must end with '/'
                .client(okHttp)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiService::class.java)
        }
    }
}
