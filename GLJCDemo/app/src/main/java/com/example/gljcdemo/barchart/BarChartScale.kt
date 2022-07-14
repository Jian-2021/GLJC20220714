package com.example.gljcdemo.barchart


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun BarChartScalePreview() {
    BarChartScale()
}

@Composable
fun BarChartScale() {
    Column {

        Canvas(
            modifier = Modifier
            .width(10.dp)
//            .height(25.dp)
//            .background(Color.White)
//                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onTap = {
//                            val i = identifyClickItem(point1, it.x, it.y)
////                                Toast.makeText(context, "onTap: $i", Toast.LENGTH_SHORT).show()  /////////////显示点击位置
//                        }
//                    )
//                }
        ) {
            //        左纵坐标轴
                drawLine(
                    start = Offset(10f, 0f),
                    end = Offset(10f, 500f),
                    color = Color.Black,
                    strokeWidth = 3f
                )

            //            刻度
//                val scale = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                val scale = listOf(1,3,5,7,9)
                val scale = listOf(2,4,6,8)
                for (i in scale) {
                    drawLine(
                        start = Offset(10f, 500 - i * 50f),
                        end = Offset(25f, 500 - i * 50f),
                        color = Color.Black,
                        strokeWidth = 1f
                    )
                }
            drawLine(
                start = Offset(10f, 500f ),
                end = Offset(50f, 500f),
                color = Color.Black,
                strokeWidth = 3f
            )


        }


    }


}

