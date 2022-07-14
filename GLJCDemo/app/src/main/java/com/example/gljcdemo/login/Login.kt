package com.example.gljcdemo.login


import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gljcdemo.R
import kotlin.system.exitProcess


@Composable
fun Login(navController: NavController) {




    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var accountValue by rememberSaveable { mutableStateOf("") }
        var passwordValue by rememberSaveable { mutableStateOf("") }

        val passwordFocusRequest = remember { FocusRequester() }
        val localFocusManager = LocalFocusManager.current
        val account1 = "1"
        val password1 = "1"
        val account2 = "2"
        val password2 = "2"
        val account3 = "3"
        val password3 = "3"
        val context = LocalContext.current
        var seePasswordToggle = remember { mutableStateOf(false) }






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
                            Color(0xFF40C4FF),
                            Color(0xFF448AFF)
                        ),
                        radius = 300f
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


//                输入账号
                OutlinedTextField(
                    value = accountValue,
                    onValueChange = { accountValue = it },
//                    placeholder = { Text(text = "账号", color = Color.Black) },
                    label = { Text(text = "账号", color = Color.Black) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Badge,
                            contentDescription = "Login"
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Email,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequest.requestFocus()
                    })
                )

                Spacer(modifier = Modifier.height(10.dp))


//                输入密码
                OutlinedTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
//                    placeholder = { Text(text = "密码") },
                    label = { Text(text = "密码", color = Color.Black) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = "Password"
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequest),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    visualTransformation = if (!seePasswordToggle.value) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (seePasswordToggle.value) Icons.Rounded.Visibility
                            else Icons.Rounded.VisibilityOff,
                            contentDescription = "Trailing Icon",
                            modifier = Modifier
                                .size(20.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                                .clickable { seePasswordToggle.value = !seePasswordToggle.value }
                        )
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        localFocusManager.clearFocus()

                    })
                )

                Spacer(modifier = Modifier.height(10.dp))


                ///////////////////////////////记住密码

                var rememberPassword by remember {
                    mutableStateOf(false)             ////////默认不记住密码
                }
                var autoLogin by remember {
                    mutableStateOf(false)         ///////////默认不自动登录
                }

//                if (rememberPassword == false) {
//                    autoLogin = false                  ///////////当不记住密码，就不自动登录
//
//                }

//                if (autoLogin == true){
//                    rememberPassword = true
//                }
//                if (rememberPassword ==false){
//                    autoLogin = false
//
//                }
                Row {
                    Text(text = "记住密码", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = rememberPassword, onCheckedChange = {
                        rememberPassword = it
                        Log.d("rememberPassword","$rememberPassword")

                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ))

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "自动登录", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = autoLogin, onCheckedChange = {
                        autoLogin = it
                        Log.d("autoLogin","$autoLogin")
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ))
                }





                Spacer(modifier = Modifier.height(10.dp))

//                登录按钮
                Button(
                    onClick = {


                        localFocusManager.clearFocus()

                        if (passwordValue.isEmpty() && accountValue.isEmpty()) {
                            val toast = Toast
                                .makeText(context, "请输入账号和密码", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                        } else if ((account1 == accountValue) && (password1 == passwordValue)) {
                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                            ////////////跳到管理员界面
//                            navController.navigate(Screen.Home.route)
                            navController.navigate(com.example.gljcdemo.Screen.Home.route)

                        } else if ((account2 == accountValue) && (password2 == passwordValue)) {

                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                            ////////////跳到南安界面
                            navController.navigate(com.example.gljcdemo.Screen.NanAn.route)
                        } else if ((account3 == accountValue) && (password3 == passwordValue)) {

                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                            ////////////跳到三明界面
                            navController.navigate(com.example.gljcdemo.Screen.SanMing.route)
                        } else {
                            val toast = Toast
                                .makeText(context, "账号或密码错误，请重新输入！", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                        }

                    },
                    Modifier
                        .fillMaxWidth(),
//                    enabled = passwordValue.isNotEmpty() && accountValue.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF448AFF),
                        contentColor = Color.Black,
                        disabledBackgroundColor = Color(0xFFBFC1C5),
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text(text = "登录", fontSize = 20.sp)
                }

            }
        }


    }

    /////////监听返回键
    BackHandler(enabled = true) {
        Log.e("tag", "返回键被点击")
        exitProcess(0)    ///////////退出软件
    }

}

//class LoginActivity : AppCompatActivity(){
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        val prefs = getPreferences(Context.MODE_PRIVATE)
////        val isRemember = prefs.getBoolean("remember_password", false)
//        val editor = getSharedPreferences("LoginData",Context.MODE_PRIVATE).edit()
//        editor.putString("account","1")
//        editor.putString("password","1")
//        editor.putBoolean("rememberPassword",false)
//        editor.putBoolean("autoLogin",false)
//
//
//
//    }
//
//
//}



