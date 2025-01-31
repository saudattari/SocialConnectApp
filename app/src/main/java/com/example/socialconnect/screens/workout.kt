package com.example.socialconnect.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp




@Composable
fun WorkOut() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // App Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FitTrack",
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Navigate to profile */ }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Daily Stats")
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatItem(title = "Calories", value = "500 kcal")
                    StatItem(title = "Steps", value = "3000")
                    StatItem(title = "Workout", value = "30 mins")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton(text = "Log Workout", icon = Icons.Default.FitnessCenter)
            ActionButton(text = "Log Meal", icon = Icons.Default.Restaurant)
            ActionButton(text = "Track Progress", icon = Icons.AutoMirrored.Filled.TrendingUp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Workout Recommendations
        Text(text = "Recommended Workouts")
        LazyRow {
            items(workoutList) { workout ->
                WorkoutCard(workout = workout)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()){
            StatItem("saud" , "200")
        }
    }
}

@Composable
fun StatItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value)
        Text(text = title)
    }
}

@Composable
fun ActionButton(text: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = { /* Handle click */ }) {
            Icon(imageVector = icon, contentDescription = text)
        }
        Text(text = text)
    }

}

@Composable
fun WorkoutCard(workout: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = workout)
        }
    }
}

val workoutList = listOf("Cardio", "Strength", "Yoga", "Cycling")

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    WorkOut()
}