package com.example.requestcodesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.requestcodesapp.presentation.RequestCodesViewModel
import com.example.requestcodesapp.ui.theme.RequestCodesAppTheme

@Composable
fun Codes(
    modifier: Modifier = Modifier,
    codes: List<String> = List(10) { "$it" },
    timerDuration: Int = 30, // общее время таймера в секундах,
    requestCodesViewModel: RequestCodesViewModel = RequestCodesViewModel()
) {
    val progress by requestCodesViewModel.timerProgress.collectAsState()
    val timeLeft by requestCodesViewModel.timeRemain.collectAsState()

    LaunchedEffect(timerDuration) {
        requestCodesViewModel.startTimer(timerDuration) {

        }
    }

    Surface(modifier = modifier) {
        LazyColumn(modifier = modifier.padding(10.dp)) {
            items(codes) { code ->
                CodeItem(
                    code = code,
                    timerProgress = progress,
                    timerRemaining = timeLeft
                )
            }
        }
    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun CodePreview() {
    RequestCodesAppTheme {
        Codes()
    }
}