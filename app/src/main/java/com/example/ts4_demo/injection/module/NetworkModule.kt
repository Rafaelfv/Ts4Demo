package com.example.ts4_demo.injection.module

import com.example.ts4_demo.BuildConfig
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.repository.ApiSalesForce
import com.example.ts4_demo.utils.BEARER
import com.example.ts4_demo.utils.BEARER_SF
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton


@Module
object NetworkModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Singleton
    internal fun provideApiConsumer(@Named("Retrofit_consumer") retrofit: Retrofit): ApiLogin {
        return retrofit.create(ApiLogin::class.java)
    }

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Singleton
    internal fun provideApiSF(@Named("Retrofit_SF") retrofit: Retrofit): ApiSalesForce {
        return retrofit.create(ApiSalesForce::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("Retrofit_consumer")
    internal fun provideRetrofitInterface(@Named ("ok_consumer_good") okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_CONSUMERGOOD)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("Retrofit_SF")
    internal fun provideRetrofitInterfaceSF(@Named ("ok_SF")okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_SALESFORCE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .registerTypeAdapter(
                Date::class.java,
                JsonDeserializer { json, _, _ -> Date(json.asJsonPrimitive.asLong) })
            .create()
    }

    @Provides
    @Singleton
    @Named("ok_consumer_good")
    fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    " Bearer $BEARER"
                )
                .build()
            chain.proceed(newRequest)
        }.addInterceptor(getInterceptor()).build()
        return client
    }

    @Provides
    @Singleton
    @Named("ok_SF")
    fun getOkHttpClientSF(): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    " Bearer $BEARER_SF"
                )
                .build()
            chain.proceed(newRequest)
        }.addInterceptor(getInterceptor()).build()
        return client
    }

    @Provides
    fun getInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}