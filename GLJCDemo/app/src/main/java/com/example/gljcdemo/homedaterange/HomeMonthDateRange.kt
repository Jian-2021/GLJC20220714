package com.example.gljcdemo.homedaterange


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HomeMonthDateRangePreview() {
    HomeMonthDateRange()
}

@Composable
fun HomeMonthDateRange() {
    Row(modifier = Modifier.fillMaxWidth()) {

        Button(
            onClick = { /*TODO*/ },
            Modifier
//                .align(alignment = )
                .width(81.dp)
                .padding(start = 10.dp, end = 2.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black),
        ) {
            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = null,
                modifier = Modifier.size(10.dp))
            Text(text = "6月",
//                fontSize = 16.sp
            )
        }


        Button(
            onClick = { /*TODO*/ },
            Modifier
//                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(start = 2.dp, end = 2.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black),
        ) {
//                        var toweek =

//            Text(text = "  日期范围：", fontSize = 16.sp)
//                        Text(text = "$today" + "~" + "$today")
            Text(text = "2022/7/1" + "~" + "2022/7/31")
        }


        Button(
            onClick = { /*TODO*/ },
            Modifier
//                .align(Alignment.CenterHorizontally)
                .width(85.dp)
                .padding(start = 2.dp, end = 10.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black),
        ) {
            Text(text = "8月",
//                fontSize = 16.sp
            )
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null,
                modifier = Modifier.size(10.dp))
        }





    }
}

