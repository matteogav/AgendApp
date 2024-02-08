package com.matteogav.agendapp.ui.composables.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.matteogav.agendapp.R
import com.matteogav.agendapp.utils.bitmapDescriptorFromVector

@Composable
fun MapDetailView(latitude: Double, longitude: Double) {
    val location = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 14f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(end = 15.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = location),
            icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.ic_map_marker)
        )
    }
}