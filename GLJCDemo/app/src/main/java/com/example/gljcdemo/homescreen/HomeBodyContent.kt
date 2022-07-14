package com.example.gljcdemo.homescreen


import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gljcdemo.HomePieChart
import com.example.gljcdemo.barchart.month.MonthBarChart
import com.example.gljcdemo.barchart.week.WeekBarChart
import com.example.gljcdemo.barchart.year.YearBarChart
import com.example.gljcdemo.cartimeslist.MonthCarTimesList
import com.example.gljcdemo.cartimeslist.WeekCarTimesList
import com.example.gljcdemo.cartimeslist.YearCarTimesList
import com.example.gljcdemo.homedaterange.HomeMonthDateRange
import com.example.gljcdemo.homedaterange.HomeWeekDateRange
import com.example.gljcdemo.homedaterange.HomeYearDateRange
import com.example.gljcdemo.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBodyContent() {
//    val today = LocalDate.now()
    val carTimesArray1 = intArrayOf(6, 3, 8, 6)
    val carTimesArray2 = intArrayOf(7, 4, 9, 7)
    val carTimesArray3 = intArrayOf(8, 5, 10, 8)
//    val carTimesArray4 = intArrayOf(9, 6, 11, 9)
//    val carTimesArray5 = intArrayOf(6, 4, 12, 8)
//    val carTimesArray6 = intArrayOf(9, 6, 11, 7)
//    val carTimesArray7 = intArrayOf(8, 5, 10, 6)
//
//    val carTimesListOf = listOf(
//        carTimesArray1,
//        carTimesArray2,
//        carTimesArray3,
//        carTimesArray4,
//        carTimesArray5,
//        carTimesArray6,
//        carTimesArray7)


    var selectedDay by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFB6E1F7))
            .background(Color(0xFFFFFAFA))

    ) {
        LazyColumn() {
//            stickyHeader {
//                Text(
//                    "首页", modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Gray)
//                        .padding(vertical = 10.dp),
//                    textAlign = TextAlign.Center
//
//                )
//            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier
//                        .offset(y = -20.dp)
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp),
//                            .height(180.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Gray),
//                        elevation = 150.dp
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.width(30.dp))
                        ChooseCompany()
                    }

                }


//                Column(modifier = Modifier.fillMaxWidth()
//                ) {
//
//                    Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
//                    ) {
//                        ChooseCompany()
//                    }
//                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier
//                        .offset(y = -20.dp)
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Gray),
//                        elevation = 150.dp
                ) {

                    TabRow(
                        selectedTabIndex = selectedDay,
                        indicator = { positions ->//设置滑动条的属性，默认是白色的
                            TabRowDefaults.Indicator(
                                Modifier.tabIndicatorOffset(positions[selectedDay]),
                                color = Color.Red
                            )
                        },
                        backgroundColor = Color.White,
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                    ) {

                        Tab(
                            selected = selectedDay == 0,
                            text = {
                                Text(text = "周")
                            },
                            selectedContentColor = Color.Red,
                            unselectedContentColor = Color.Black,
                            onClick = { selectedDay = 0 })

                        Tab(
                            selected = selectedDay == 1,
                            text = {
                                Text(text = "月")
                            },
                            selectedContentColor = Color.Red,
                            unselectedContentColor = Color.Black,
                            onClick = { selectedDay = 1 })

                        Tab(
                            selected = selectedDay == 2,
                            text = {
                                Text(text = "年")
                            },
                            selectedContentColor = Color.Red,
                            unselectedContentColor = Color.Black,
                            onClick = { selectedDay = 2 })
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                when (selectedDay) {
                    0 -> HomeWeekDateRange()
                    1 -> HomeMonthDateRange()
                    2 -> HomeYearDateRange()
                }

                BackHandler(enabled = true) {
                    Log.e("tag","返回键被点击")
                    selectedDay =0
                }

                Spacer(modifier = Modifier.height(10.dp))

//                Button(onClick = { /*TODO*/ },
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Color.White,
//                        contentColor = Color.Black),
//                    border = BorderStroke(1.dp, Color.Gray),
//                    shape = shapes.small,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(10.dp)
//                )
                Card(
                    modifier = Modifier
//                        .offset(y = -20.dp)
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
//                            .height(180.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Gray),
//                        elevation = 150.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
//                        .padding(10.dp)
//                        .background(Color(0xFFFCFAE7)),

//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
                    ) {

                        Row() {
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = "产量", fontSize = 10.sp)
                        }

                        Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally)
                        ) {
//                            填充左边
                            Spacer(modifier = Modifier.width(10.dp))
//                            纵坐标
//                            Column {
////                            Spacer(modifier = Modifier.height(5.dp))
////                            Text(text = "50")
//                                Spacer(modifier = Modifier.height(25.dp))
//                                Text(text = "40", fontSize = 10.sp)
//                                Spacer(modifier = Modifier.height(20.dp))
//                                Text(text = "30", fontSize = 10.sp)
//                                Spacer(modifier = Modifier.height(20.dp))
//                                Text(text = "20", fontSize = 10.sp)
//                                Spacer(modifier = Modifier.height(20.dp))
//                                Text(text = "10", fontSize = 10.sp)
//                                Spacer(modifier = Modifier.height(15.dp))
//                                Text(text = "  0", fontSize = 10.sp)
//                            }

//                        Spacer(modifier = Modifier.width(5.dp))
//                        柱状图


                            when (selectedDay) {
                                0 -> WeekBarChart(carTimesArray1)
                                1 -> MonthBarChart()
                                2 -> YearBarChart()
                            }


//                            Spacer(modifier = Modifier.width(240.dp))
                            Column(modifier = Modifier.width(60.dp)) {
                                Spacer(modifier = Modifier.height(160.dp))
                                Text(text = "粒径", fontSize = 10.sp)
                            }
                        }


                        Spacer(modifier = Modifier.height(5.dp))
                        Row(Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(onClick = { /*TODO*/ }, enabled = false,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = chartColor0_5,
                                    contentColor = Color.Black,
                                    disabledBackgroundColor = chartColor0_5,
                                    disabledContentColor = Color.Black
                                ), modifier = Modifier.height(30.dp)
                            ) {
                                Text(text = "0-5", fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = { /*TODO*/ }, enabled = false,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = chartColor5_10,
                                    contentColor = Color.Black,
                                    disabledBackgroundColor = chartColor5_10,
                                    disabledContentColor = Color.Black
                                ), modifier = Modifier.height(30.dp)
                            ) {
                                Text(text = "5-10", fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = { /*TODO*/ }, enabled = false,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = chartColor10_25,
                                    contentColor = Color.Black,
                                    disabledBackgroundColor = chartColor10_25,
                                    disabledContentColor = Color.Black
                                ), modifier = Modifier.height(30.dp)
                            ) {
                                Text(text = "10-25", fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = { /*TODO*/ }, enabled = false,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = chartColor20_31_5,
                                    contentColor = Color.Black,
                                    disabledBackgroundColor = chartColor20_31_5,
                                    disabledContentColor = Color.Black
                                ), modifier = Modifier.height(30.dp)
                            ) {
                                Text(text = "25-31.5", fontSize = 10.sp)
                            }
                        }

                    }
                }

