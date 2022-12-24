package com.tana.facebookclone.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.facebookclone.presentation.add_post.AddPostScreen
import com.tana.facebookclone.presentation.comments.CommentsScreen
import com.tana.facebookclone.presentation.home.ui.HomeScreen
import com.tana.facebookclone.presentation.registration.signin.ui.SignInScreen
import com.tana.facebookclone.presentation.registration.signin.ui.keyboardAsState
import com.tana.facebookclone.presentation.registration.signup.ui.SignUpScreen
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun FBNavHost(
    navHostController: NavHostController,
    systemUiController: SystemUiController,
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
    scope: CoroutineScope,
) {
    val keyboardState by keyboardAsState()
    val focusManager = LocalFocusManager.current
    val keyboardOptions = KeyboardOptions.Default
    NavHost(navController = navHostController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                goToSignIn = {
                    navHostController.navigate(it.route) {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                },
                //navigate = {},
                navHostController = navHostController,
                systemUiController = systemUiController,
                scope = scope,
                scaffoldState = scaffoldState
            )
        }
        composable(route = "signin") {
            SignInScreen(
                navigateToHome = {
                    navHostController.navigate(it.route)
                },
                navigateToSignUp = {
                    navHostController.navigate(it.route)
                },
                systemUiController = systemUiController,
                focusManager = focusManager,
                scaffoldState = scaffoldState,
                keyboardState = keyboardState
            )
        }
        composable(route = "sign_up") {
            SignUpScreen(
                navigateToHome = { navHostController.navigate(it.route) },
                navigateToSignIn = {
                    navHostController.navigate(it.route)
                },
                focusManager = focusManager,
                keyboardState = keyboardState,
                scaffoldState = scaffoldState,
                scrollState = scrollState,
                systemUiController = systemUiController
            )
        }
        composable(route = "add_post") {
            AddPostScreen(
                scrollState = scrollState,
                navigate = {
                    navHostController.navigate(it.route)
                },
                popBack = {
                    navHostController.popBackStack()
                },
                scaffoldState = scaffoldState
            )
        }
        composable(
            route = "comments/{postId}",
        ) {
            CommentsScreen(
                scaffoldState = scaffoldState,
                keyboardState = keyboardState
            )
        }
        composable(route = "profile") {

        }
    }
}