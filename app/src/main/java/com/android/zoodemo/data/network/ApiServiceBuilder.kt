package com.android.zoodemo.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class ApiServiceBuilder {
    private val retrofit: Retrofit
    val apiService: ApiService

    init {

        val authInterceptor = Interceptor {
            val newUrl = it.request().url.newBuilder()
                    .addQueryParameter("scope", ApiConstant.QUERY_PARAMS_SCOPE)
                    .build()
            val newRequest = it.request().newBuilder().url(newUrl).build()
            it.proceed(newRequest)
        }


        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(authInterceptor).build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }

}