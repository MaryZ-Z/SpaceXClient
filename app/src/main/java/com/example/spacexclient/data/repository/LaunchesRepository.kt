package com.example.spacexclient.data.repository

import com.example.spacexclient.data.launches.LaunchesBody
import com.example.spacexclient.data.launchdetail.BodyCrew
import com.example.spacexclient.data.launchdetail.BodyDetail
import com.example.spacexclient.restapi.LaunchesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchesRepository @Inject constructor(
    private val launchesService: LaunchesService
) {
    suspend fun getLaunches(body: LaunchesBody) = launchesService.getLaunches(body)

    suspend fun getDetails(body: BodyDetail) = launchesService.getDetails(body)

    suspend fun getCrew(body: BodyCrew) = launchesService.getCrew(body)
}