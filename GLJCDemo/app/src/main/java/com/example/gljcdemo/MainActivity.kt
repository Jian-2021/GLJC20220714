package com.example.gljcdemo

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gljcdemo.ui.theme.GLJCDemoTheme

//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Text

import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gljcdemo.accountscreen.MainAccountScreen
import com.example.gljcdemo.homescreen.HomeScreen
import com.example.gljcdemo.homescreen.NanAnHomeScreen
import com.example.gljcdemo.homescreen.SanMingHomeScreen
import com.example.gljcdemo.login.Login
import com.example.gljcdemo.login.LoginViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT     ////////////设置竖屏
        super.onCreate(savedInstanceState)

        //////////////创建实例
        val loginDataViewModel by viewModels<LoginViewModel>()

        setContent {
            GLJCDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.White) {
                    Navigation(loginDataViewModel)
                }
            }
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GLJCDemoTheme {
//        Navigation()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(viewModel: LoginViewModel) {


    val navController = rememberNavController()       /*官方*/

    NavHost(
        navController = navController,             /*实例化*/
//        startDestination = Screen.Splash.route    /*起始页*/
        startDestination = Screen.Splash.route
    ) {


        /*------------------------------------------------------*/
        /*启动过场界面*/

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController,viewModel)
        }

        /*登录界面*/
        composable(route = Screen.Login.route) {
            Login(navController,viewModel)
//            Screen.Login
        }
        /*------------------------------------------------------*/
        /*第一个界面   首页*/
        composable(
            route = Screen.Home.route /*路由名称*/
        ) {
            /*界面*/
            HomeScreen(navController)
//            {
////                navController.navigate(Screen.Alert.route)
//                navController.popBackStack(             /*返回*/
//                    com.example.gljcdemo.Screen.NanAn.route,
//                    inclusive = false
//                )
//
//            }
        }

        composable(route = Screen.NanAn.route) {
            NanAnHomeScreen(navController)

        }


        composable(route = Screen.SanMing.route) {
            SanMingHomeScreen(navController)
        }

        /*------------------------------------------------------*/
        /*第二个界面      报警界面*/
        composable(
            route = Screen.Alert.route /*路由名称*/
        ) {
            /*界面*/
            AlertScreen() {
                navController.navigate(Screen.Detail.route)
            }
        }

        /*------------------------------------------------*/
        /*第三个界面       详情界面*/
        composable(
            route = Screen.Detail.route /*路由名称*/
        ) {
            /*界面*/
            DetailScreen() {
                navController.navigate(Screen.Alert.route)
            }


        }
        /*-------------------------------------------------*/

        /*第4个界面       我的界面*/
        composable(
            route = Screen.MainAccount.route /*路由名称*/
        ) {
            /*界面*/
            MainAccountScreen(navController = navController)

        }
        /*-------------------------------------------------*/


    }
}


sealed class Screen(val route: String) {

    object Splash : Screen("Splash")
    object Login : Screen("Login")
    object Home : Screen("Home")
    object NanAn : Screen("NanAn")
    object SanMing : Screen("SanMing")
    object Alert : Screen("Alert")
    object Detail : Screen("Detail")
    object MainAccount : Screen("MainAccount")


}