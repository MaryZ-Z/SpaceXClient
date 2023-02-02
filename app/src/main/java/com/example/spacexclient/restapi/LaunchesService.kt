package com.example.spacexclient.restapi

import com.example.spacexclient.data.launchdetail.DetailPagingResponse
import com.example.spacexclient.data.launches.LaunchesBody
import com.example.spacexclient.data.launches.PagingResponse
import com.example.spacexclient.data.launchdetail.BodyCrew
import com.example.spacexclient.data.launchdetail.BodyDetail
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