package com.example.gljcdemo.login


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gljcdemo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess


@Composable
fun Login(navController: NavController,viewModel: LoginViewModel) {

    val context = LocalContext.current
    //////////////////创建配置数据库变量
    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase
    getLoginDataAndSave(context, viewModel)
//    queryLoginDataStore(context, viewModel)
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var accountValue by rememberSaveable { mutableStateOf("") }     ////////////账号输入框当前显示的值，也可获取输入值
        var passwordValue by rememberSaveable { mutableStateOf("") }    ////////////密码输入框当前显示的值，也可获取输入值

        val passwordFocusRequest = remember { FocusRequester() }
        val localFocusManager = LocalFocusManager.current
//        val account1 = "1"
//        val password1 = "1"
//        val account2 = "2"
//        val password2 = "2"
//        val account3 = "3"
//        val password3 = "3"

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


                ///////////////////////////////记住密码//////////////////////////

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
                    ///////////////////////////////记住密码选择框//////////////////////////

                    Text(text = "记住密码", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = rememberPassword, onCheckedChange = {
                        rememberPassword = it
                        Log.d("rememberPassword","$rememberPassword")

                        val values = ContentValues().apply {
                            // 开始组装第一条数据
                            put("RememberPassword", it)

                        }
                        db.insert("RememberPassword", null, values)              ////插入第一条数据
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ))

                    Spacer(modifier = Modifier.width(20.dp))
                    ///////////////////////////////自动登录选择框//////////////////////////

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
//                        getLoginDataAndSave(context, viewModel)
                        queryLoginDataStore(context, viewModel)

                        localFocusManager.clearFocus()
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////验证账号密码
                        if (passwordValue.isEmpty() && accountValue.isEmpty()) {                      ///////////////当账号输入框或密码输入框为空
                            val toast = Toast
                                .makeText(context, "请输入账号和密码", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                        } else if ((viewModel.account.value == accountValue) && (viewModel.password.value == passwordValue)) {      ///////////////当账号输入
                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                            ////////////跳到管理员界面
//                            navController.navigate(Screen.Home.route)
                            navController.navigate(com.example.gljcdemo.Screen.Home.route)

                        } else if ((viewModel.account.value == accountValue) && (viewModel.password.value == passwordValue)) {

                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                            ////////////跳到南安界面
                            navController.navigate(com.example.gljcdemo.Screen.NanAn.route)
                        } else if ((viewModel.account.value == accountValue) && (viewModel.password.value == passwordValue)) {

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

fun getLoginDataAndSave(context: Context , viewModel: LoginViewModel){

//////////////////创建配置数据库变量
    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

/////////////////配置网络
    val retrofit = Retrofit.Builder()
        .baseUrl("http://1.117.154.150:2222/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val appService = retrofit.create(LoginService::class.java)
    db.delete("LoginData", "IDofDB > ?", arrayOf("0"))   ////删除数据库

    appService.getAppData().enqueue(
        object : Callback<List<LoginApp>> {
            override fun onResponse(call: Call<List<LoginApp>>, response: Response<List<LoginApp>>) {
                val list = response.body()
                if (list != null) {
                    for (LoginApp in list) {
                        Log.d("MainActivity", "id is ${LoginApp.id}")
                        Log.d("MainActivity", "account is ${LoginApp.account}")
                        Log.d("MainActivity", "password is ${LoginApp.password}")


                        ///////////////////////////////////////////////////////////////////////////////////
                        ////////将Login网络数据存入viewModel
                        viewModel.accountInput(LoginApp.account)
                        viewModel.passwordInput(LoginApp.password)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////存入数据库
                        val values = ContentValues().apply {
                            // 开始组装第一条数据
                            put("id", LoginApp.id)
                            put("account", LoginApp.account)
                            put("password", LoginApp.password)

                        }

                        db.insert("LoginData", null, values)              ////插入一条数据


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////// 通过粒径分类计算车次
//                        when(app.Category){
//                            "0-5" -> carTimes0_5++
//                            "5-10" ->carTimes5_10++
//                            "10-25" ->carTimes10_25++
//                            "20-31.5" ->carTimes20_31_5++
//
//                        }
////////////////////////////////////////////////////////////////////用viewModel在界面上显示数据

//                        viewModel.onInputChangecarTimes0_5(carTimes0_5.toString())
//                        viewModel.onInputChangecarTimes5_10(carTimes5_10.toString())
//                        viewModel.onInputChangecarTimes10_25(carTimes10_25.toString())
//                        viewModel.onInputChangecarTimes20_31_5(carTimes20_31_5.toString())



                    }
                }

            }

            override fun onFailure(call: Call<List<LoginApp>>, t: Throwable) {
                t.printStackTrace()
            }
        })

}


//////////////////////////////读取SQLite .db 数据库
fun queryLoginDataStore(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    val i = 0        ////用来索引viewModel 中的数据
    // 查询GL表中所有的数据
    val cursor = db.query("LoginData", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // 遍历Cursor对象，取出数据并打印
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////手机数据库里的id
            @SuppressLint("Range") val id = cursor.getString(cursor.getColumnIndex("id"))              //////////////LoginData数据库里的id
            @SuppressLint("Range") val account = cursor.getString(cursor.getColumnIndex("account"))    //////////////LoginData数据库里的账号
            @SuppressLint("Range") val password = cursor.getString(cursor.getColumnIndex("password"))  //////////////LoginData数据库里的密码

            Log.d("MainActivity", "LoginData from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "LoginData from Database id is $id")
            Log.d("MainActivity", "LoginData from Database account is $account")
            Log.d("MainActivity", "LoginData from Database account is $password")




            ///////////////////////////////////////////////////////////////////////////////////
            ////////用viewModel在界面上显示数据
//            viewModel.onInputChange(timeEnd.toString())
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}









