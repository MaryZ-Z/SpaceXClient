package com.example.testcaseci.restapi

import com.example.testcaseci.data.launchdetail.DetailPagingResponse
import com.example.testcaseci.data.launches.LaunchesBody
import com.example.testcaseci.data.launches.PagingResponse
import com.example.testcaseci.data.launchdetail.BodyCrew
import com.example.testcaseci.data.launchdetail.BodyDetail
import retrofit2.http.Body
import retrofit2.http.POST

interface LaunchesService {

    @POST("v4/launches/query")
    suspend fun getLaunches(@Body options: LaunchesBody): PagingResponse

    @POST("v4/launches/query")
    suspend fun getDetails(@Body options: BodyDetail): PagingResponse

    @POST("v4/crew/query")
    suspend fun getCrew(@Body body: BodyCrew): DetailPagingResponse
}