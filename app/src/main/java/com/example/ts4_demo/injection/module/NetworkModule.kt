package com.grupo.jibaro.tienditas_repartidor.injection.module

import com.example.ts4_demo.BuildConfig
import com.example.ts4_demo.domain.repository.ApiLogin
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Module
object NetworkModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    internal fun providePostApi(retrofit: Retrofit): ApiLogin {
        return retrofit.create(ApiLogin::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient,gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getGson(): Gson {
        val gson = GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ -> Date(json.asJsonPrimitive.asLong) })
            .create()
        return gson
    }

    @Provides
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        return client
    }

    @Provides
    fun getInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}