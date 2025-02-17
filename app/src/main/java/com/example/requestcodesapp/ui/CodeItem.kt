package com.example.requestcodesapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.requestcodesapp.ui.theme.RequestCodesAppTheme

@Composable
fun CodeItem(
    modifier: Modifier = Modifier,
    code: String = "123 321",
    timerProgress: Float = 1f,
    timerRemaining: Int
) {
    val textColor = if (timerRemaining <= 5) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = code,
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )
        CircularProgressIndicator(
            progress = timerProgress,
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )
    }
    Divider(color = Color.LightGray, thickness = 1.dp)

}

@Preview(showBackground = true)
@Composable
fun CodeItemPreviewTimeMoreThen5(
    timerRemaining: Int = 30
) {
    RequestCodesAppTheme {
        CodeItem(
            timerRemaining = timerRemaining
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CodeItemPreviewTimeLessThen5(
    timerProgress: Float = 1f / 6,
    timerRemaining: Int = 3
) {
    RequestCodesAppTheme {
        CodeItem(
            timerProgress = timerProgress,
            timerRemaining = timerRemaining
        )
    }
}
