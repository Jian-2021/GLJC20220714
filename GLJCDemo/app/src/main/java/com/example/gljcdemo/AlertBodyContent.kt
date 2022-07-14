package com.example.gljcdemo


import android.os.Build
import android.webkit.RenderProcessGoneDetail
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlertBodyContent() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(){
//            stickyHeader {
//                Text(
//                    "报警", modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Gray)
//                        .padding(vertical = 10.dp),
//                    textAlign = TextAlign.Center)
//            }
            item {

                var alert by remember {
                    mutableStateOf(0)
                }
                var visible by remember {
                    mutableStateOf(false)
                }
                var visible2 by remember {
                    mutableStateOf(false)
                }
//                动画效果
                AnimatedVisibility2(visible2)


                when (alert){
                    1 -> AlertInformation1()
                    2 -> AlertInformation2()
                    3 -> AlertInformation3()
//                    4 -> AnimatedVisibility1(visible2)

                }
//                AnimatedVisibility(
//                    visible = visible
//                ) {
//                    AlertInformation2()
//
//                }

                AlertInformation1()

                Button(onClick = {alert = 1 },modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                        contentColor = Color.Black)
                ) {
                    Text(text = "报警测试按钮1")
                }
                Button(onClick = {alert = 2 },modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                    contentColor = Color.Black)
                ) {
                    Text(text = "报警测试按钮2")
                }
                Button(onClick = {alert = 3 },modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                        contentColor = Color.Black)

                ) {
                    Text(text = "报警测试按钮3")
                }


                Button(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                        contentColor = Color.Black),
                    onClick = {
                    visible2 = !visible2
//                    alert = 4

                }) {
                    val value = if (visible2) {
                        "隐藏报警"
                    } else {
                        "显示报警"
                    }
                    Text(text = value)
                }


            }



        }


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimatedVisibility1( visible :Boolean) {

    Column {
        AnimatedVisibility(
            visible = visible
        ) {
            AlertInformation1()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimatedVisibility2( visible :Boolean) {

    Column {
        AnimatedVisibility(
            visible = visible
        ) {
            AlertInformation2()
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertInformation1() {
    val today = LocalDate.now()

    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color(0xFFF0C3C3), shape = RoundedCornerShape(5.dp))
    ) {

//        Text(text = "报警时间：$today",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center)
        Text(text = "报警时间：2022-05-17  08:23:17",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)


        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp)
                .padding(8.dp)
//                .background(color = Color(0xFFE79898), shape = RoundedCornerShape(2.dp))
        ) {

            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "人工判断：")
            Text(text = "0-5")
            Spacer(modifier = Modifier.width(48.dp))
            Text(text = "监测仪判断：")
            Text(text = "5-10")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertInformation2() {
    val today = LocalDate.now()

    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color(0xFFF0C3C3), shape = RoundedCornerShape(5.dp))
    ) {

//        Text(text = "报警时间：$today",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center)

        Text(text = "报警时间：2022-05-17  08:40:42",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp)
                .padding(8.dp)
//                .background(color = Color(0xFFE79898), shape = RoundedCornerShape(2.dp))
        ) {

            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "人工判断：")
            Text(text = "5-10")
            Spacer(modifier = Modifier.width(38.dp))
            Text(text = "监测仪判断：")
            Text(text = "10-25")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertInformation3() {
    val today = LocalDate.now()

    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color(0xFFF0C3C3), shape = RoundedCornerShape(5.dp))
    ) {

//        Text(text = "报警时间：$today",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center)
        Text(text = "报警时间：2022-05-17  08:32:12",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)


        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp)
                .padding(8.dp)
//                .background(color = Color(0xFFE79898), shape = RoundedCornerShape(2.dp))
        ) {

            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "人工判断：")
            Text(text = "10-25")
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "监测仪判断：")
            Text(text = "20-31.5")
        }

    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AlertBodyContentPreview() {
    AlertBodyContent()
}

