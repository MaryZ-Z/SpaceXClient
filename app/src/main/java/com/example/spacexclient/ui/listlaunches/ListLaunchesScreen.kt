package com.example.spacexclient.ui.listlaunches

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberAsyncImagePainter
import com.example.spacexclient.common.UiError
import com.example.spacexclient.data.launches.QueryLaunches
import com.example.spacexclient.navigation.Screen
import com.example.spacexclient.ui.components.ErrorScreen
import com.example.spacexclient.ui.components.LoadingScreen
import com.example.spacexclient.R

@Composable
fun ListLaunchesScreen(navigate: (String) -> Unit) {
    val viewModel: ListLaunchesViewModel = hiltViewModel()
    val pager = viewModel.flow.collectAsLazyPagingItems()

    Column {
        when (val state = pager.loadState.refresh) {
            LoadState.Loading -> {
                LoadingScreen()
            }
            is LoadState.Error -> {
                ErrorScreen(
                    uiError = UiError.common(state.error),
                    onRefresh = viewModel::getLaunches
                )
            }
            else -> {
                LazyColumn {
                    itemsIndexed(pager) { index, item ->
                        item?.let {
                            LaunchItem(
                                launch = item,
                                onLaunchClick = {
                                    navigate(Screen.Detail.navigate(it.id, it.flightNumber))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchItem(launch: QueryLaunches, onLaunchClick: (QueryLaunches) -> Unit) {
    val flight = launch.cores.first().flight

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLaunchClick.invoke(launch) }
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = rememberAsyncImagePainter(
                model = launch.links.patch.small
                    ?: "https://upload.wikimedia.org/wikipedia/commons/4/4f/No_%28single%29_logo.png"
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                maxLines = 2,
                text = launch.name,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (flight != null) {
                    stringResource(id = R.string.flights_number, flight)
                } else {
                    stringResource(
                        id = R.string.flights_number,
                        stringResource(id = R.string.no_data)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.date_of_launch, launch.dateUtc),
                style = MaterialTheme.typography.body2

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
        }
    }
}