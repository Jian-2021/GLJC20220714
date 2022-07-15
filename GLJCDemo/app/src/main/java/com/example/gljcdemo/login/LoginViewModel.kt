package com.example.gljcdemo.login

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // 状态数据初始化，初始化为字符串
    var account = mutableStateOf("0")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun accountInput(inputContent: String) {
        account.value = inputContent
    }


/////////////////////////////////////////////////////////////////////////////////
    // 状态数据初始化，初始化为字符串
    var password  = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun passwordInput(inputContent: String) {
        password.value = inputContent
    }


//////////////////////////////////记住密码///////////////////////////////////
    // 状态数据初始化，初始化为布尔值
    var rememberPassword  = mutableStateOf(false)
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun rememberPasswordInput(inputContent: Boolean) {
        rememberPassword.value = inputContent
    }


    //////////////////////////////////自动登录///////////////////////////////////
    // 状态数据初始化，初始化为布尔值
    var autoLogin  = mutableStateOf(false)
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun autoLoginInput(inputContent: Boolean) {
        autoLogin.value = inputContent
    }

/////////////////////////////////数据库中的账号////////////////////////////////////////////
    // 状态数据初始化，初始化为列表
    var accountFormDatabase  = mutableStateListOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun accountFormDatabaseInput(inputContent: String,i : Int) {
        accountFormDatabase[i] = inputContent
    }



//////////////////////////////////数据库中的密码///////////////////////////////////////////
    // 状态数据初始化，初始化为列表
    var passwordFormDatabase  = mutableStateListOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun passwordFormDatabaseInput(inputContent: String,i : Int) {
        passwordFormDatabase[i] = inputContent
    }











}