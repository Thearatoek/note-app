package com.sample.noteapp.presentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sample.noteapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    navController: NavController
){
    LaunchedEffect(Unit){
        delay(2000)
        navController.navigate("dashboard_screen"){
            popUpTo("splash_screen"){inclusive = true}
        }
    }
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.notebook),
                contentDescription = "Notebook Icon",
                modifier = Modifier
                    .size(160.dp)

            )
        }
    }
}