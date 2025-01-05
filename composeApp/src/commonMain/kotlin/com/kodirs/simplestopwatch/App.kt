package com.kodirs.simplestopwatch

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0952DA),
                            Color(0xFF00FFE1)
                        )
                    )
                ).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            StopwatchScreen(viewModel = StopwatchViewModel())
        }

    }
}

@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel) {
    val time by viewModel.time.collectAsState()
    val seconds = (time / 1000L).toInt() % 60
    val minutes = (time / 60000L).toInt() % 60
    val millis = (time % 1000L) / 10

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${minutes.toString().padStart(2, '0')}:${
                seconds.toString().padStart(2, '0')
            }:${millis.toString().padStart(2, '0')}",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))


        Canvas(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        ) {
            val radius = size.minDimension / 2
            val angle = (seconds + millis / 100.0) * 6.0 * (PI / 180) - (PI / 2)
            val centerX = size.width / 2
            val centerY = size.height / 2
            val handLength = radius * 0.8

            drawCircle(Color.Black.copy(alpha = 0.5f), radius)

            drawLine(
                color = Color.White,
                start = center,
                end = androidx.compose.ui.geometry.Offset(
                    x = (centerX + handLength * cos(angle)).toFloat(),
                    y = (centerY + handLength * sin(angle)).toFloat()
                ),
                strokeWidth = 4f
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.start() }, shape = CircleShape,
                modifier = Modifier.padding(top = 16.dp).width(90.dp).height(90.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black.copy(alpha = 0.5f),
                    contentColor = Color(0xFF00FFE1)
                ),
            ) {
                Text("Start", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            }
            Button(
                onClick = { viewModel.stop() }, shape = CircleShape,
                modifier = Modifier.padding(top = 16.dp).width(90.dp).height(90.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black.copy(alpha = 0.5f),
                    contentColor = Color(0xFF00FFE1)
                ),
            ) {
                Text("Stop", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
                Button(
                    onClick = { viewModel.reset() }, shape = CircleShape,
                    modifier = Modifier.padding(top = 16.dp).width(90.dp).height(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black.copy(alpha = 0.5f),
                        contentColor = Color(0xFF00FFE1)
                    ),
                ) {
                    Text("Reset", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                }
            }
        }

