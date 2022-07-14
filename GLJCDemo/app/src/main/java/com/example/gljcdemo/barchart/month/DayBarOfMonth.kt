package com.example.gljcdemo.barchart.month


import android.graphics.Point
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.window.Dialog
import com.example.gljcdemo.ui.theme.chartColor0_5
import com.example.gljcdemo.ui.theme.chartColor10_25
import com.example.gljcdemo.ui.theme.chartColor20_31_5
import com.example.gljcdemo.ui.theme.chartColor5_10

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DayBarPreview() {
    val carTimesArray = intArrayOf(6, 13, 37, 25)
    DayBar(carTimesArray, 6, 1)
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


///////////////////////////////月中的几号

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayBar(carTimesListOf: IntArray, month: Int, day: Int) {

    var showDialog by remember {
        mutableStateOf(false)
    }


    val carTimesArray1 = carTimesListOf
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


    Row(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.Red),  ///////////////////////////////////////////////点击时背景颜色
            ) {
                showDialog = !showDialog
            }
            .width(30.dp)       ////////////////////////限制柱体加刻度的宽度
    ) {

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
                        topLeft = Offset(p.x + 25f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre),
                        cornerRadius = CornerRadius(15f, 15f)
                    )
                }

                for (p in point3) {
                    drawRect(
                        color = chartColor10_25,
                        topLeft = Offset(p.x + 25f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point2) {
                    drawRect(
                        color = chartColor5_10,
                        topLeft = Offset(p.x + 25f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

                for (p in point1) {
                    drawRect(
                        color = chartColor0_5,
                        topLeft = Offset(p.x + 25f, 500 - (100 - p.y) * heightPre),
                        size = Size(40f, (100 - p.y) * heightPre)
                    )
                }

            }
            Spacer(modifier = Modifier.height(170.dp))
            Row(modifier = Modifier.width(30.dp)) {
//                Spacer(modifier = Modifier.width(15.dp))  ///////////////////////////////文字前留出空间
                Text(
                    text = "$month/$day", fontSize = 10.sp,
                    modifier = Modifier
                        .width(35.dp)
                        .clickable {

//                    val toast = Toast
//                        .makeText(context, "$month/$day\n" + "0-5mm: ${carTimesArray1[0]}车\n" +
//                                "5-10mm: ${carTimesArray1[1]}车\n" +
//                                "10-25mm: ${carTimesArray1[2]}车\n" +
//                                "20-31.5mm: ${carTimesArray1[3]}车", Toast.LENGTH_SHORT)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()

                            showDialog = !showDialog


                        },
                    textAlign = TextAlign.Center,
                )

                if (showDialog) {
                    Dialog(onDismissRequest = { showDialog = !showDialog }) {

//                        Box(
//                            Modifier
//                                .size(200.dp, 120.dp)
//                                .background(Color.Gray)
//                                .clip(
//                                    shape = RoundedCornerShape(
//                                        bottomStart = 25.dp,
//                                        bottomEnd = 25.dp
//                                    )
//                                )) {
//                            Column {
//                                Text(text = "$month/$day")
//                                Text(text = "0-5mm:${carTimesArray1[0]}车")
//                                Text(text = "5-10mm:${carTimesArray1[1]}车")
//                                Text(text = "10-25mm:${carTimesArray1[2]}车")
//                                Text(text = "20-31.5mm:${carTimesArray1[3]}车")
//                            }
//
//                        }


                        Card(
                                modifier = Modifier
//                                    .offset(y = -20.dp)
                                    .width(150.dp)
                                    .height(120.dp),
                            shape = RoundedCornerShape(5.dp),
//                                elevation = 15.dp
                        ) {
                            Column {
                                Text(text = "$month/$day" ,Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                                Row {
                                    Text(text = "0-5mm:")
                                    Text(text = "${carTimesArray1[0]}吨",Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                                }

                                Row {
                                    Text(text = "5-10mm:")
                                    Text(text = "${carTimesArray1[1]}吨",Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                                }

                                Row {
                                    Text(text = "10-25mm:")
                                    Text(text = "${carTimesArray1[2]}吨",Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                                }

                                Row {
                                    Text(text = "20-31.5mm:")
                                    Text(text = "${carTimesArray1[3]}吨",Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                                }
                            }
                        }




                    }
                }


            }
        }
    }


}

