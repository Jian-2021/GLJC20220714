package com.example.gljcdemo.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // 状态数据初始化，初始化为字符串
    var account = mutableStateOf("")

    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun accountInput(inputContent: String) {
        account.value = inputContent
    }




    // 状态数据初始化，初始化为字符串
    var password  = mutableStateOf("")

    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun passwordInput(inputContent: String) {
        password.value = inputContent
    }

    // 状态数据初始化，初始化为布尔值
    var rememberPassword  = mutableStateOf(false)

    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun rememberPasswordInput(inputContent: Boolean) {
        rememberPassword.value = inputContent
    }













}