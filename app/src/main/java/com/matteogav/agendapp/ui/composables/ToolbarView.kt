package com.matteogav.agendapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matteogav.agendapp.R
import com.matteogav.agendapp.utils.customShadow

@Composable
fun ToolbarView(title: String, color: Color, shadowAlpha: Float? = null, onBackClick: (() -> Unit)? = null) {
    Row (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .statusBarsPadding()
            .customShadow(
                color = Color.White.copy(shadowAlpha ?: 0f),
                blurRadius = 10.dp,
                borderRadius = 5.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = { onBackClick?.invoke() },
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                tint = color,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = title.uppercase(),
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color,
        )
        IconButton(
            onClick = {  },
            ) {
            Icon(
                painter = painterResource(R.drawable.ic_menu),
                tint = color,
                contentDescription = null
            )
        }
    }
}