package com.example.gljcdemo.accountscreen


import android.icu.util.Calendar
import android.os.Build
import android.webkit.WebView

import android.webkit.WebViewClient
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDateTime
import java.time.temporal.ChronoField


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AccountScreenPreview() {
    AccountScreen()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountScreen() {
    val today = LocalDateTime.now()
    val today2 = today.get(ChronoField.DAY_OF_MONTH) + 1
    val calendar = Calendar.getInstance()
    val calendar2 = Calendar.DATE

//       val client = OkHttpClient()
//       val request = Request.Builder()
//           // 指定访问的服务器地址是电脑本机
////                    .url("http://10.0.2.2/get_data.xml")
////                .url("http://10.0.2.2/get_data.json")
//           .url("https://www.baidu.com")
//           .build()
//       val response = client.newCall(request).execute()
//       val responseData = response.body?.string()
//       thread {
//           try {
//
//               if (responseData != null) {
////                    parseXMLWithPull(responseData)
////                    parseXMLWithSAX(responseData)
////                    parseJSONWithJSONObject(responseData)
////                parseJSONWithGSON(responseData)
//
//               }
//           } catch (e: Exception) {
//               e.printStackTrace()
//           }
//       }
//    LazyColumn( ){
//       item {
//
//           Column {
//               Text(text = "$responseData")
//           }
//       }
//
//   }
    LazyColumn {
        item {

//            Text(text = "$today")
//            Text(text = "$today2")
//            Text(text = "${today.get(ChronoField.YEAR)}")
//            Text(text = "${today.get(ChronoField.MONTH_OF_YEAR)}")
//            Text(text = "${today.get(ChronoField.DAY_OF_MONTH)}")
//            Text(text = "${today.get(ChronoField.DAY_OF_WEEK)}")
//            Text(text = "${today.get(ChronoField.CLOCK_HOUR_OF_DAY)}")
//            Text(text = "${today.get(ChronoField.MINUTE_OF_HOUR)}")
//            Text(text = "${today.get(ChronoField.SECOND_OF_MINUTE)}")
//            Text(text = "${today.get(ChronoField.AMPM_OF_DAY)}")
//            Text(text = "${today.get(ChronoField.CLOCK_HOUR_OF_AMPM)}")
//            Text(text = "${calendar.time}")
//            Text(text = "${calendar.isWeekend}")
//            Text(text = "${calendar.fieldCount}")
//            Text(text = "${calendar2}")

//            AndroidView(
//                factory = ::WebView,
//                update = { webView ->
//                    webView.webViewClient = WebViewClient()
//                    webView.loadUrl("https://www.baidu.com/")
//                },
//                modifier = Modifier.fillMaxSize()
//            )

            AndroidView(factory = {
                TextView(it)
            }) {
                it.apply {
                    text = "这是从 Compose 里的 xml 布局的 TextView"
                }
            }

        }
    }


}

@Composable
fun MyWebClient(url: String) {
    AndroidView(
        factory = ::WebView,
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
    )
}

