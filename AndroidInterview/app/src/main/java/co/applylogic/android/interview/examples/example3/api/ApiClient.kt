package co.applylogic.android.interview.examples.example3.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClient(var context: Context) {

    fun getClient(baseURL: String): Retrofit? {
        var retrofit: Retrofit? = null
        try {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .client( OkHttpClient().newBuilder().build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retrofit
    }
}