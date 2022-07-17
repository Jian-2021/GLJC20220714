package com.example.gljcdemo.login

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // 状态数据初始化，初始化为字符串
    var netAccount = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun netAccountInput(inputContent: String) {
        netAccount.value = inputContent
    }


/////////////////////////////////////////////////////////////////////////////////
    // 状态数据初始化，初始化为字符串
    var netPassword  = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun netPasswordInput(inputContent: String) {
        netPassword.value = inputContent
    }


/////////////////////////////////登录成功后保存到数据库中的账号////////////////////////////////////////////
    // 状态数据初始化，初始化为字符串
    var localAccount = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun localAccountInput(inputContent: String) {
        localAccount.value = inputContent
    }


/////////////////////////////////登录成功后保存到数据库中的密码////////////////////////////////////////////
    // 状态数据初始化，初始化为字符串
    var localPassword  = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun localPasswordInput(inputContent: String) {
        localPassword.value = inputContent
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

/////////////////////////////////从服务器直接保存到数据库中的账号////////////////////////////////////////////
    // 状态数据初始化，初始化为列表
    var netAccountList  = mutableStateListOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun netAccountListInput(inputContent: String,i : Int) {
        netAccountList[i] = inputContent
    }

//////////////////////////////////从服务器直接保存到数据库中的密码///////////////////////////////////////////
    // 状态数据初始化，初始化为列表
    var netPasswordList  = mutableStateListOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun netPasswordListInput(inputContent: String,i : Int) {
        netPasswordList[i] = inputContent
    }


    /////////////////////////////////登录成功后保存到数据库中的账号////////////////////////////////////////////
    // 状态数据初始化，初始化为字符串
    var loginNetDataListSize = mutableStateOf("")
    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun loginNetDataListSizeInput(inputContent: String) {
        loginNetDataListSize.value = inputContent
    }



}