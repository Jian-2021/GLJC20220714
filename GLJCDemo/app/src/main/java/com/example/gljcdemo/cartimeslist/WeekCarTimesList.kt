package com.example.gljcdemo.cartimeslist


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun WeekCarTimesListPreview() {
    val carTimesArray = intArrayOf(6, 13, 37, 25)
    WeekCarTimesList(carTimesArray)
}

@Composable
fun WeekCarTimesList(carTimesArray: IntArray) {
    val carTimesArray1 = intArrayOf(6, 3, 8, 6)
    val carTimesArray2 = intArrayOf(7, 4, 9, 7)
    val carTimesArray3 = intArrayOf(8, 5, 10, 8)
    val carTimesArray4 = intArrayOf(9, 6, 11, 9)
    val carTimesArray5 = intArrayOf(6, 4, 12, 8)
    val carTimesArray6 = intArrayOf(9, 6, 11, 7)
    val carTimesArray7 = intArrayOf(8, 5, 10, 6)

    val carTimes =
        intArrayOf(
            carTimesArray1[0] + carTimesArray2[0] + carTimesArray3[0] + carTimesArray4[0] + carTimesArray5[0] + carTimesArray6[0] + carTimesArray7[0],
            carTimesArray1[1] + carTimesArray2[1] + carTimesArray3[1] + carTimesArray4[1] + carTimesArray5[1] + carTimesArray6[1] + carTimesArray7[1],
            carTimesArray1[2] + carTimesArray2[2] + carTimesArray3[2] + carTimesArray4[2] + carTimesArray5[2] + carTimesArray6[2] + carTimesArray7[2],
            carTimesArray1[3] + carTimesArray2[3] + carTimesArray3[3] + carTimesArray4[3] + carTimesArray5[3] + carTimesArray6[3] + carTimesArray7[3]
        )


    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black),
        border = BorderStroke(1.dp, Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {

//            表头
            Row() {
                Spacer(modifier = Modifier.width(50.dp))
                Text(text = "粒径")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "本周产量")
            }
//            表头分界线
            Divider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(5.dp))

//            底部内容
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(20.dp))

//                粒径列
                Column(modifier = Modifier.width(60.dp)) {

                    val particleSizeList = listOf("0-5", "5-10", "10-25", "20-31.5")

                    for (i in 0..3) {
                        Text(text = particleSizeList[i],
                            textAlign = TextAlign.Start,
                            modifier = Modifier.width(60.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                }

//                单位列
                Column(modifier = Modifier.width(110.dp)) {
                    for (i in 1..4) {
                        Text(text = "mm",
                            textAlign = TextAlign.End,
                            modifier = Modifier.width(30.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                }
//                车次
                Column() {

                    for (i in 0..3) {
                        Text(text = "${carTimes[i]}",
                            textAlign = TextAlign.End,
                            modifier = Modifier.width(35.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                }
//                单位
                Column() {
                    for (i in 1..4) {
                        Text(text = "吨")
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                }
            }
        }
    }
}

