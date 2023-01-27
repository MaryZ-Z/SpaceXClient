package com.example.testcaseci.data.repository

import com.example.testcaseci.data.launches.LaunchesBody
import com.example.testcaseci.data.launchdetail.BodyCrew
import com.example.testcaseci.data.launchdetail.BodyDetail
import com.example.testcaseci.restapi.LaunchesService
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