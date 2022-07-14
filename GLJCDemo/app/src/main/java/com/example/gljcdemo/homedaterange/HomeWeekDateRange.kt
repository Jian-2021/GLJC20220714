package com.example.gljcdemo.homedaterange


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HomeDateRangePreview() {
    HomeWeekDateRange()
}

@Composable
fun HomeWeekDateRange() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black),
        ) {
//                        var toweek =

            Text(text = "  日期范围：", fontSize = 16.sp)
//                        Text(text = "$today" + "~" + "$today")
            Text(text = "2022-06-20" + "~" + "2022-06-24")

        }

    }

}

