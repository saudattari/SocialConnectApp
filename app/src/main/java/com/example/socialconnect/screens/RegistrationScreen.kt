package com.example.socialconnect.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialconnect.CoilImage
import com.example.socialconnect.R

@Preview
@Composable
fun RegistrationScreen() {
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .fillMaxSize()
                .padding(vertical = 50.dp)
        ) {

//            CoilImageLoader Function Calling
            CoilImage.CoilImageLoader(
                url = R.drawable.logo,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 6.dp),
                colorFilter = null
            )
//                Heading
            Text(
                text = "See what people are talking about in the world",
                modifier = Modifier
                    .padding(20.dp, 100.dp, 20.dp, 70.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Left,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = 28.sp,
            )
            Spacer(modifier = Modifier.height(60.dp))
//            Google Signup Button
            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(30.dp, 0.dp, 30.dp, 0.dp),
                border = BorderStroke(1.dp, color = Color.Black),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Gray
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                Text(
                    text = "Continue with Google",
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
//            Create an account button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(30.dp, 0.dp, 30.dp, 0.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF1F9EFF),
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                )
            ) {
                Text(
                    text = "Create an account",
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account: ",
                    modifier = Modifier
                )
                Text(
                    text = "Login",
                    color = Color(0xFF1F9EFF),
                    modifier = Modifier.clickable { }

                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = "Developed by\nSaud",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        )

    }
}