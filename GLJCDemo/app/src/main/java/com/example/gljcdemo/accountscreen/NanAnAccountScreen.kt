package com.example.gljcdemo.accountscreen


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Update
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.gljcdemo.R
import com.example.gljcdemo.Screen

@Preview
@Composable
fun NanAnAccountScreenPreview() {
//    NanAnAccountScreen()
}

@Composable
fun NanAnAccountScreen(navController: NavController) {


//    val navController = rememberNavController()       /*官方*/

    var showDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
//    Image(
//        painter = painterResource(
//            id = R.drawable.background
//        ),
//        contentDescription = null,
//
//        modifier = Modifier.width(500.dp)
////        modifier = Modifier
////            .size(400.dp)
////            .clip(shape = RoundedCornerShape(50))
////            .border(
////                width = 2.dp,
////                color = Color.Gray,
////                shape = RoundedCornerShape(50)
////            ),
////        contentScale = ContentScale.Crop,
//
//    )

    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(
                id = R.drawable.background1
            ),
            contentDescription = null,

//            modifier = Modifier.width(500.dp)
//        modifier = Modifier
//            .size(400.dp)
//            .clip(shape = RoundedCornerShape(50))
//            .border(
//                width = 2.dp,
//                color = Color.Gray,
//                shape = RoundedCornerShape(50)
//            ),
//        contentScale = ContentScale.Crop,

        )

        Spacer(modifier = Modifier.padding(10.dp))

        ///头像和账号信息
        Card(
            modifier = Modifier
//                        .offset(y = -20.dp)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
//                .height(80.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Gray),
//                        elevation = 150.dp
        ) {

            Column {
                Spacer(modifier = Modifier.padding(5.dp))
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(
                            id = R.drawable.nflg_gljc
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(shape = RoundedCornerShape(50))
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(50)
                            ),
                        contentScale = ContentScale.Crop,

                        )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Column {
                        Text(text = "南安翔特")
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "ID: 2")

                    }

                }
                Spacer(modifier = Modifier.padding(5.dp))


            }


        }






        Spacer(modifier = Modifier.padding(10.dp))


        Button(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF5F2F2)

            )
        ) {

            Icon(imageVector = Icons.Default.Chat, contentDescription = null)
            Text(text = "意见反馈")

        }
        Spacer(modifier = Modifier.padding(5.dp))


        Button(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF5F2F2)

            )
        ) {

            Icon(imageVector = Icons.Default.MenuBook, contentDescription = null)
            Text(text = "关于骨料监测系统")

        }
        Spacer(modifier = Modifier.padding(5.dp))





        Button(onClick = {
//            showDialog = !showDialog

            Toast.makeText(context, "已是最新版本！", Toast.LENGTH_SHORT).show()


        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF5F2F2)

            )
        ) {

            Icon(imageVector = Icons.Default.Update, contentDescription = null)
            Text(text = "检查更新")
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = !showDialog }) {
                Box(
                    Modifier
                        .size(300.dp, 100.dp)
                        .background(Color.White)) {

                    Column(Modifier.fillMaxWidth()) {
                        Text(text = "提示", fontSize = 15.sp, textAlign = TextAlign.Center)
                        Text(text = "已是最新版本！", fontSize = 10.sp, textAlign = TextAlign.Center)
                    }

                }
            }
        }


        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {

            navController.navigate(Screen.Login.route)       //////////跳转到登录界面


        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFECD2D2),
                contentColor = Color.Red

            )
        ) {
            Text(text = "退出登录")
        }
    }




}

