package com.example.spacexclient.ui.launchdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.spacexclient.R
import com.example.spacexclient.common.UiListState
import com.example.spacexclient.common.UiState
import com.example.spacexclient.data.launchdetail.Crew
import com.example.spacexclient.data.launches.QueryLaunches
import com.example.spacexclient.ui.components.ErrorScreen
import com.example.spacexclient.ui.components.LoadingScreen

@Composable
fun LaunchDetailScreen() {
    val viewModel: LaunchDetailViewModel = hiltViewModel()
    val state = viewModel.state
    val detailState = viewModel.detailState
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        when (detailState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Success -> {
                LaunchDetail(launch = detailState.data)
            }
            is UiState.Error -> ErrorScreen(
                uiError = detailState.uiError,
                onRefresh = viewModel::getDetails
            )
        }
        when (state) {
            is UiListState.Loading -> LoadingScreen()
            is UiListState.Success -> CrewList(crew = state.data)
            is UiListState.Error -> ErrorScreen(
                uiError = state.uiError,
                onRefresh = viewModel::getCrew
            )
        }
    }
}

@Composable
fun LaunchDetail(launch: QueryLaunches) {
    val flight = launch.cores.first().flight

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .align(CenterHorizontally),
            painter = rememberAsyncImagePainter(
                model = launch.links.patch.large
                    ?: "https://upload.wikimedia.org/wikipedia/commons/4/4f/No_%28single%29_logo.png"
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = launch.name,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (flight != null) {
                stringResource(id = R.string.flights_number, flight)
            } else {
                stringResource(id = R.string.flights_number, stringResource(id = R.string.no_data))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (launch.success == null) {
                stringResource(id = R.string.no_data)
            } else {
                stringResource(
                    id = R.string.status, launch.success
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (launch.details != null) {
                stringResource(id = R.string.details, launch.details)
            } else {
                stringResource(id = R.string.no_data)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.date_of_launch, launch.dateUtc),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun CrewList(crew: List<Crew>) {
    Text(
        text = stringResource(id = R.string.crew_list),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(start = 14.dp)
    )
    LazyRow {
        items(crew) { crew ->
            Column(
                Modifier.padding(14.dp)
            ) {
                CrewLaunch(crew = crew)
            }
        }
    }
}

@Composable
fun CrewLaunch(crew: Crew) {
    Column {
        Text(
            text = crew.name,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = crew.status)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = crew.agency)
    }
}