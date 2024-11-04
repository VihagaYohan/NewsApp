package com.techtribeservices.jetnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.techtribeservices.jetnews.presentation.home.HomeScreen
import com.techtribeservices.jetnews.presentation.news_details.NewsDetailsScreen
import com.techtribeservices.jetnews.ui.theme.JetNewsTheme
import com.techtribeservices.jetnews.utils.NavRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            JetNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar {
                            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                            bottomNavItems.forEach {
                                NavigationBarItem(
                                    icon = {it.icon},
                                    label = {it.title},
                                    selected = currentRoute == it.route,
                                    onClick = {
                                        navController.navigate(it.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable(NavScreen.Home.route) {
                                HomeScreen(modifier = Modifier.padding(innerPadding),
                                    navController = navController)
                            }
                            composable("/details/news={news}") {
                                val newsJson = it.arguments?.getString("news")
                                val news = NavRoute.getNewsFromRoute(newsJson!!)
                                NewsDetailsScreen(navController = navController, news = news)
                            }
                            composable(NavScreen.Bookmarks.route) {
                                HomeScreen(modifier = Modifier.padding(innerPadding),
                                    navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class NavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Home: NavScreen("/home", Icons.Default.Home, "Home")
    object Bookmarks: NavScreen("/home", Icons.Default.Favorite, "Bookmarks")
}

val bottomNavItems = listOf(
    NavScreen.Home,
    NavScreen.Bookmarks
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNewsTheme {

    }
}