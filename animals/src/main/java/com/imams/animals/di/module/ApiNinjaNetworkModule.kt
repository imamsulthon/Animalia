package com.imams.animals.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.imams.animals.BuildConfig
import com.imams.animals.di.Animalia
import com.imams.animals.util.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiNinjaNetworkModule {

    @Provides
    @Singleton
    @Animalia
    fun retrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BaseURLNinja)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        chuckInterceptor: ChuckerInterceptor,
    ) : OkHttpClient {
        val builder =  OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .pingInterval(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Api-Key", Constant.API_KEY)
                return@addInterceptor chain.proceed(request.build())
            }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(chuckInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun createChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

}