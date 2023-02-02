package com.example.spacexclient.ui.listlaunches

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.spacexclient.common.UiError
import com.example.spacexclient.common.UiListState
import com.example.spacexclient.data.launches.*
import com.example.spacexclient.data.repository.LaunchesRepository
import com.example.spacexclient.paging.PagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLaunchesViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository
) : ViewModel() {
    var state by mutableStateOf<UiListState<QueryLaunches>>(UiListState.Loading)
        private set
    private var searchJob: Job? = null
    val flow = Pager(
        PagingConfig(pageSize = 10)
    ) {
        PagingSource(launchesRepository)
    }.flow
        .cachedIn(viewModelScope)

    init {
        getLaunches()
    }

    fun getLaunches() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            state = UiListState.Loading
            state = try {
                val body =
                    LaunchesBody(
                        query = DateUtc(FromToDate()),
                        options = Options("-date_utc", 1)
                    )
                val items = launchesRepository.getLaunches(body).docs
                UiListState.Success(items)
            } catch (e: Exception) {
                UiListState.Error(uiError = UiError.common(e))
            }
        }
    }
}