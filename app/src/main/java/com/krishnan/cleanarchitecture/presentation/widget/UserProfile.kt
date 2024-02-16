package com.krishnan.cleanarchitecture.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.krishnan.cleanarchitecture.domain.model.User

@Composable
fun UserProfile(user: User, deleteUser: (id: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(18.dp, 6.dp)
            .fillMaxWidth()
            .height(110.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Image(
                imageVector = Icons.Filled.Person,
                contentDescription = user.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black
                )
                Text(
                    text = user.mobile,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    color = Color.Black
                )
                Text(
                    text = user.gender,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    color = Color.Black
                )
            }
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .padding(3.dp)
                    .size(30.dp)
                    .padding(3.dp)
                    .align(Alignment.Bottom)
                    .clickable { deleteUser(user.id) },
            )
        }
    }
}