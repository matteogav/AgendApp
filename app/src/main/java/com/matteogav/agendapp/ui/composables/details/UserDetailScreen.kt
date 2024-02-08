package com.matteogav.agendapp.ui.composables.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matteogav.agendapp.R
import com.matteogav.agendapp.domain.models.Gender
import com.matteogav.agendapp.domain.models.User
import com.matteogav.agendapp.ui.composables.ToolbarView
import com.matteogav.agendapp.ui.utils.theme.AgendAppTheme

sealed class DetailItem {
    data class TextDetail(val title: String, val detail: String, val iconId: Int)
    data class MapDetail(val title: String, val latitude: String, val longitude: String)
}

@Composable
fun UserDetailScreen(user: User, navController: NavController) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(Unit) {
        systemUiController.setStatusBarColor(color = Color.Transparent, false)
        onDispose {
            systemUiController.setStatusBarColor(color = Color.White, true)
        }
    }

    AgendAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            color = Color.White
        ) {
            if(user.name != null && user.email != null && user.gender != null &&
                user.registrationDate != null && user.cell != null &&
                user.latitude != null && user.longitude != null
                ) {

                val details = listOf(
                    DetailItem.TextDetail(
                        stringResource(R.string.detail_name_title),
                        user.name,
                        R.drawable.ic_user
                    ),
                    DetailItem.TextDetail(
                        stringResource(R.string.detail_email_title),
                        user.email,
                        R.drawable.ic_email
                    ),
                    if(user.gender == Gender.Female)
                        DetailItem.TextDetail(
                            stringResource(R.string.detail_genre_title),
                            stringResource(R.string.detail_female),
                            R.drawable.ic_gender_woman
                        )
                    else
                        DetailItem.TextDetail(
                            stringResource(R.string.detail_genre_title),
                            stringResource(R.string.detail_male),
                            R.drawable.ic_gender_woman
                        ),
                    DetailItem.TextDetail(
                        stringResource(R.string.detail_registerdate_title),
                        user.registrationDate,
                        R.drawable.ic_date
                    ),
                    DetailItem.TextDetail(
                        stringResource(R.string.detail_cell_title),
                        user.cell,
                        R.drawable.ic_phone
                    ),
                    DetailItem.MapDetail(
                        stringResource(R.string.detail_direction_title),
                        user.latitude,
                        user.longitude
                    )
                )
                Column (
                    verticalArrangement = Arrangement.spacedBy(70.dp)
                ) {
                    Box {
                        UserDetailHeader(user.image)
                        ToolbarView("${user.name}", Color.White, 0.1f) {
                            navController.popBackStack()
                        }
                    }
                    LazyColumn {
                        items(details) { item ->
                            when(item) {
                                is DetailItem.TextDetail -> {
                                    UserDetailItem(item.title, item.detail, item.iconId)
                                }
                                is DetailItem.MapDetail -> {
                                    Column (
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier
                                            .padding(start = 72.dp)
                                            .padding(vertical = 10.dp)
                                    ) {
                                        Text(
                                            text = item.title,
                                            fontSize = 11.sp,
                                            color = Color.Gray
                                        )
                                        MapDetailView(user.latitude.toDouble(), user.longitude.toDouble())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}