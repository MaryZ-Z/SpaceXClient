package com.example.testcaseci.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String
) {
    object LaunchesList : Screen(
        route = "launches_list"
    )

    class Detail : Screen(
        route = route
    ) {
        companion object {
            private const val ROUTE = "details"
            const val LAUNCH_ID = "launchId"
            const val FLIGHT_NUMBER = "flightNumber"
            const val route = "$ROUTE/{$LAUNCH_ID}/{$FLIGHT_NUMBER}"
            val arguments = listOf(
                navArgument(LAUNCH_ID) { type = NavType.StringType },
                navArgument(FLIGHT_NUMBER) { type = NavType.StringType }
            )

            fun navigate(launchId: String, flightNumber: String) = "$ROUTE/$launchId/$flightNumber"
        }
    }
}
