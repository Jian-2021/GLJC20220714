package com.example.gljcdemo


import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailBodyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB6E1F7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(){
//            stickyHeader {
//                Text(
//                    "详情", modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Gray)
//                        .padding(vertical = 10.dp),
//                    textAlign = TextAlign.Center)
//            }

            item{

                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black),
                    ) {
//                        var toweek =
                        Text(text = "  日期范围：", fontSize = 16.sp)
//                        Text(text = "$today" + "~" + "$today")
                        Text(text = "2022-05-16" + "~" + "2022-05-22")
                    }
                }

                Column(modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        ChooseParticleSize()
                    }
                }

            }

        }

    }

}

@Preview
@Composable
fun DetailBodyContentPreview() {
    DetailBodyContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseParticleSize() {
    val options = listOf(
        "0-5", "5-10", "10-25","20-31.5","全部粒径"
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
            label = { Text("选择粒径", color = Color.Black) },
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