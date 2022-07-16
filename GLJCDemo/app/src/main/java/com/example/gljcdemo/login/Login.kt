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

//    getLoginDataAndSave(context, viewModel)      ////////////////获取服务器上的账号密码
//    queryLoginDataStore(context, viewModel)
    queryRememberPassword(context, viewModel)    /////////////////读取本地数据库的记住密码状态，并传入viewModel
    queryAutoLogin(context, viewModel)    /////////////////读取本地数据库的自动登录状态，并传入viewModel



    //////////////////创建配置数据库变量
    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase
    var accountValue by rememberSaveable { mutableStateOf(viewModel.localAccount.value) }     ////////////账号输入框当前显示的值，也可获取输入值,屏幕旋转不改变值
//    var accountValue by remember { mutableStateOf(viewModel.account.value) }               ////////////账号输入框当前显示的值，也可获取输入值
    var passwordValue by rememberSaveable { mutableStateOf("") }                     ////////////密码输入框当前显示的值，也可获取输入值,屏幕旋转不改变值
    val passwordFocusRequest = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current
    var seePasswordToggle = remember { mutableStateOf(false) }



    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
///////////////////////////////////////////////////////////////////////////最外层列//////////////////////////////////////////////////////////////////////
//        accountValue = viewModel.account.value /////////////////////////////////////////////////////死循环，导致内存泄露，这里不能直接赋值

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
        ) {              /////////////////////////////////////////////////////白色卡片区域
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp, bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /////////////////////////////////////////////////////////////白色卡片内部列
                Text(text = "骨料监测系统",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 16.dp))


