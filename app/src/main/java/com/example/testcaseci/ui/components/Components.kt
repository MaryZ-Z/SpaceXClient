package com.example.testcaseci.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testcaseci.R
import com.example.testcaseci.common.UiError
import com.example.testcaseci.navigation.Screen
import com.example.testcaseci.ui.launchdetail.LaunchDetailScreen
import com.example.testcaseci.ui.listlaunches.ListLaunchesScreen
import com.example.testcaseci.ui.theme.TestCaseCITheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestCaseICApp() {
    TestCaseCITheme {
        val navController = rememberNavController()

        Scaffold {
            TestCaseICNavHost(navController = navController)
        }
    }
}

@Composable
fun TestCaseICNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LaunchesList.route
    ) {
        composable(route = Screen.LaunchesList.route) {
            ListLaunchesScreen(navController::navigate)
        }
        composable(
            route = Screen.Detail.route,
            arguments = Screen.Detail.arguments
        ) {
            LaunchDetailScreen()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}

@Composable
fun ErrorScreen(uiError: UiError, onRefresh: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 32.dp))
        Text(
            text = stringResource(id = uiError.headerMessageRes),
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(
            text = stringResource(id = uiError.messageRes),
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(height = 64.dp))
        Button(
            onClick = onRefresh,
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.refresh_button),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 47.dp, vertical = 10.dp)
            )
        }
    }
}