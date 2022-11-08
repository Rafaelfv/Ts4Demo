package com.example.ts4_demo.domain.repository

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiSalesForce {

    @GET("/services/data/v46.0/sobjects/Contact/")
    fun getContacts( ): Single<Response<ResponseBody>>

}