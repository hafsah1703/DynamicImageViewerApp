package com.hafsahiqbal.project3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hafsahiqbal.project3.ui.theme.Project3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project3Theme {
                DynamicImageViewerApp()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DynamicImageViewerApp() {
    // State to track the current image
    var currentImage by remember { mutableStateOf(R.drawable.image1) }

    // Background color
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF928C6F)) // Set background to #928c6f
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated image transition
            AnimatedContent(
                targetState = currentImage,
                transitionSpec = {
                    fadeIn() with fadeOut()
                }
            ) { targetImage ->
                Image(
                    painter = painterResource(id = targetImage),
                    contentDescription = "Displayed Image",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(16.dp)
                )
            }

            // Descriptive text for images
            Text(
                text = when (currentImage) {
                    R.drawable.image1 -> "This is the first image."
                    R.drawable.image2 -> "This is the second image."
                    else -> "This is the third image."
                },
                style = TextStyle(fontSize = 16.sp, color = Color.White),
                modifier = Modifier.padding(8.dp)
            )

            // Buttons with the same color
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { currentImage = R.drawable.image1 },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7D7D)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Image 1", color = Color.White)
                }
                Button(
                    onClick = { currentImage = R.drawable.image2 },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7D7D)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Image 2", color = Color.White)
                }
                Button(
                    onClick = { currentImage = R.drawable.image3 },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7D7D)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Image 3", color = Color.White)
                }
            }
        }

        // Floating Action Button to reset the image
        FloatingActionButton(
            onClick = { currentImage = R.drawable.image1 },
            containerColor = Color.Yellow,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_refresh),
                contentDescription = "Reset",
                tint = Color.Black
            )
        }
    }
}
