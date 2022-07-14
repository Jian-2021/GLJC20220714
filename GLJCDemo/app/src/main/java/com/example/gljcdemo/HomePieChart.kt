package com.example.gljcdemo


import android.graphics.RectF
import android.graphics.Region
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gljcdemo.ui.theme.chartColor0_5
import com.example.gljcdemo.ui.theme.chartColor10_25
import com.example.gljcdemo.ui.theme.chartColor20_31_5
import com.example.gljcdemo.ui.theme.chartColor5_10
import java.math.RoundingMode
import java.text.DecimalFormat

@Preview
@Composable
fun HomePieChartPreview() {
    HomePieChart()
}

private fun getPositionFromAngle(
    angles: List<Float>,
    touchAngle: Double,
): Int {
    var totalAngle = 0f
    for ((i, angle) in angles.withIndex()) {
        totalAngle += angle
        if (touchAngle <= totalAngle) {
            return i
        }
    }
    return -1
}

@Composable
fun HomePieChart() {
    val context = LocalContext.current
    val point = listOf(53, 33, 71, 51)
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    val percentage0 = df.format((point[0] * 1f) / point.sum() * 100)
    val percentage1 = df.format((point[1] * 1f) / point.sum() * 100)
    val percentage2 = df.format((point[2] * 1f) / point.sum() * 100)
    val percentage3 = df.format((point[3] * 1f) / point.sum() * 100)
    val color = listOf(
        chartColor0_5,
        chartColor5_10,
        chartColor10_25,
        chartColor20_31_5
    )
    val sum = point.sum() * 1f
    var startAngle = 0f
//    半径
    val radius = 200f
    val rect = Rect(Offset(-radius, -radius), Size(2 * radius, 2 * radius))
    val path = Path()
    val angles = mutableListOf<Float>()
    var start by remember { mutableStateOf(false) }
    val sweepPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .width(150.dp)
            .padding(start = 20.dp)
            .height(150.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val x = it.x - radius
                        val y = it.y - radius
                        var touchAngle = Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()))
//                       坐标1,2象限返回-180~0  3,4象限返回0~180
                        if (x < 0 && y < 0 || x > 0 && y < 0) {
//                            3,4象限
                            touchAngle += 360
                        }
                        val position =
                            getPositionFromAngle(touchAngle = touchAngle, angles = angles)

                        when (position) {
                            0 -> Toast
                                .makeText(context, "0-5mm: ${percentage0}%", Toast.LENGTH_SHORT)
                                .show()
                            1 -> Toast
                                .makeText(context, "5-10mm: ${percentage1}%", Toast.LENGTH_SHORT)
                                .show()
                            2 -> Toast
                                .makeText(context, "10-25mm: ${percentage2}%", Toast.LENGTH_SHORT)
                                .show()
                            3 -> Toast
                                .makeText(context, "20-31.5mm: ${percentage3}%", Toast.LENGTH_SHORT)
                                .show()
                        }


//                        Toast
//                            .makeText(context, "onTap: $position ${point[1]}", Toast.LENGTH_SHORT)
//                            .show()
                    }
                )
            }
    ) {
        translate(radius, radius) {
            start = true
            for ((i, p) in point.withIndex()) {
                val sweepAngle = p / sum * 360f
                path.moveTo(0f, 0f)
                path.arcTo(rect = rect, startAngle, sweepAngle * sweepPre, false)
                angles.add(sweepAngle)
                drawPath(path = path, color = color[i])
                path.reset()
                startAngle += sweepAngle
            }
        }
        drawCircle(Color.White, radius = 150f,
            center = Offset(200f, 200f),
            style =
//            Stroke(width = 10f)
        Fill
        )
//        drawArc(
//            Color.Blue,
//            startAngle = 0f,
//            sweepAngle = 180f,
//            useCenter = true,
//            style = Stroke(width = 100f)
//        )

    }

}
