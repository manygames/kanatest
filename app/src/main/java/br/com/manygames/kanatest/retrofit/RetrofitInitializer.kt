package br.com.manygames.kanatest.retrofit

import br.com.manygames.kanatest.retrofit.services.GithubApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInitializer {
    private val retrofit: Retrofit

    val gitService: GithubApiServices
        get() = retrofit.create(GithubApiServices::class.java)

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build()
    }
}