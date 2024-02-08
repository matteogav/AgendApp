package com.matteogav.agendapp.ui.composables.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matteogav.agendapp.R
import com.matteogav.agendapp.ui.NavigationRoute
import com.matteogav.agendapp.ui.composables.SearchBar
import com.matteogav.agendapp.ui.composables.ToolbarView
import com.matteogav.agendapp.ui.utils.theme.AgendAppTheme
import com.matteogav.agendapp.ui.viewmodels.UsersViewModel
import com.matteogav.agendapp.utils.gzipCompress
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder

@Composable
fun UsersListScreen(navController: NavController) {
    val usersViewModel = getViewModel<UsersViewModel>()
    val users = usersViewModel.users.collectAsLazyPagingItems()

    var searchText = remember { mutableStateOf(TextFieldValue()) }

    val systemUiController = rememberSystemUiController()
    DisposableEffect(Unit) {
        systemUiController.setStatusBarColor(color = Color.White, true)
        onDispose {
            systemUiController.setStatusBarColor(color = Color.Transparent, false)
        }
    }
    AgendAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            color = Color.White
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ToolbarView(stringResource(R.string.user_list_title), Color.Black)
                SearchBar(searchText,
                    "Buscar usuario...",
                    R.drawable.ic_search
                )
                LaunchedEffect(searchText.value.text) {
                    usersViewModel.searchUsers(searchText.value.text)
                }
                LazyColumn {
                    items(users.itemCount) { index ->
                        users[index]?.let {
                            UserListItem(it) {
                                val userString = Json.encodeToString(it)
                                val encodedJson = URLEncoder.encode(userString.gzipCompress(), "UTF-8")
                                navController.navigate("${NavigationRoute.USER_DETAIL.destination}/{$encodedJson}")
                            }
                        }
                    }
                }
            }
        }
    }
}