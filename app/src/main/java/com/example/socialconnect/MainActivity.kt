package com.example.socialconnect

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.navigation.compose.rememberNavController
import com.example.socialconnect.navigation.ScreenNavigation
import com.example.socialconnect.ui.theme.SocialConnectTheme
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            SocialConnectTheme {
                val navHostController = rememberNavController()
                ScreenNavigation(navHostController)
            }
        }
    }

    // Add the handleSignIn method here
    fun handleSignIn(
        result: GetCredentialResponse,
        onComplete: (Boolean) -> Unit
    ) {
        val credentialData = result.credential
        when (credentialData) {
            is CustomCredential -> {
                if (credentialData.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        auth = Firebase.auth
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credentialData.data)
                        val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken,null)
                        auth.signInWithCredential(firebaseCredential).addOnCompleteListener({ task->
                            if(task.isSuccessful){
                                Toast.makeText(this, "Successfully logged in ", Toast.LENGTH_SHORT).show()
                                onComplete(true)
                            }
                        })
                    } catch (e: GoogleIdTokenParsingException) {
                        Toast.makeText(this, "Task failed", Toast.LENGTH_SHORT).show()
                        onComplete(false)
                    }
                }
                else{
                    Toast.makeText(this, "Unexpected type of credential", Toast.LENGTH_SHORT).show()
                    onComplete(false)
                }
            }
        }

    }
}
