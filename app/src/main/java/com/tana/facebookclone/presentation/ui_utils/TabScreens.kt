package com.tana.facebookclone.presentation.ui_utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.tana.facebookclone.R
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.presentation.home.tab_screens.*
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.CoroutineScope

//typealias ComposableFun = @Composable () -> Unit
sealed class TabScreens(val label: String, val icon: Int, val screen: @Composable () -> Unit) {
    @RequiresApi(Build.VERSION_CODES.O)
    data class Home(
        val navHostController: NavHostController,
        val posts: List<Post>,
        val comments: List<Comment>,
        val user: User?,
        ) : TabScreens(
        label = "Home",
        icon = R.drawable.home_icon,
        screen = {
            FeedsScreen(
                navHostController = navHostController,
                posts = posts,
                user = user,
                comments = comments
            )
        }
    )
    object Friends : TabScreens(label = "Friends", icon = R.drawable.people_icon, screen = { FriendsScreen() })
    object Watch : TabScreens(label = "Watch", icon = R.drawable.video_outline_icon, screen = { VideosScreen() })
    @RequiresApi(Build.VERSION_CODES.O)
    class Profile(
        scope: CoroutineScope,
        onNavigateToUpdateCover: (AppEvents.Navigate) -> Unit,
        onNavigateToEditProfile: (AppEvents.Navigate) -> Unit,
        scaffoldState: ScaffoldState
    ) : TabScreens(
        label = "Profile",
        icon = R.drawable.person_outline_icon,
        screen = {
            ProfileScreen(
                scope = scope,
                onNavigateToUpdateCover = onNavigateToUpdateCover,
                onNavigateToEditProfile = onNavigateToEditProfile,
                scaffoldState = scaffoldState
            )
        }
    )
    object Notification : TabScreens(label = "Notification", icon = R.drawable.alarm_bell_outlin_icon, screen = { NotificationScreen() })
    object Menu : TabScreens(label = "Menu", icon = R.drawable.menu_icon, screen = { MenuScreen() })
}