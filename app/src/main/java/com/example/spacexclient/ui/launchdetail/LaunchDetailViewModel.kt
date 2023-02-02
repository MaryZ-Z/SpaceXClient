package com.example.spacexclient.ui.launchdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexclient.common.UiError
import com.example.spacexclient.common.UiListState
import com.example.spacexclient.common.UiState
import com.example.spacexclient.data.launches.QueryLaunches
import com.example.spacexclient.data.launchdetail.*
import com.example.spacexclient.data.repository.LaunchesRepository
import com.example.spacexclient.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf<UiListState<Crew>>(UiListState.Loading)
        private set
    var detailState by mutableStateOf<UiState<QueryLaunches>>(UiState.Loading)
        private set
    private val launchId = savedStateHandle.get<String>(Screen.Detail.LAUNCH_ID) ?: ""
    private val flightNumber = savedStateHandle.get<String>(Screen.Detail.FLIGHT_NUMBER) ?: ""
    private var searchJob: Job? = null
    private var detailsJob: Job? = null

    init {
        getDetails()
        getCrew()
    }

    fun getCrew() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            state = UiListState.Loading
            state = try {
                val body = BodyCrew(QueryCrew(launchId))
                val items = launchesRepository.getCrew(body).docs
                UiListState.Success(items)
            } catch (e: Exception) {
                UiListState.Error(uiError = UiError.common(e))
            }
        }
    }

    fun getDetails() {
        detailsJob?.cancel()
        detailsJob = viewModelScope.launch {
            detailState = UiState.Loading
            detailState = try {
                val body = BodyDetail(QueryDetail(flightNumber))
                val items = launchesRepository.getDetails(body).docs.first()
                UiState.Success(items)
            } catch (e: Exception) {
                UiState.Error(uiError = UiError.common(e))
            }
        }
    }
}