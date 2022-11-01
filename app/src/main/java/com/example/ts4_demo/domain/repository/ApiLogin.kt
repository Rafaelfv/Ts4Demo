package com.example.ts4_demo.domain.repository

import com.example.ts4_demo.ResponseBase
import com.example.ts4_demo.data.models.User
import com.example.ts4_demo.data.models.login.Credentials
import com.example.ts4_demo.data.models.login.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiLogin {

    @Headers(
        "Authorization: Bearer 2viktpsXucqnjEj6L7mRefe5mkQT7W4c",
        "Accept: application/json",
        "Connection: close"
    )
    @POST("login")
    fun login(@Body credentials: Credentials): Single<Response<LoginResponse>>

    @Headers("Accept: application/json", "Connection: close")
    @POST("signup")
    fun signup(@Body user: User): Single<Response<ResponseBase>>

}