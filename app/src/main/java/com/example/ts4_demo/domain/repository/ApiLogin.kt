package com.example.ts4_demo.domain.repository

import com.example.ts4_demo.ResponseSignup
import com.example.ts4_demo.data.models.User
import com.example.ts4_demo.data.models.login.Credentials
import com.example.ts4_demo.data.models.login.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiLogin {

    @POST("signIn")
    fun login(@Body credentials: Credentials): Single<Response<LoginResponse>>

    @POST("signUp")
    fun signup(@Body user: User): Single<Response<ResponseSignup>>

}