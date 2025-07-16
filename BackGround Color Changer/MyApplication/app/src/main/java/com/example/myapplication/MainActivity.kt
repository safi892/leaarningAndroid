package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.SideEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Obtain the current SystemUIController
            val systemUIController = rememberSystemUiController() // top bar where time is shown
            val backgroundColor = remember { mutableStateOf(Color.Gray) }
            val buttonColor = remember { mutableStateOf(Color.Gray) }



            // Set the status bar color whenever backgroundColor changes
            SideEffect {
                systemUIController.setStatusBarColor(color = backgroundColor.value) //Top bar

                systemUIController.setNavigationBarColor(// three bottom buttons
                    color = backgroundColor.value,
                    darkIcons = backgroundColor.value.luminance() > 0.5f // Adjust icons for readability
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor.value),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    onClick = {
                        // Generate a new random color
                        val newColor = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f
                        )
                        backgroundColor.value = newColor
                        buttonColor.value= newColor
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(12.dp)
                        .border(2.dp ,Color.Black, RoundedCornerShape(percent = 50)),  // used therse properties because used material3 button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor.value, // Set the button's background color
                        contentColor = if (buttonColor.value.luminance() > 0.5f) Color.Black else Color.White // adjust text color for readability
                )

                ) {
                    Text("Click to Change Background Color")

                }
            }
        }
    }

    /*
    
    Add this to get (buildgradle "APP") android top bar and battom bar
      implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0") // Use the latest stable version

    */
    // helper function for readbility
    private fun Color.luminance(): Float {
        return (0.2126f * red + 0.7152f * green + 0.0722f * blue)
    }
}





