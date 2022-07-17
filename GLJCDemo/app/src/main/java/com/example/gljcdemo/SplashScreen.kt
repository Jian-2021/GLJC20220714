package com.example.gljcdemo


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gljcdemo.login.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,viewModel: LoginViewModel) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        queryAutoLogin(context, viewModel)    /////////////////读取本地数据库的自动登录状态，并传入viewModel
        delay(800)
        getLoginDataAndSave(context, viewModel)      ////////////////获取服务器上的账号密码
        delay(800)
        queryNetLoginDataStore(context, viewModel)      ////////////////这里不能查询总库
        delay(800)
        queryLocalLoginDataStore(context, viewModel)
        delay(800)
        if(viewModel.autoLogin.value){
            ////////////跳到管理员界面
//                            navController.navigate(Screen.Home.route)
            navController.navigate(com.example.gljcdemo.Screen.Home.route){
                popUpTo(route = Screen.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(route = Screen.Login.route) {
                popUpTo(route = Screen.Splash.route) { inclusive = true }
            }

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            Image(
                painter = painterResource(
                    id = R.drawable.nflg_logo_login
                ),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

    }


}



