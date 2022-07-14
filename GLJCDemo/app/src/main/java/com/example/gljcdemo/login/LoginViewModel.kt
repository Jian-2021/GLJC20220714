package com.example.gljcdemo.login

import androidx.compose.runtime.mutableStateOf

class LoginViewModel {

    // 状态数据初始化，初始化为字符串
    var dataStr = mutableStateOf("-")

    // 状态更新方法，将新输入的内容赋值给 MutableState<T> 对象的 value 值
    fun onInputChange(inputContent: String) {
        dataStr.value = inputContent
    }

    // 状态数据初始化，初始化为字符串
    var carTimes0_5 = mutableStateOf("-")

    // 状态数据初始化，初始化为字符串
    var carTimes0_52 = mutableStateOf("-")











}