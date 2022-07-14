package com.example.gljcdemo.homescreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gljcdemo.*
import com.example.gljcdemo.R
import com.example.gljcdemo.accountscreen.MainAccountScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
//fun HomeScreen(onNavigateToOther: () -> Unit) {
fun HomeScreen(navController: NavController) {

    val bottomData = listOf("首页", "报警", "详情", "我的")
    val iconList = listOf(Icons.Default.Home,
        Icons.Default.Notifications,
        Icons.Default.Analytics,
        Icons.Default.AccountBox)

    var bottomNavigationCurrentIndex by remember {
        mutableStateOf(0)
    }
//    val navController = rememberNavController()       /*官方*/
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
                            Text(text = "用户: 管理员", color = Color.Black , textAlign = TextAlign.Center , fontSize = 12.sp)
                        }

                    }

                },

                backgroundColor = Color(0xFF448AFF),
//              backgroundColor= Color.LightGray,

//                左边导航按钮
//                navigationIcon = {
//                    Row(modifier =Modifier) {
//                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
////                        Text(text = "iii", color = Color.Red)
//                    }
//
//                },
//                右边操作按钮
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
            0 -> HomeBodyContent()
            1 -> AlertBodyContent()
            2 -> DetailBodyContent()
//            3 -> AccountScreen()
            3 -> MainAccountScreen(navController)

        }
        BackHandler(enabled = true) {
            Log.e("tag","返回键被点击")
            bottomNavigationCurrentIndex =0
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen() {}
}

