package com.example.socialconnect.screens

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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialconnect.CoilImage
import com.example.socialconnect.R
import com.example.socialconnect.ui.theme.clickColor
import com.example.socialconnect.ui.theme.focusColor

@Composable
@Preview
fun LogInScreen() {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Top Section: Logo and Login Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.TopCenter), // Align at the top
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage.CoilImageLoader(
                    url = R.drawable.logo,
                    modifier = Modifier
                        .padding(20.dp)
                        .size(50.dp),
                    colorFilter = ColorFilter.tint(clickColor)
                )
                Text(
                    text = "Login Now",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    ),
                    modifier = Modifier
                        .padding(0.dp, 20.dp, 0.dp, 0.dp)
                )
            }

            // Center Section: Form and Button
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .align(Alignment.Center), // Center the form
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    label = { Text(text = "Email") },
                    value = email.value,
                    onValueChange = { email.value = it },
                    modifier = Modifier.padding(vertical = 10.dp),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        focusedContainerColor = focusColor,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = clickColor,
                        unfocusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray
                    )
                )
                OutlinedTextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    onValueChange = { password.value = it },
                    modifier = Modifier.padding(vertical = 10.dp),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        focusedContainerColor = focusColor,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = clickColor,
                        unfocusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray
                    )
                )
                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = clickColor,
                    )
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account ")
                    Text(
                        text = "Signup", Modifier.clickable { }, color = clickColor
                    )
                }
            }

            // Bottom Section: Footer
            Text(
                text = "Developed by\nSaud",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}