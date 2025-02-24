package com.example.otherinput

import android.R.attr.content
import android.R.id.content
import android.icu.text.CaseMap
import android.os.Bundle
import android.preference.PreferenceActivity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.otherinput.ui.theme.OtherInputTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtherInputTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                    TopAppBar(
                        title = { Text("Top Bar ") },
                        navigationIcon = {
                            IconButton(onClick = { /* Handle drawer click */ }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* Handle search click */ }) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        }
                    )

                        Greeting(
                            name = "Friend!!! ",
                            modifier = Modifier.padding(innerPadding)
                        )

                        BasicDropdownMenu()
                        SimpleSliderWithSteps()

                    }

                }
            }
        }
    }

    /**
     * DD Menu Composable - Can be called up from other locations
     *  From https://developer.android.com/develop/ui/compose/components/menu
     */
    @Composable
    fun BasicDropdownMenu() {
        var expanded by remember { mutableStateOf(false) }
        Row(

            modifier = Modifier
                .fillMaxWidth(0.5F) // Take up half screen
                .background(Color.Blue)
        ) {
            Text("Click Here for Options ...")
            IconButton(onClick = { expanded = !expanded }, ) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Option 1") },
                    onClick = { // Put applicable code here if option is selected
                        Toast.makeText(baseContext,"DDM Option 1", Toast.LENGTH_LONG).show()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Option 2") },
                    onClick = { // Put applicable code here if option is selected
                        Toast.makeText(baseContext,"DDM Option 2", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }


    @Preview
    @Composable
    fun SimpleSliderWithSteps() {
        var sliderPosition by remember { mutableFloatStateOf(0f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 15,
                valueRange = 0f..50f
            )
            var vol =  ((sliderPosition/ 50)*100).roundToInt()
            Text(text = "Volume $vol %")
        }
    }
// Default composables
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        OtherInputTheme {
            Greeting("Android")
        }

    }
}