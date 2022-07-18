package com.example.gljcdemo.homescreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gljcdemo.*
import com.example.gljcdemo.R
import com.example.gljcdemo.accountscreen.AccountScreen
import com.example.gljcdemo.accountscreen.SanMingAccountScreen
import com.example.gljcdemo.login.LoginViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SanMingHomeScreen(navController: NavController,viewModel: LoginViewModel) {

    val bottomData = listOf("首页", "报警", "详情", "我的")
    val iconList = listOf(Icons.Default.Home,
        Icons.Default.Notifications,
        Icons.Default.Analytics,
        Icons.Default.AccountBox)

    var bottomNavigationCurrentIndex by remember {
        mutableStateOf(0)
    }

//    主框架
    Scaffold(
        // backgroundColor=Color.Gray,               /*body背景*/

//        顶部状态栏
        topBar = {

            TopAppBar(

                title = {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.nflg_logo_en
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "骨料识别系统", color = Color.Black , textAlign = TextAlign.Center)
                            Text(text = "用户: 三明华鑫", color = Color.Black , textAlign = TextAlign.Center , fontSize = 12.sp)
                        }

                    }

                },

                backgroundColor = Color(0xFF448AFF),
//              backgroundColor= Color.LightGray,
//                navigationIcon = {
//                    Row(modifier =Modifier) {
//                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
////                        Text(text = "iii", color = Color.Red)
//                    }
//
//                },

//                actions = {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
////                    Text(text = "iii", color = Color.Red)
//                }
            )
        },


//        底部栏
        bottomBar = {

            BottomNavigation(modifier = Modifier, backgroundColor = Color.White) {
                bottomData.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = bottomNavigationCurrentIndex == index,
                        onClick = { bottomNavigationCurrentIndex = index },
                        icon = {
                            Box(
                            ) {
                                Icon(
                                    imageVector = iconList[index],
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(item) })
                }
            }

        },


        ) {
//        scaffold框架内部内容


//        Column (){
//            HomeBodyContent()
//            Button(onClick = {
//                onNavigateToOther()
//            }) {
//                Text(text = "Navigate to Alert screen from Column")
//            }
//
//
//        }

        when (bottomNavigationCurrentIndex) {
            0 -> SanMingHomeBodyContent()
            1 -> AlertBodyContent()
            2 -> DetailBodyContent()
            3 -> SanMingAccountScreen(navController = navController,viewModel)
        }

    }

}





