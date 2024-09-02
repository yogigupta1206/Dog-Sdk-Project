package com.yogigupta1206.dogsdkproject.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yogigupta1206.dogsdkproject.presentation.dogs_list.DogsListScreen
import com.yogigupta1206.dogsdkproject.presentation.home.HomeScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomePage.route,
        modifier = Modifier
    ) {

        composable(Screens.HomePage.route) {
            HomeScreen(onNavigateToDogsList = { navController.onNavigateToDogsListPage(it) })
        }



        composable(
            route = Screens.DogsListPage.route + "?n={n}",
            arguments = listOf(
                navArgument("n") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) {
            DogsListScreen(onNavigateBack = { navController.popBackStack() })
        }


    }
}

fun NavController.onNavigateToDogsListPage(n: Int) = navigate(Screens.DogsListPage.route + "?n=$n")

