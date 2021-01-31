package com.example.composeapplication.ui.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplication.R
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : AppCompatActivity() {

    private val isDarkTheme = mutableStateOf(false)

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeApplicationTheme(darkTheme = isDarkTheme.value) {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "Supercars") },
                actions = {
                    IconButton(onClick = { isDarkTheme.value = !isDarkTheme.value }) {
                        Icon(vectorResource(id = R.drawable.ic_dark_light_theme))
                    }
                }
            )
        },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.addCar() }
                ) {
                    Icon(imageVector = vectorResource(id = R.drawable.ic_add))
                }
            }, bodyContent = {
                Column {

                    FilterTextField()
                    Spacer(modifier = Modifier.padding(4.dp))
                    CarsList()
                }
            })
    }


    val filterText = mutableStateOf("")

    @Composable
    fun FilterTextField() {

        OutlinedTextField(
            value = filterText.value,
            onValueChange = {
                viewModel.find(it)
                filterText.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text("Search for a supercar...") },
        )
    }


    @Composable
    fun CarsList() {
        val titleStyle = MaterialTheme.typography.body1
        val valueStyle = MaterialTheme.typography.caption

        // RecyclerView
        LazyColumnFor(items = viewModel.cars.value) { car ->

            Surface(
                shape = MaterialTheme.shapes.large,
                elevation = 4.dp,
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    Image(
                        bitmap = imageResource(car.resId),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("${car.maker} ${car.model}", style = MaterialTheme.typography.h6)
                        Text(
                            viewModel.formatMoney(car.price),
                            color = Color(0xFF009933),
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )
                    }
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text("Power: ", style = titleStyle)
                        Text("${car.hp} bhp", style = valueStyle)
                    }
                    Row(modifier = Modifier.padding(8.dp)) {

                        Text("Top speed: ", style = titleStyle)
                        Text("${car.topSpeed} km/h", style = valueStyle)
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Button(
                            onClick = { viewModel.removeCar(car) },
                            colors = ButtonConstants.defaultButtonColors(
                                backgroundColor = MaterialTheme.colors.error,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "DELETE")
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview2() {
        MainScreen()
    }
}

