package com.example.watchlist.common.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {

    private const val TASTYWORKS_BASE_URL = "https://api.tastyworks.com"
    private const val IEX_CLOUD_BASE_URL = "https://cloud.iexapis.com"

    private const val iexAuthToken = "sk_e9b48de93a884fb58539fb87e1eea738"

    private val gsonFactory = GsonConverterFactory.create()
    private val twOkHttpClient = OkHttpClient()

    private var iexOkHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("token", iexAuthToken)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    var tastyWorksClient: Retrofit = Retrofit.Builder()
        .baseUrl(TASTYWORKS_BASE_URL)
        .client(twOkHttpClient)
        .addConverterFactory(gsonFactory)
        .build()

    var iexClient: Retrofit = Retrofit.Builder()
        .baseUrl(IEX_CLOUD_BASE_URL)
        .client(iexOkHttpClient)
        .addConverterFactory(gsonFactory)
        .build()

}
