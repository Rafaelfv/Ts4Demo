package com.example.ts4_demo.domain.repository

import com.example.ts4_demo.ResponseBase
import com.example.ts4_demo.data.models.Credentials
import com.example.ts4_demo.data.models.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiLogin {

    @Headers("Accept: application/json", "Connection: close")
    @POST("login")
    fun login(@Body credentials: Credentials): Single<Response<ResponseBase>>

    @Headers("Accept: application/json", "Connection: close")
    @POST("signup")
    fun signup(@Body user: User): Single<Response<ResponseBase>>

}