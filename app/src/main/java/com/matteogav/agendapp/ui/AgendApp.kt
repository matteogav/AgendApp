package com.matteogav.agendapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.maps.MapsInitializer
import com.matteogav.agendapp.di.repositoryModule
import com.matteogav.agendapp.di.useCaseModule
import com.matteogav.agendapp.di.viewModelModule
import com.matteogav.agendapp.domain.models.User
import com.matteogav.agendapp.ui.composables.details.UserDetailScreen
import com.matteogav.agendapp.ui.composables.list.UsersListScreen
import com.matteogav.agendapp.utils.decodeBase64AndDecompress
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AgendApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@AgendApp)
            modules(
                listOf(viewModelModule, useCaseModule, repositoryModule)
            )
        }

        setContent {
            MapsInitializer.initialize(LocalContext.current)

            val navController = rememberNavController()
            NavHost(navController, NavigationRoute.USERS_LIST.destination) {
                composable(NavigationRoute.USERS_LIST.destination) {
                    UsersListScreen(navController)
                }

                composable("${NavigationRoute.USER_DETAIL.destination}/{user}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType} )
                ) { backStackEntry ->
                    val userJSON = backStackEntry.arguments?.getString("user") ?: ""
                    val user = Json.decodeFromString<User>(userJSON.decodeBase64AndDecompress())
                    UserDetailScreen(user, navController)
                }
            }
        }
    }
}

enum class NavigationRoute(val destination: String) {
    USERS_LIST("users_list"),
    USER_DETAIL("user_details")
}