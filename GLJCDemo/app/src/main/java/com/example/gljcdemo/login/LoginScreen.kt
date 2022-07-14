package com.example.gljcdemo.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gljcdemo.R


@Composable
fun LoginScreen(navController: NavController) {
    var inputIDValue = remember {
        mutableStateOf("")
    }
    val inputPasswordValue = remember {
        mutableStateOf("")
    }
    val id1 = "111111"
    val id2 = "222222"
    val password1 = "111111"
    val password2 = "222222"
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF),
                                Color(0xFFF3EAE7)
                            ),
                            radius = 415f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(280.dp)
                        .offset(y = (-20.dp)),
                    painter = painterResource(id = R.drawable.nflg_logo_login),
                    contentDescription = "Background Image"
                )
            }

            Card(
                modifier = Modifier
                    .offset(y = -20.dp)
                    .width(290.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = 15.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 30.dp, bottom = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "骨料监测系统",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 16.dp))

//                    CustomInputField(inputValue = inputValueID, type = "uid")
//                    CustomInputField(inputValue = inputValuePass, type = "password")

//                    账号输入
                    IdInputField(inputValue = inputIDValue, type = "uid")
//                    密码输入
                    PasswordInputField(inputValue = inputPasswordValue, type = "password")

                }
            }

//登录按钮
//            GradientButton(
//                modifier = Modifier.offset(y = -50.dp),
//                text = "登录",
//                textColor = Color.White,
//                gradient = Brush.horizontalGradient(
//                    listOf(
//                        Color(0xFFC4A0F4),
//                        Color(0xFFCF4CB9),
//                        Color(0xFFE60B41)
//                    )
//                )
//
//            ) {
//                navController.navigate(Screen.Home.route)
//
//            }
//            Button(
//                onClick = {
//                    localFocusManager.clearFocus()
//                    if (id1 == inputIDValue){
//                        val toast = Toast
//                            .makeText(context, "1111", Toast.LENGTH_SHORT)
//                        toast.setGravity(Gravity.CENTER, 0, 0)
//                        toast.show()
//                    } else {
//                        val toast = Toast
//                            .makeText(context, "2222", Toast.LENGTH_SHORT)
//                        toast.setGravity(Gravity.CENTER, 0, 0)
//                        toast.show()
//
//
//                    }
//
//                },
//                Modifier
//                    .fillMaxWidth(),
//                enabled = passwordValue.isNotEmpty() && accountValue.isNotEmpty()
//            ) {
//                Text(text = "登录", fontSize = 20.sp)
//            }





        }

    }
}



