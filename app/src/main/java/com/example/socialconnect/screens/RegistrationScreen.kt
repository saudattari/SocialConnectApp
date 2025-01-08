package com.example.socialconnect.screens


import android.credentials.GetCredentialException
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.socialconnect.CoilImage
import com.example.socialconnect.MainActivity
import com.example.socialconnect.R
import com.example.socialconnect.navigation.NavigationRoute
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import kotlinx.coroutines.launch
import java.security.SecureRandom

@Composable
fun RegistrationScreen(navHostController: NavHostController) {
    val context = LocalContext.current as MainActivity
    val scroll = rememberScrollState()
    val credential = CredentialManager.create(context)
    val signInWithGoogleOption = GetSignInWithGoogleOption.Builder(R.string.default_web_client_id.toString())
        .setNonce(generateNonce())
        .build()
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(signInWithGoogleOption)
        .build()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .fillMaxSize()
                .padding(vertical = 50.dp)
        ) {
            // Coil Image Loader (Logo)
            CoilImage.CoilImageLoader(
                url = R.drawable.logo,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 6.dp),
                colorFilter = null
            )

            // Heading
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

            // Google SignUp Button
            Button(
                onClick = {
                    if (context != null) {
                        context.lifecycleScope.launch {
                            try {
                                val result = credential.getCredential(request = request, context = context)
                                context.handleSignIn(result) {}
                            } catch (e: GetCredentialException) {
                                Log.e("SignInActivity", "Error retrieving credentials", e)
                            }
                        }
                    } else {
                        Log.e("SignInActivity", "Invalid context")
                    }
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

            // Create an account button
            Button(
                onClick = {
                    navHostController.navigate(NavigationRoute.SignupScreen.route)
                },
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
                    modifier = Modifier.clickable {
                        navHostController.navigate(NavigationRoute.LoginScreen.route)
                    }

                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        // Footer text
        Text(
            text = "Developed by\nSaud",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 18.dp)
        )
    }
}

fun generateNonce(length: Int = 32): String? {
    val nonce = ByteArray(length)
    SecureRandom().nextBytes(nonce)
    return Base64.encodeToString(nonce, Base64.URL_SAFE or Base64.NO_PADDING)
}
