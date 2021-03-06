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
import androidx.compose.material.MaterialTheme.colors
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
import com.example.gljcdemo.Screen
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess


@Composable
fun Login(navController: NavController, viewModel: LoginViewModel) {
    val context = LocalContext.current
//    if(viewModel.autoLogin.value){
//        ////////////?????????????????????
////                            navController.navigate(Screen.Home.route)
//        navController.navigate(com.example.gljcdemo.Screen.Home.route)
//    }

    queryAutoLogin(context, viewModel)    /////////////////??????????????????????????????????????????????????????viewModel
    getLoginDataAndSave(context, viewModel)      ////////////////?????????????????????????????????
    queryNetLoginDataStore(context, viewModel)      ////////////////????????????????????????
    queryLocalLoginDataStore(context, viewModel)   /////////////////????????????????????????????????????????????????viewModel
    queryRememberPassword(context, viewModel)    /////////////////??????????????????????????????????????????????????????viewModel
    queryAutoLogin(context, viewModel)    /////////////////??????????????????????????????????????????????????????viewModel
    if (!viewModel.rememberPassword.value){
        viewModel.localPasswordInput("")
    }
    //////////////////???????????????????????????
    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase
    var accountValue by rememberSaveable { mutableStateOf(viewModel.localAccount.value) }     ////////////?????????????????????????????????????????????????????????,????????????????????????
//    var accountValue by remember { mutableStateOf(viewModel.account.value) }               ////////////?????????????????????????????????????????????????????????
    var passwordValue by rememberSaveable { mutableStateOf("") }                     ////////////?????????????????????????????????????????????????????????,????????????????????????
    passwordValue = if (viewModel.rememberPassword.value) {
        viewModel.localPassword.value

    } else {
        ""
    }


    val passwordFocusRequest = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current
    var seePasswordToggle = remember { mutableStateOf(false) }

    var loop = 0
    var loginSucceed = false

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
///////////////////////////////////////////////////////////////////////////????????????//////////////////////////////////////////////////////////////////////
//        accountValue = viewModel.account.value /////////////////////////////////////////////////////?????????????????????????????????????????????????????????

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
        ) {              /////////////////////////////////////////////////////??????????????????
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp, bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /////////////////////////////////////////////////////////////?????????????????????
                Text(text = "??????????????????",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 16.dp))


