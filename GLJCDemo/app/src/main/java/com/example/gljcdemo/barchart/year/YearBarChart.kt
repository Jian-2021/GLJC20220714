package com.example.gljcdemo.barchart.year


import android.graphics.Point
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gljcdemo.barchart.BarChartScale
import java.time.LocalDateTime
import java.time.temporal.ChronoField

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun YearBarChartPreview() {
    YearBarChart()
}
private fun identifyClickItem(
    points: List<Point>,
    x: Float,
    y: Float,
): Int {
    for ((index, point) in points.withIndex()) {
        if (x > point.x + 25 && x < point.x + 25 + 90) {
            return index
        }
    }
    return -1
}


////////////////////////////////////////////////年的图表，在这里设置12个月的数据

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearBarChart() {

//    车次数值
    val month = LocalDateTime.now().get(ChronoField.MONTH_OF_YEAR)
    val carTimesArray1 = intArrayOf(6, 3, 8, 6)
    val carTimesArray2 = intArrayOf(7, 4, 9, 7)
    val carTimesArray3 = intArrayOf(8, 5, 10, 8)
    val carTimesArray4 = intArrayOf(9, 6, 11, 9)
    val carTimesArray5 = intArrayOf(6, 4, 12, 8)
    val carTimesArray6 = intArrayOf(9, 6, 11, 7)
    val carTimesArray7 = intArrayOf(8, 5, 10, 6)
    val carTimesArray8 = intArrayOf(6, 3, 8, 6)
    val carTimesArray9 = intArrayOf(7, 4, 9, 7)
    val carTimesArray10 = intArrayOf(8, 5, 10, 8)
    val carTimesArray11 = intArrayOf(9, 6, 11, 9)
    val carTimesArray12 = intArrayOf(6, 4, 12, 8)


    val carTimesList = listOf(
        carTimesArray1,
        carTimesArray2,
        carTimesArray3,
        carTimesArray4,
        carTimesArray5,
        carTimesArray6,
        carTimesArray7,
        carTimesArray8,
        carTimesArray9,
        carTimesArray10,
        carTimesArray11,
        carTimesArray12
    )
    val monthTimes = carTimesList.size


    Row(modifier = Modifier.width(265.dp)) {
        Column() {
//                            Spacer(modifier = Modifier.height(5.dp))
//                            Text(text = "50")
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "100", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "75", modifier = Modifier.width(20.dp),fontSize = 10.sp, textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "50",modifier = Modifier.width(20.dp), fontSize = 10.sp, textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "25",modifier = Modifier.width(20.dp), fontSize = 10.sp, textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "0",modifier = Modifier.width(20.dp), fontSize = 10.sp, textAlign = TextAlign.End)
        }
        BarChartScale()
        LazyRow() {
            item {

                for (i in 1..monthTimes) {
                    MonthBar(carTimesListOf = carTimesList[i-1], month = i)

                }
            }
        }
    }

}

