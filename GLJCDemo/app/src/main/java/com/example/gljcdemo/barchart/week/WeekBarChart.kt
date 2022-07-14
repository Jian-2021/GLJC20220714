package com.example.gljcdemo.barchart.week


import android.graphics.Point
import android.view.Gravity
import android.widget.Toast
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gljcdemo.ui.theme.*

@Preview(showSystemUi = true)
@Composable
fun WeekBarChartPreview() {
    val carTimesArray = intArrayOf(6, 13, 37, 25)
    WeekBarChart(carTimesArray)
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

@Composable
fun WeekBarChart(carTimesListOf: IntArray) {

//    车次数值

//    val BarValueY = carTimesArray
//    val BarValueY2 =intArrayOf(10+BarValueY[0],10+BarValueY[1],10+BarValueY[2],10+BarValueY[3])
//    val BarValueY3 =intArrayOf(10+BarValueY2[0],10+BarValueY2[1],10+BarValueY2[2],10+BarValueY2[3])

    val carTimesArray1 = intArrayOf(6, 3, 8, 6)
    val carTimesArray2 = intArrayOf(7, 4, 9, 7)
    val carTimesArray3 = intArrayOf(8, 5, 10, 8)
    val carTimesArray4 = intArrayOf(9, 6, 11, 9)
    val carTimesArray5 = intArrayOf(6, 4, 12, 8)
    val carTimesArray6 = intArrayOf(9, 6, 11, 7)
    val carTimesArray7 = intArrayOf(8, 5, 10, 6)

//    val carTimes = carTimesListOf
//
//    val carTimesArray1 = carTimes[0]
//    val carTimesArray2 = intArrayOf(7, 4, 9, 7)
//    val carTimesArray3 = intArrayOf(8, 5, 10, 8)
//    val carTimesArray4 = intArrayOf(9, 6, 11, 9)
//    val carTimesArray5 = intArrayOf(6, 4, 12, 8)
//    val carTimesArray6 = intArrayOf(9, 6, 11, 7)
//    val carTimesArray7 = intArrayOf(8, 5, 10, 6)


    val BarValueY1 = intArrayOf(carTimesArray1[0],
        carTimesArray1[0] + carTimesArray1[1],
        carTimesArray1[0] + carTimesArray1[1] + carTimesArray1[2],
        carTimesArray1[0] + carTimesArray1[1] + carTimesArray1[2] + carTimesArray1[3])
    val BarValueY2 = intArrayOf(carTimesArray2[0],
        carTimesArray2[0] + carTimesArray2[1],
        carTimesArray2[0] + carTimesArray2[1] + carTimesArray2[2],
        carTimesArray2[0] + carTimesArray2[1] + carTimesArray2[2] + carTimesArray2[3])
    val BarValueY3 = intArrayOf(carTimesArray3[0],
        carTimesArray3[0] + carTimesArray3[1],
        carTimesArray3[0] + carTimesArray3[1] + carTimesArray3[2],
        carTimesArray3[0] + carTimesArray3[1] + carTimesArray3[2] + carTimesArray3[3])
    val BarValueY4 = intArrayOf(carTimesArray4[0],
        carTimesArray4[0] + carTimesArray4[1],
        carTimesArray4[0] + carTimesArray4[1] + carTimesArray4[2],
        carTimesArray4[0] + carTimesArray4[1] + carTimesArray4[2] + carTimesArray4[3])
    val BarValueY5 = intArrayOf(carTimesArray5[0],
        carTimesArray5[0] + carTimesArray5[1],
        carTimesArray5[0] + carTimesArray5[1] + carTimesArray5[2],
        carTimesArray5[0] + carTimesArray5[1] + carTimesArray5[2] + carTimesArray5[3])
    val BarValueY6 = intArrayOf(carTimesArray6[0],
        carTimesArray6[0] + carTimesArray6[1],
        carTimesArray6[0] + carTimesArray6[1] + carTimesArray6[2],
        carTimesArray6[0] + carTimesArray6[1] + carTimesArray6[2] + carTimesArray6[3])
    val BarValueY7 = intArrayOf(carTimesArray7[0],
        carTimesArray7[0] + carTimesArray7[1],
        carTimesArray7[0] + carTimesArray7[1] + carTimesArray7[2],
        carTimesArray7[0] + carTimesArray7[1] + carTimesArray7[2] + carTimesArray7[3])


//      坐标
//    第一排
    val point1 = listOf(
        Point(0, 100 - 10 * BarValueY1[0]),
        Point(90, 100 - 10 * BarValueY2[0]),
        Point(180, 100 - 10 * BarValueY3[0]),
        Point(270, 100 - 10 * BarValueY4[0]),
        Point(360, 100 - 10 * BarValueY5[0]),
        Point(450, 100 - 10 * BarValueY6[0]),
        Point(540, 100 - 10 * BarValueY7[0])
    )
    //    第二排
    val point2 = listOf(
        Point(0, 100 - 10 * BarValueY1[1]),
        Point(90, 100 - 10 * BarValueY2[1]),
        Point(180, 100 - 10 * BarValueY3[1]),
        Point(270, 100 - 10 * BarValueY4[1]),
        Point(360, 100 - 10 * BarValueY5[1]),
        Point(450, 100 - 10 * BarValueY6[1]),
        Point(540, 100 - 10 * BarValueY7[1])
    )
    //    第三排
    val point3 = listOf(
        Point(0, 100 - 10 * BarValueY1[2]),
        Point(90, 100 - 10 * BarValueY2[2]),
        Point(180, 100 - 10 * BarValueY3[2]),
        Point(270, 100 - 10 * BarValueY4[2]),
        Point(360, 100 - 10 * BarValueY5[2]),
        Point(450, 100 - 10 * BarValueY6[2]),
        Point(540, 100 - 10 * BarValueY7[2])
    )
    //    第四排
    val point4 = listOf(
        Point(0, 100 - 10 * BarValueY1[3]),
        Point(90, 100 - 10 * BarValueY2[3]),
        Point(180, 100 - 10 * BarValueY3[3]),
        Point(270, 100 - 10 * BarValueY4[3]),
        Point(360, 100 - 10 * BarValueY5[3]),
        Point(450, 100 - 10 * BarValueY6[3]),
        Point(540, 100 - 10 * BarValueY7[3])
    )

    val context = LocalContext.current
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Row {
        Column {
//                            Spacer(modifier = Modifier.height(5.dp))
//                            Text(text = "50")
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "40", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "30", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "20", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "10", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "0", modifier = Modifier.width(20.dp),fontSize = 10.sp , textAlign = TextAlign.End)
        }

        Column {

            Canvas(
                modifier = Modifier
//            .width(20.dp)
//            .height(25.dp)
//            .background(Color.White)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                val i = identifyClickItem(point1, it.x, it.y)
//                                Toast.makeText(context, "onTap: $i", Toast.LENGTH_SHORT).show()    /////////////显示点击位置
                            }
                        )
                    }
            ) {
//        左纵坐标轴
                drawLine(
                    start = Offset(10f, 0f),
                    end = Offset(10f, 500f),
                    color = Color.Black,
                    strokeWidth = 3f
                )
//        横坐标轴
                drawLine(
                    start = Offset(10f, 500f),
                    end = Offset(700f, 500f),
                    color = Color.Black,
                    strokeWidth = 3f
                )
//         右边线轴
//                drawLine(
//                    start = Offset(700f, 500f),
//                    end = Offset(700f, 0f),
//                    color = Color.Black,
//                    strokeWidth = 3f
//                )

//            刻度
//                val scale = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                val scale = listOf(2,4,6,8)
                for (i in scale) {
                    drawLine(
                        start = Offset(10f, 500 - i * 50f),
                        end = Offset(25f, 500 - i * 50f),
                        color = Color.Black,
                        strokeWidth = 1f
                    )
                }

                start = true


//        柱形
                for (p in point4) {
                    drawRoundRect(
                        color = chartColor20_31_5,
                        topLeft = Offset(p.x + 50f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre),
                        cornerRadius = CornerRadius(15f, 15f)
                    )
                }

                for (p in point3) {
                    drawRect(
                        color = chartColor10_25,
                        topLeft = Offset(p.x + 50f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point2) {
                    drawRect(
                        color = chartColor5_10,
                        topLeft = Offset(p.x + 50f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point1) {
                    drawRect(
                        color = chartColor0_5,
                        topLeft = Offset(p.x + 50f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

            }
            Spacer(modifier = Modifier.height(170.dp))
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "周一", fontSize = 10.sp, modifier = Modifier.clickable {

                    val toast = Toast
                        .makeText(context, "周一\n" + "0-5mm: ${carTimesArray1[0]}吨\n" +
                                "5-10mm: ${carTimesArray1[1]}吨\n" +
                                "10-25mm: ${carTimesArray1[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray1[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()




                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周二", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周二\n" + "0-5mm: ${carTimesArray2[0]}吨\n" +
                                "5-10mm: ${carTimesArray2[1]}吨\n" +
                                "10-25mm: ${carTimesArray2[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray2[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周三", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周三\n" + "0-5mm: ${carTimesArray3[0]}吨\n" +
                                "5-10mm: ${carTimesArray3[1]}吨\n" +
                                "10-25mm: ${carTimesArray3[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray3[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周四", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周四\n" + "0-5mm: ${carTimesArray4[0]}吨\n" +
                                "5-10mm: ${carTimesArray4[1]}吨\n" +
                                "10-25mm: ${carTimesArray4[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray4[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周五", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周五\n" + "0-5mm: ${carTimesArray5[0]}吨\n" +
                                "5-10mm: ${carTimesArray5[1]}吨\n" +
                                "10-25mm: ${carTimesArray5[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray5[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周六", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周六\n" + "0-5mm: ${carTimesArray6[0]}吨\n" +
                                "5-10mm: ${carTimesArray6[1]}吨\n" +
                                "10-25mm: ${carTimesArray6[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray6[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "周日", fontSize = 10.sp, modifier = Modifier.clickable {
                    val toast = Toast
                        .makeText(context, "周日\n" + "0-5mm: ${carTimesArray7[0]}吨\n" +
                                "5-10mm: ${carTimesArray7[1]}吨\n" +
                                "10-25mm: ${carTimesArray7[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray7[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
                Spacer(modifier = Modifier.width(25.dp))
            }
        }
    }


}







