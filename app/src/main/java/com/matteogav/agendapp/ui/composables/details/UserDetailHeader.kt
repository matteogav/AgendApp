package com.matteogav.agendapp.ui.composables.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.matteogav.agendapp.R

@Composable
fun UserDetailHeader(userImageURL: String? = null) {
    Box {
        Image(
            painter = painterResource(R.drawable.detail_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight(0.25f)
                .fillMaxWidth()
        )
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .scale(Scale.FIT)
                    .data(userImageURL)
                    .placeholder(R.drawable.ic_user)
                    .build(),
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .offset(y=40.dp)
                .padding(start = 15.dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, Color.White, CircleShape)
                .align(Alignment.BottomStart)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp)
                .offset(y = 45.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}