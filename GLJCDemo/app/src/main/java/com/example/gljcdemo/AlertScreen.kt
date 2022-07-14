package com.example.gljcdemo


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertScreen(onNavigateToDetail:  () -> Unit) {
    val bottomData = listOf("首页", "报警", "详情")
    val iconList = listOf(Icons.Default.Home,Icons.Default.Notifications,Icons.Default.Analytics)
    var bottomNavigationCurrentIndex by remember {
        mutableStateOf(1)
    }


//    主框架
    Scaffold(
        // backgroundColor=Color.Gray,               /*body背景*/

//        顶部状态栏
        topBar = {

            TopAppBar(title = { Text(text = "报警") },
//                backgroundColor= Color.LightGray,

                navigationIcon = {
                    Row(modifier = Modifier) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//                        Text(text = "iii", color = Color.Red)
                    }

                },

                actions = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                    Text(text = "iii", color = Color.Red)
                },
                backgroundColor= Color(0xFF40C4FF)
            )
        },


//        底部栏
        bottomBar = {

            BottomNavigation(modifier = Modifier,backgroundColor = Color.White) {
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
//        框架内部内容
        Column {
            AlertBodyContent()
            Button(onClick = {
                onNavigateToDetail()
            }) {
                Text(text = "Navigate to Detail screen from column")
            }
        }
        if (bottomNavigationCurrentIndex == 0){
            Text("这是首页")
        } else if (bottomNavigationCurrentIndex == 1){
            Text("这是报警界面")
        } else {
            Text("这是详情")
        }
    }


}

@Preview
@Composable
fun AlertScreenPreview() {
    AlertScreen{}
}

