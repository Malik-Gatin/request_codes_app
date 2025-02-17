package com.example.requestcodesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.requestcodesapp.ui.Codes
import com.example.requestcodesapp.ui.theme.RequestCodesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestCodesAppTheme {
                RequestCodesApp(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun RequestCodesApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Codes()
    }
}

@Preview(showBackground = true)
@Composable
fun RequestCodesAppPreview() {
    RequestCodesAppTheme {
        RequestCodesApp()
    }
}