package com.krishnan.cleanarchitecture.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.krishnan.cleanarchitecture.R

@Composable
fun EmptyUserProfile() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Text(
            text = getString(LocalContext.current, R.string.no_profiles_found),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp,
            color = Color.Black
        )
    }
}