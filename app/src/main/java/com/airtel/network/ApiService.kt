package com.airtel.network

import com.airtel.model.ApiResponse
import com.airtel.model.Suggestion
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/compassLocation/rest/address/autocomplete?queryString=airtel&city=gurgaon")
    fun getAllUsers(): Single<ApiResponse>

    @GET("/compassLocation/rest/address/autocomplete?queryString=airtel")
    fun getSuggestion(@Query("city") cityName: String): Deferred<Response<Suggestion>>

}