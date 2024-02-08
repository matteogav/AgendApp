package com.matteogav.agendapp.ui.composables.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserDetailItem(title: String, detail: String, iconId: Int) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Inside
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Text(
                    text = detail,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 72.dp)
                .background(Color.LightGray)
                .align(Alignment.BottomEnd)
        )
    }
}