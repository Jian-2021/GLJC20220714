package com.example.gljcdemo.barchart.year


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gljcdemo.ui.theme.chartColor0_5
import com.example.gljcdemo.ui.theme.chartColor10_25
import com.example.gljcdemo.ui.theme.chartColor20_31_5
import com.example.gljcdemo.ui.theme.chartColor5_10


@Preview
@Composable
fun MonthBarPreview() {
    val carTimesArray = intArrayOf(6, 13, 37, 25)
    MonthBar(carTimesArray,6)
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


///////////////////////////////////////////////////年中的某个月
@Composable
fun MonthBar(carTimesListOf: IntArray ,month: Int) {

    val carTimesArray1 =carTimesListOf
    val BarValueY1 = intArrayOf(carTimesArray1[0],
        carTimesArray1[0] + carTimesArray1[1],
        carTimesArray1[0] + carTimesArray1[1] + carTimesArray1[2],
        carTimesArray1[0] + carTimesArray1[1] + carTimesArray1[2] + carTimesArray1[3])

//      坐标
//    第一排
    val point1 = listOf(
        Point(0, 100 - 10 * BarValueY1[0])
    )
    //    第二排
    val point2 = listOf(
        Point(0, 100 - 10 * BarValueY1[1])
    )
    //    第三排
    val point3 = listOf(
        Point(0, 100 - 10 * BarValueY1[2])
    )
    //    第四排
    val point4 = listOf(
        Point(0, 100 - 10 * BarValueY1[3])
    )
    val context = LocalContext.current
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )


    Row {

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
//                                Toast.makeText(context, "onTap: $i", Toast.LENGTH_SHORT).show()  /////////////显示点击位置
                            }
                        )
                    }
            ) {
//        左纵坐标轴
//                drawLine(
//                    start = Offset(10f, 0f),
//                    end = Offset(10f, 500f),
//                    color = Color.Black,
//                    strokeWidth = 3f
//                )
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
//                for (i in scale) {
//                    drawLine(
//                        start = Offset(10f, 500 - i * 50f),
//                        end = Offset(700f, 500 - i * 50f),
//                        color = Color.Black,
//                        strokeWidth = 1f
//                    )
//                }

                start = true


//        柱形
                for (p in point4) {
                    drawRoundRect(
                        color = chartColor20_31_5,
                        topLeft = Offset(p.x + 40f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre),
                        cornerRadius = CornerRadius(15f, 15f)
                    )
                }

                for (p in point3) {
                    drawRect(
                        color = chartColor10_25,
                        topLeft = Offset(p.x + 40f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point2) {
                    drawRect(
                        color = chartColor5_10,
                        topLeft = Offset(p.x + 40f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point1) {
                    drawRect(
                        color = chartColor0_5,
                        topLeft = Offset(p.x + 40f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

            }
            Spacer(modifier = Modifier.height(170.dp))
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "${month}月", fontSize = 10.sp, modifier = Modifier.clickable {

                    val toast = Toast
                        .makeText(context, "${month}月\n" +
                                "0-5mm: ${carTimesArray1[0]}吨\n" +
                                "5-10mm: ${carTimesArray1[1]}吨\n" +
                                "10-25mm: ${carTimesArray1[2]}吨\n" +
                                "20-31.5mm: ${carTimesArray1[3]}吨", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                })
            }
        }
    }




}



