package com.example.ts4_demo.domain.repository

import com.example.ts4_demo.ResponseSignup
import com.example.ts4_demo.data.models.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiLogin {

    @Headers("Authorization: Bearer 2viktpsXucqnjEj6L7mRefe5mkQT7W4c","Accept: application/json", "Connection: close")
    @POST("registerUser")
    fun signup(@Body user: User): Single<Response<ResponseSignup>>

}