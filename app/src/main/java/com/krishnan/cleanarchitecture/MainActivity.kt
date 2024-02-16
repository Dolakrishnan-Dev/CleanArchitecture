package com.krishnan.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.krishnan.cleanarchitecture.presentation.viewmodal.UserViewModal
import com.krishnan.cleanarchitecture.presentation.widget.AddUserInfo
import com.krishnan.cleanarchitecture.presentation.widget.EmptyUserProfile
import com.krishnan.cleanarchitecture.presentation.widget.ShimmerEffect
import com.krishnan.cleanarchitecture.presentation.widget.UserProfile
import com.krishnan.cleanarchitecture.ui.theme.BackGroundColor
import com.krishnan.cleanarchitecture.ui.theme.CleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModal by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    var showSheet by remember { mutableStateOf(false) }
                    LaunchedEffect(key1 = true) {
                        viewModel.init()
                    }
                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text(text = "User Profile", color = Color.Black) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                        )
                    },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { showSheet = true },
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add"
                                )
                            }
                        }) { padding ->

                        ShimmerEffect(
                            isLoading = viewModel.showShimmer.collectAsStateWithLifecycle().value,
                            contentAfterLoading = {
                                val userInfo = viewModel.userInfo.collectAsStateWithLifecycle()
                                userInfo.value?.let {
                                    if (it.isNotEmpty()) {
                                        LazyColumn(
                                            modifier = Modifier
                                                .background(color = BackGroundColor)
                                                .fillMaxSize()
                                                .padding(padding)
                                        ) {
                                            items(it) { profile ->
                                                UserProfile(user = profile) { userId ->
                                                    viewModel.deleteUser(userId)
                                                }
                                            }
                                        }

                                    } else {
                                        EmptyUserProfile()
                                    }
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding)
                        )
                    }

                    if (showSheet) {
                        AddUserInfo { userInfo ->
                            showSheet = false
                            userInfo?.let {
                                viewModel.insertUserInfo(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