//                Spacer(modifier = Modifier.height(5.dp))


                Spacer(modifier = Modifier.height(0.dp))
//                    车次表

//                Row(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp)) {
//                    CarTimesChart()
//                }
//                Row(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp)) {
//                    CarTimesChart2()
//                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    when (selectedDay) {
                        0 -> WeekCarTimesList(carTimesArray1)
                        1 -> MonthCarTimesList(carTimesArray2)
                        2 -> YearCarTimesList()
                    }

                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
//                        .offset(y = -20.dp)
                            .fillMaxWidth()
                            .padding(10.dp),
//                            .height(180.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Gray),
//                        elevation = 150.dp
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = "粒径占比", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(5.dp))

                            Row {

                                Spacer(modifier = Modifier.width(20.dp))
//                                饼图
                                HomePieChart()

                                Spacer(modifier = Modifier.width(60.dp))
                                Column {
                                    Button(onClick = { /*TODO*/ }, enabled = false,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = chartColor0_5,
                                            contentColor = Color.Black,
                                            disabledBackgroundColor = chartColor0_5,
                                            disabledContentColor = Color.Black
                                        ), modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp)
                                    ) {
                                        Text(text = "0-5", fontSize = 10.sp)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Button(onClick = { /*TODO*/ }, enabled = false,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = chartColor5_10,
                                            contentColor = Color.Black,
                                            disabledBackgroundColor = chartColor5_10,
                                            disabledContentColor = Color.Black
                                        ), modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp)
                                    ) {
                                        Text(text = "5-10", fontSize = 10.sp)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Button(onClick = { /*TODO*/ }, enabled = false,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = chartColor10_25,
                                            contentColor = Color.Black,
                                            disabledBackgroundColor = chartColor10_25,
                                            disabledContentColor = Color.Black
                                        ), modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp)
                                    ) {
                                        Text(text = "10-25", fontSize = 10.sp)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Button(onClick = { /*TODO*/ }, enabled = false,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = chartColor20_31_5,
                                            contentColor = Color.Black,
                                            disabledBackgroundColor = chartColor20_31_5,
                                            disabledContentColor = Color.Black
                                        ), modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp)
                                    ) {
                                        Text(text = "25-31.5", fontSize = 10.sp)
                                    }

                                }

                            }
                            Spacer(modifier = Modifier.height(5.dp))

                        }

                    }

                }


//                底部填充适配小屏手机
                Spacer(modifier = Modifier.height(70.dp))
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeBodyContentPreview() {
    HomeBodyContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseCompany() {
    val options = listOf(
        "南安翔特", "三明华鑫"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
//        modifier = Modifier.background(Color(0xFFF7B35D))
    ) {
        TextField(
            modifier = Modifier.width(200.dp),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("选择公司", color = Color.Black) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(backgroundColor = Color(0xFFFFFFFF))
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
//                    Text(text = "selectionOption")
                }
            }
        }
    }
}