///////////////////////////////////////////////////////////////////////////////////////////////////账号输入框
                OutlinedTextField(
                    value = viewModel.localAccount.value,
                    onValueChange = {
                        viewModel.localAccount.value = it
                        accountValue = it

                                    },
                    placeholder = { Text(text = viewModel.localAccount.value, color = Color.Black) },
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


/**////////////////////////////////////////////////////////////////////////////////////////////////密码输入框
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
///////////////////////////////////////////////////////////////////////////////////////////////密码输入框/**/
                Text(text = viewModel.localAccount.value)
                Text(text = accountValue)

                Spacer(modifier = Modifier.height(10.dp))


                ///////////////////////////////记住密码//////////////////////////
                /////////////////////从viewModel中读取记住密码状态


                var rememberPassword by remember {
                    mutableStateOf(viewModel.rememberPassword.value)             ////////默认不记住密码
                }
                var autoLogin by remember {
                    mutableStateOf(viewModel.autoLogin.value)                 ///////////默认不自动登录
                }

                if (rememberPassword == false) {
                    autoLogin = false                               ///////////当不记住密码，就不自动登录

                }
//                autoLogin = autoLogin&&rememberPassword

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
                        if (it ==false){
                            autoLogin = it
                        }

                        Log.d("rememberPassword","$rememberPassword")
                        Log.d("autoLogin","$autoLogin")
//                        ////////将是否记住密码状态存入viewModel
//                        viewModel.rememberPasswordInput(rememberPassword)

                        ////////将是否记住密码状态存入数据库
                        val values = ContentValues().apply {
                            // 开始组装第一条数据
                            put("RememberPassword", rememberPassword)

                        }
                        db.delete("RememberPassword", "IDofDB > ?", arrayOf("0"))   ////删除数据库
                        db.insert("RememberPassword", null, values)              ////插入一条数据
                        ////////将是否自动登录状态存入数据库
                        val valuesAutoLogin = ContentValues().apply {
                            // 开始组装第一条数据
                            put("AutoLogin", autoLogin)

                        }
                        db.delete("AutoLogin", "IDofDB > ?", arrayOf("0"))   ////删除数据库
                        db.insert("AutoLogin", null, valuesAutoLogin)              ////插入一条数据

                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ))

                    Spacer(modifier = Modifier.width(20.dp))
                    ///////////////////////////////自动登录选择框//////////////////////////

                    Text(text = "自动登录", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = autoLogin, onCheckedChange = {
                        autoLogin = if (rememberPassword){
                            it
                        }else {
                            false
                        }

                        Log.d("rememberPassword","$rememberPassword")
                        Log.d("autoLogin","$autoLogin")

                        ////////将是否自动登录状态存入数据库
                        val values = ContentValues().apply {
                            // 开始组装第一条数据
                            put("AutoLogin", autoLogin)

                        }
                        db.delete("AutoLogin", "IDofDB > ?", arrayOf("0"))   ////删除数据库
                        db.insert("AutoLogin", null, values)              ////插入一条数据

                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ))
                }


                Spacer(modifier = Modifier.height(10.dp))

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////登录按钮
                Button(
                    onClick = {
//                        getLoginDataAndSave(context, viewModel)
//                        queryLoginDataStore(context, viewModel)

                        localFocusManager.clearFocus()
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////验证账号密码

                        if (passwordValue.isEmpty() && accountValue.isEmpty()) {                      ///////////////当账号输入框或密码输入框为空
                            val toast = Toast
                                .makeText(context, "请输入账号和密码", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                        } else if ((viewModel.netAccount.value == accountValue) && (viewModel.netPassword.value == passwordValue)) {      ///////////////当账号输入

                            /////////////////////////////////将账号密码存入本地数据库
                            db.delete("LocalLoginData", "IDofDB > ?", arrayOf("0"))   ////删除数据库内容
                            val localValues = ContentValues().apply {                                       // 开始组装数据
                                put("localAccount", accountValue)
                                put("localPassword", passwordValue)
                            }
                            db.insert("LocalLoginData", null, localValues)              ////插入一条数据


                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                            ////////////跳到管理员界面
//                            navController.navigate(Screen.Home.route)
                            navController.navigate(com.example.gljcdemo.Screen.Home.route)

                        }else if ((viewModel.localAccount.value == accountValue) && (viewModel.localPassword.value == passwordValue)) {      ///////////////当账号输入

                            /////////////////////////////////将账号密码存入本地数据库
                            db.delete("LocalLoginData", "IDofDB > ?", arrayOf("0"))   ////删除数据库内容
                            val localValues = ContentValues().apply {                                       // 开始组装数据
                                put("localAccount", accountValue)
                                put("localPassword", passwordValue)
                            }
                            db.insert("LocalLoginData", null, localValues)              ////插入一条数据


                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                            ////////////跳到管理员界面
//                            navController.navigate(Screen.Home.route)
                            navController.navigate(com.example.gljcdemo.Screen.Home.route)

                        } else if ((viewModel.localAccount.value == accountValue) && (viewModel.localPassword.value == passwordValue)) {

                            val toast = Toast
                                .makeText(context, "登录成功", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()

                            ////////////跳到南安界面
                            navController.navigate(com.example.gljcdemo.Screen.NanAn.route)
                        } else if ((viewModel.localAccount.value == accountValue) && (viewModel.localPassword.value == passwordValue)) {

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
                }///////////////////////////////////////////////////////////////////////////登录按钮
            } //////////////////////////////////////////////////////////////////////////////白色卡片内部列
        } //////////////////////////////////////////////////////////////////////////////////白色卡片区域
    } //////////////////////////////////////////////////////////////////////////////////////最外层列
    /////////监听返回键
    BackHandler(enabled = true) {
        Log.e("tag", "返回键被点击")
        exitProcess(0)    ///////////退出软件
    }

}


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
    db.delete("LoginData", "IDofDB > ?", arrayOf("0"))   ////删除数据库内容

    appService.getAppData().enqueue(
        object : Callback<List<LoginApp>> {
            override fun onResponse(call: Call<List<LoginApp>>, response: Response<List<LoginApp>>) {
                val list = response.body()
                Log.d("MainActivity", "net list is $list")
                if (list != null) {
                    for (LoginApp in list) {
                        Log.d("MainActivity", "net id is ${LoginApp.id}")
                        Log.d("MainActivity", "net account is ${LoginApp.account}")
                        Log.d("MainActivity", "net password is ${LoginApp.password}")


                        ///////////////////////////////////////////////////////////////////////////////////
                        //////将Login网络数据存入viewModel
//                        viewModel.accountInput(LoginApp.account)
//                        viewModel.passwordInput(LoginApp.password)
//                        viewModel.accountFormDatabaseInput(LoginApp.account,LoginApp.id.toInt())
//                        viewModel.passwordFormDatabaseInput(LoginApp.password,LoginApp.id.toInt())
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

    var i = 0        ////用来索引viewModel 中的数据
    // 查询LoginData表中所有的数据
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
            Log.d("MainActivity", "LoginData i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            ////////将Login数据库数据存入viewModel
            viewModel.netAccountInput(account)
            viewModel.netPasswordInput(password)
//////////////////////////////将数据存入viewModel

//            viewModel.accountFormDatabaseInput(account,i)
//            viewModel.passwordFormDatabaseInput(password,i)
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}

//////////////////////////////读取SQLite .db 数据库
fun queryLocalLoginDataStore(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////用来索引viewModel 中的数据
    // 查询LoginData表中所有的数据
    val cursor = db.query("LocalLoginData", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // 遍历Cursor对象，取出数据并打印
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////手机数据库里的id
            @SuppressLint("Range") val account = cursor.getString(cursor.getColumnIndex("localAccount"))    //////////////LocalLoginData数据库里的账号
            @SuppressLint("Range") val password = cursor.getString(cursor.getColumnIndex("localPassword"))  //////////////LocalLoginData数据库里的密码

            Log.d("MainActivity", "LocalLoginData from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "LocalLoginData from Database account is $account")
            Log.d("MainActivity", "LocalLoginData from Database account is $password")
            Log.d("MainActivity", "LocalLoginData i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////将Login数据库数据存入viewModel
            viewModel.localAccountInput(account)
            viewModel.localPasswordInput(password)
//////////////////////////////将数据存入viewModel

//            viewModel.accountFormDatabaseInput(account,i)
//            viewModel.passwordFormDatabaseInput(password,i)
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}


//////////////////////////////读取SQLite .db 数据库
fun queryRememberPassword(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////用来索引viewModel 中的数据
    // 查询RememberPassword表中所有的数据
    val cursor = db.query("RememberPassword", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // 遍历Cursor对象，取出数据并打印
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////手机数据库里的id
            @SuppressLint("Range") val RememberPassword = cursor.getString(cursor.getColumnIndex("RememberPassword"))              //////////////LoginData数据库里的id


            Log.d("MainActivity", "RememberPassword from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "RememberPassword from Database RememberPassword is $RememberPassword")

            Log.d("MainActivity", "RememberPassword i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////将记住密码状态存入viewModel
            var rememberPasswordBoolean = RememberPassword.toInt() != 0
            viewModel.rememberPasswordInput(rememberPasswordBoolean)
            Log.d("MainActivity", " rememberPasswordBoolean is $rememberPasswordBoolean")
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}

//////////////////////////////读取SQLite .db 数据库
fun queryAutoLogin(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////用来索引viewModel 中的数据
    // 查询RememberPassword表中所有的数据
    val cursor = db.query("AutoLogin", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // 遍历Cursor对象，取出数据并打印
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////手机数据库里的id
            @SuppressLint("Range") val AutoLogin = cursor.getString(cursor.getColumnIndex("AutoLogin"))              //////////////LoginData数据库里的id


            Log.d("MainActivity", "AutoLogin from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "AutoLogin from Database AutoLogin is $AutoLogin")
            Log.d("MainActivity", "AutoLogin i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////将记住密码状态存入viewModel
            var autoLoginBoolean = AutoLogin.toInt() != 0
            viewModel.autoLoginInput(autoLoginBoolean)
            Log.d("MainActivity", " AutoLoginBoolean is $autoLoginBoolean")
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}





