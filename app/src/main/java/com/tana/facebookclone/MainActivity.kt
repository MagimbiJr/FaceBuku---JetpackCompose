package com.tana.facebookclone

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tana.facebookclone.presentation.navigation.FBNavHost
import com.tana.facebookclone.presentation.theme.FacebookCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val scaffoldState = rememberScaffoldState()
            val scrollState = rememberScrollState()
            val scope = rememberCoroutineScope()
            val galleryPermission = rememberPermissionState(
                permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            FacebookCloneTheme {
                FBNavHost(
                    navHostController = navHostController,
                    systemUiController = systemUiController,
                    galleryPermission = galleryPermission,
                    scaffoldState = scaffoldState,
                    scrollState = scrollState,
                    scope = scope
                )
            }
        }
    }
}