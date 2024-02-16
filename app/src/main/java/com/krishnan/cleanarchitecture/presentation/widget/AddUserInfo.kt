package com.krishnan.cleanarchitecture.presentation.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.krishnan.cleanarchitecture.R
import com.krishnan.cleanarchitecture.domain.model.User
import com.krishnan.cleanarchitecture.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserInfo(onclick: (User?) -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var mExpanded by remember {
        mutableStateOf(false)
    }
    var mSelectedText by remember {
        mutableStateOf("")
    }
    val genderList = listOf<String>("Male", "Female", "Others")
    val modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { onclick(null) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { value -> name = value },
                label = { Text(text = getString(context, R.string.enter_the_name)) },
                maxLines = 1,
                isError = name.isEmpty(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = modifier
            )
            OutlinedTextField(
                value = email,
                onValueChange = { value -> email = value },
                label = { Text(text = getString(context, R.string.enter_the_email_id)) },
                maxLines = 1,
                isError = email.isEmpty() || Constants.isEmailValid(email).not(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = modifier
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { value -> phone = value },
                label = {
                    Text(
                        text = getString(context, R.string.enter_the_phone_number),
                        color = if (phone.isEmpty()) Color.Red.copy(alpha = 0.3f) else Color.Gray
                    )
                },
                maxLines = 1,
                isError = phone.isEmpty() || phone.length != 10,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = modifier,
            )


            Column(Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = OutlinedTextFieldDefaults.MinWidth,
                            minHeight = OutlinedTextFieldDefaults.MinHeight
                        )
                        .border(
                            1.dp,
                            if (mSelectedText.isEmpty()) Color.Red.copy(alpha = 0.3f) else Color.Gray
                        )
                        .clickable { mExpanded = mExpanded.not() }
                        .padding(10.dp),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = mSelectedText.ifEmpty { "Select Gender" },
                        color = if (mSelectedText.isEmpty()) Color.Red.copy(alpha = 0.3f) else Color.Gray
                    )
                }

                DropdownMenu(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                ) {
                    genderList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            mSelectedText = label
                            mExpanded = false
                        }, text = {
                            Text(text = label)
                        })
                    }
                }
            }
            Button(
                onClick = {
                    if (name.isNotEmpty() && phone.isNotEmpty() && phone.length == 10 && Constants.isEmailValid(
                            email
                        ) && mSelectedText.isNotEmpty()
                    ) {
                        val user = User(
                            id = "",
                            name = name,
                            email = email,
                            mobile = phone,
                            gender = mSelectedText
                        )
                        onclick(user)
                    }
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = getString(context, R.string.save))
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}