///////////////////////////////////////////////////////////////////////////////////////////////////???????????????
                OutlinedTextField(
//                    value = viewModel.localAccount.value,
                    value = accountValue,
                    onValueChange = {
                        viewModel.localAccount.value = it
                        accountValue = it

                    },
//                    placeholder = { Text(text = viewModel.localAccount.value, color = Color.Black) },
                    label = { Text(text = "??????", color = Color.Black) },
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


/**////////////////////////////////////////////////////////////////////////////////////////////////???????????????
                OutlinedTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
//                    placeholder = { Text(text = "??????") },
                    label = { Text(text = "??????", color = Color.Black) },
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
///////////////////////////////////////////////////////////////////////////////////////////////???????????????/**/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////????????????????????????
                Text(text = "localAccount :${viewModel.localAccount.value}  localPassword :${viewModel.localPassword.value}", fontSize = 10.sp)
//                Text(text = "netAccount :${viewModel.netAccount.value}  netPassword :${viewModel.netPassword.value}", fontSize = 10.sp)
                Text(text = "accountValue :$accountValue  passwordValue :$passwordValue", fontSize = 10.sp)
                for (n in 0 until viewModel.loginNetDataListSize.value.toInt()) {
                    Text(text = "netAccountList$n :${viewModel.netAccountList[n]}  netPasswordList$n :${viewModel.netPasswordList[n]}", fontSize = 10.sp)
                }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Spacer(modifier = Modifier.height(10.dp))




                ///////////////////////////////????????????//////////////////////////
                /////////////////////???viewModel???????????????????????????


                var rememberPassword by remember {
                    mutableStateOf(viewModel.rememberPassword.value)             ////////?????????????????????
                }
                var autoLogin by remember {
                    mutableStateOf(viewModel.autoLogin.value)                 ///////////?????????????????????
                }

                if (rememberPassword == false) {
                    autoLogin = false                               ///////////???????????????????????????????????????

                }
//                autoLogin = autoLogin&&rememberPassword

//                if (autoLogin == true){
//                    rememberPassword = true
//                }
//                if (rememberPassword ==false){
//                    autoLogin = false
//
//                }
                var  checkBoxEnable = true
                if (accountValue.isEmpty()||passwordValue.isEmpty()){
                    checkBoxEnable  = false
                    rememberPassword =false
//                    viewModel.autoLoginInput(false)
//                    ////////??????????????????????????????????????????
//                    val values = ContentValues().apply {
//                        // ???????????????????????????
//                        put("AutoLogin", false)
//                    }
//                    db.delete("AutoLogin", "IDofDB > ?", arrayOf("0"))   ////???????????????
//                    db.insert("AutoLogin", null, values)              ////??????????????????
//                    navController.navigate(Screen.Login.route)       //////////?????????????????????
                }



                Row {
                    ///////////////////////////////?????????????????????//////////////////////////

                    Text(text = "????????????", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = rememberPassword, onCheckedChange = {
                        rememberPassword = it
                        if (it == false) {
                            autoLogin = it
                            db.delete("LocalLoginData", "IDofDB > ?", arrayOf("0"))   ////?????????????????????
                            val localValues = ContentValues().apply {                                       // ??????????????????
                                put("localId", "")
                                put("localAccount", accountValue)
                                put("localPassword", "")
                            }
                            db.insert("LocalLoginData", null, localValues)              ////??????????????????
                        }

                        Log.d("rememberPassword", "$rememberPassword")
                        Log.d("autoLogin", "$autoLogin")
//                        ////////?????????????????????????????????viewModel
//                        viewModel.rememberPasswordInput(rememberPassword)

                        ////////??????????????????????????????????????????
                        val values = ContentValues().apply {
                            // ???????????????????????????
                            put("RememberPassword", rememberPassword)

                        }
                        db.delete("RememberPassword", "IDofDB > ?", arrayOf("0"))   ////???????????????
                        db.insert("RememberPassword", null, values)              ////??????????????????
                        ////////??????????????????????????????????????????
                        val valuesAutoLogin = ContentValues().apply {
                            // ???????????????????????????
                            put("AutoLogin", autoLogin)

                        }
                        db.delete("AutoLogin", "IDofDB > ?", arrayOf("0"))   ////???????????????
                        db.insert("AutoLogin", null, valuesAutoLogin)              ////??????????????????

                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ), enabled = checkBoxEnable)

                    Spacer(modifier = Modifier.width(20.dp))
                    ///////////////////////////////?????????????????????//////////////////////////

                    Text(text = "????????????", modifier = Modifier.offset(10.dp, 12.dp), fontSize = 15.sp)
                    Checkbox(checked = autoLogin, onCheckedChange = {
                        autoLogin = if (rememberPassword  &&  accountValue.isNotEmpty()  &&  passwordValue.isNotEmpty()) {
                            it
                        } else {
                            false
                        }

                        Log.d("rememberPassword", "$rememberPassword")
                        Log.d("autoLogin", "$autoLogin")

                        ////////??????????????????????????????????????????
                        val values = ContentValues().apply {
                            // ???????????????????????????
                            put("AutoLogin", autoLogin)

                        }
                        db.delete("AutoLogin", "IDofDB > ?", arrayOf("0"))   ////???????????????
                        db.insert("AutoLogin", null, values)              ////??????????????????

                    }, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF448AFF),
                        uncheckedColor = Color.Black,
                    ), enabled = checkBoxEnable)
                }


                Spacer(modifier = Modifier.height(10.dp))

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////????????????
                Button(
                    onClick = {
//                        getLoginDataAndSave(context, viewModel)
//                        queryLoginDataStore(context, viewModel)

                        localFocusManager.clearFocus()
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////??????????????????


                        for (n in 0 .. viewModel.loginNetDataListSize.value.toInt()) {
//                            Text(text = "netAccountList :${viewModel.netAccountList[n]}  netPassword :${viewModel.netPasswordList[n]}", fontSize = 10.sp)

                            if ((viewModel.netAccountList[n] == accountValue) && (viewModel.netPasswordList[n] == passwordValue)) {      ///////////////???????????????
                                loginSucceed = true
                                /////////////////////////////////????????????????????????????????????
                                db.delete("LocalLoginData", "IDofDB > ?", arrayOf("0"))   ////?????????????????????
                                val localValues = ContentValues().apply {                                       // ??????????????????
                                        put("localId", n)
                                        put("localAccount", accountValue)
                                        put("localPassword", passwordValue)
                                    }
                                db.insert("LocalLoginData", null, localValues)              ////??????????????????


                                ////////////?????????????????????
                                when (n) {
                                    0 -> {    ////////////?????????????????????
                                        val toast = Toast
                                            .makeText(context, "????????????", Toast.LENGTH_SHORT)
                                        toast.setGravity(Gravity.CENTER, 0, 0)
                                        toast.show()
                                        navController.navigate(com.example.gljcdemo.Screen.Home.route)
                                    }
                                    1 -> {    ////////////??????????????????
                                        val toast = Toast
                                            .makeText(context, "????????????", Toast.LENGTH_SHORT)
                                        toast.setGravity(Gravity.CENTER, 0, 0)
                                        toast.show()
                                        navController.navigate(com.example.gljcdemo.Screen.NanAn.route)
                                    }
                                    2 -> {    ////////////?????????????????????
                                        val toast = Toast
                                            .makeText(context, "????????????", Toast.LENGTH_SHORT)
                                        toast.setGravity(Gravity.CENTER, 0, 0)
                                        toast.show()
                                        navController.navigate(com.example.gljcdemo.Screen.SanMing.route)
                                    }

                                }

                            } else if (n==viewModel.loginNetDataListSize.value.toInt()&&passwordValue.isEmpty() && accountValue.isEmpty()) {                      ///////////////??????????????????????????????????????????
                                val toast = Toast
                                    .makeText(context, "????????????????????????", Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()

                            }else if (n==viewModel.loginNetDataListSize.value.toInt()&&passwordValue.isEmpty()) {                      ///////////////??????????????????????????????????????????
                                val toast = Toast
                                    .makeText(context, "???????????????", Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()

                            } else if (n==viewModel.loginNetDataListSize.value.toInt()&&accountValue.isEmpty()) {                      ///////////////??????????????????????????????????????????
                                val toast = Toast
                                    .makeText(context, "???????????????", Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()

                            } else if (n ==viewModel.loginNetDataListSize.value.toInt()&&(loginSucceed ==false)){
                                val toast = Toast
                                    .makeText(context, "??????????????????????????????????????????", Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()
                            }

//                            else if(n==viewModel.loginNetDataListSize.value.toInt()&&(!((viewModel.netAccountList[n] == accountValue) && (viewModel.netPasswordList[n] == passwordValue)))){
//                                val toast = Toast
//                                    .makeText(context, "??????????????????????????????????????????", Toast.LENGTH_SHORT)
//                                toast.setGravity(Gravity.CENTER, 0, 0)
//                                toast.show()
//                            }
                            Log.d("n", "$n")
                            Log.d("loop1", "$loop")
                            loop+=1
                            Log.d("loop2", "$loop")
                        }
                        Log.d("loop", "$loop")

//                        if (loop >=viewModel.loginNetDataListSize.value.toInt()){
//                            val toast = Toast
//                                .makeText(context, "??????????????????????????????????????????", Toast.LENGTH_SHORT)
//                            toast.setGravity(Gravity.CENTER, 0, 0)
//                            toast.show()
//                        }


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
                    Text(text = "??????", fontSize = 20.sp)
                }///////////////////////////////////////////////////////////////////////////????????????
            } //////////////////////////////////////////////////////////////////////////////?????????????????????
        } //////////////////////////////////////////////////////////////////////////////////??????????????????
    } //////////////////////////////////////////////////////////////////////////////////////????????????
    /////////???????????????
    BackHandler(enabled = true) {
        Log.e("tag", "??????????????????")
        exitProcess(0)    ///////////????????????
    }

}


fun getLoginDataAndSave(context: Context, viewModel: LoginViewModel) {

//////////////////???????????????????????????
    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

/////////////////????????????
    val retrofit = Retrofit.Builder()
        .baseUrl("http://1.117.154.150:2222/")
//        .baseUrl("http://192.168.50.108:2222/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val appService = retrofit.create(LoginService::class.java)
    db.delete("LoginData", "IDofDB > ?", arrayOf("0"))   ////?????????????????????

    appService.getAppData().enqueue(
        object : Callback<List<LoginApp>> {
            override fun onResponse(
                call: Call<List<LoginApp>>,
                response: Response<List<LoginApp>>,
            ) {
                val list = response.body()
                val listSize = list?.size.toString()
                viewModel.loginNetDataListSizeInput(listSize)
                Log.d("MainActivity", "net list is $list")
                Log.d("MainActivity", "net listSize is $listSize")
                if (list != null) {
                    for (LoginApp in list) {
                        Log.d("MainActivity", "net id is ${LoginApp.id}")
                        Log.d("MainActivity", "net account is ${LoginApp.account}")
                        Log.d("MainActivity", "net password is ${LoginApp.password}")


                        ///////////////////////////////////////////////////////////////////////////////////
                        //////???Login??????????????????viewModel
//                        viewModel.accountInput(LoginApp.account)
//                        viewModel.passwordInput(LoginApp.password)
//                        viewModel.accountFormDatabaseInput(LoginApp.account,LoginApp.id.toInt())
//                        viewModel.passwordFormDatabaseInput(LoginApp.password,LoginApp.id.toInt())
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////???????????????
                        val values = ContentValues().apply {
                            // ???????????????????????????
                            put("id", LoginApp.id)
                            put("account", LoginApp.account)
                            put("password", LoginApp.password)

                        }

                        db.insert("LoginData", null, values)              ////??????????????????

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    }
                }

            }

            override fun onFailure(call: Call<List<LoginApp>>, t: Throwable) {
                t.printStackTrace()
            }
        })

}


//////////////////////////////??????SQLite .db ?????????
fun queryNetLoginDataStore(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////????????????viewModel ????????????
    // ??????LoginData?????????????????????
    val cursor = db.query("LoginData", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // ??????Cursor??????????????????????????????
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////?????????????????????id
            @SuppressLint("Range") val id = cursor.getString(cursor.getColumnIndex("id"))              //////////////LoginData???????????????id
            @SuppressLint("Range") val account = cursor.getString(cursor.getColumnIndex("account"))    //////////////LoginData?????????????????????
            @SuppressLint("Range") val password = cursor.getString(cursor.getColumnIndex("password"))  //////////////LoginData?????????????????????

            Log.d("MainActivity", "LoginData from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "LoginData from Database id is $id")
            Log.d("MainActivity", "LoginData from Database account is $account")
            Log.d("MainActivity", "LoginData from Database account is $password")
            Log.d("MainActivity", "LoginData i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            ////////???Login?????????????????????viewModel
//            viewModel.netAccountInput(account)
//            viewModel.netPasswordInput(password)
//////////////////////////////???????????????viewModel

            viewModel.netAccountList.add(i, account)
            viewModel.netPasswordList.add(i, password)
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}

//////////////////////////////??????SQLite .db ?????????
fun queryLocalLoginDataStore(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////????????????viewModel ????????????
    // ??????LoginData?????????????????????
    val cursor = db.query("LocalLoginData", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // ??????Cursor??????????????????????????????
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////?????????????????????id
            @SuppressLint("Range") val localId = cursor.getString(cursor.getColumnIndex("localId"))     ////////////////////?????????????????????id
            @SuppressLint("Range") val account = cursor.getString(cursor.getColumnIndex("localAccount"))    //////////////LocalLoginData?????????????????????
            @SuppressLint("Range") val password = cursor.getString(cursor.getColumnIndex("localPassword"))  //////////////LocalLoginData?????????????????????

            Log.d("MainActivity", "LocalLoginData from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "LocalLoginData from Database localId is $localId")
            Log.d("MainActivity", "LocalLoginData from Database account is $account")
            Log.d("MainActivity", "LocalLoginData from Database password is $password")
            Log.d("MainActivity", "LocalLoginData i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////???Login?????????????????????viewModel
            viewModel.localIdInput(localId)
            viewModel.localAccountInput(account)
            viewModel.localPasswordInput(password)

//////////////////////////////???????????????viewModel

//            viewModel.accountFormDatabaseInput(account,i)
//            viewModel.passwordFormDatabaseInput(password,i)
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}


//////////////////////////////??????SQLite .db ?????????
fun queryRememberPassword(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////????????????viewModel ????????????
    // ??????RememberPassword?????????????????????
    val cursor = db.query("RememberPassword", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // ??????Cursor??????????????????????????????
            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))     ////////////////////?????????????????????id
            @SuppressLint("Range") val RememberPassword = cursor.getString(cursor.getColumnIndex("RememberPassword"))              //////////////LoginData???????????????id


            Log.d("MainActivity", "RememberPassword from Database IDofDB is $IDofDB")
            Log.d("MainActivity",
                "RememberPassword from Database RememberPassword is $RememberPassword")

            Log.d("MainActivity", "RememberPassword i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////???????????????????????????viewModel
            var rememberPasswordBoolean = RememberPassword.toInt() != 0
            viewModel.rememberPasswordInput(rememberPasswordBoolean)
            Log.d("MainActivity", " rememberPasswordBoolean is $rememberPasswordBoolean")
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}

//////////////////////////////??????SQLite .db ?????????
fun queryAutoLogin(context: Context, viewModel: LoginViewModel) {

    val dbHelper = LoginDataBaseHelper(context, "LoginDataStore.db", 3)
    val db = dbHelper.writableDatabase

    var i = 0        ////????????????viewModel ????????????
    // ??????RememberPassword?????????????????????
    val cursor = db.query("AutoLogin", null, null, null, null, null, null)
    if (cursor.moveToFirst()) {
        do {
            // ??????Cursor??????????????????????????????

            @SuppressLint("Range") val IDofDB = cursor.getString(cursor.getColumnIndex("IDofDB"))                    ////////////////////?????????????????????id
            @SuppressLint("Range") val AutoLogin = cursor.getString(cursor.getColumnIndex("AutoLogin"))              //////////////LoginData???????????????id


            Log.d("MainActivity", "AutoLogin from Database IDofDB is $IDofDB")
            Log.d("MainActivity", "AutoLogin from Database AutoLogin is $AutoLogin")
            Log.d("MainActivity", "AutoLogin i is $i")

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////???????????????????????????viewModel
            var autoLoginBoolean = AutoLogin.toInt() != 0
            viewModel.autoLoginInput(autoLoginBoolean)
            Log.d("MainActivity", " AutoLoginBoolean is $autoLoginBoolean")
            i++
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } while (cursor.moveToNext())
    }
    cursor.close()

}





