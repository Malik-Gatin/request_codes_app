package com.example.requestcodesapp.ui.screen.request_codes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.requestcodesapp.data.network.api.models.ApiResult
import com.example.requestcodesapp.ui.theme.RequestCodesAppTheme
import kotlinx.coroutines.launch

@Composable
fun Codes(
    modifier: Modifier = Modifier,
    requestCodesViewModel: RequestCodesViewModel = viewModel()
) {
    val progress by requestCodesViewModel.timerProgress.collectAsState()
    val timeRemain by requestCodesViewModel.timeRemain.collectAsState()
    val state by requestCodesViewModel.codesState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state) {
        if (state is ApiResult.Error) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar((state as ApiResult.Error).message)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is ApiResult.Loading -> CircularProgressIndicator()
                is ApiResult.Success -> {
                    val codes = (state as ApiResult.Success<List<String>>).data
                    CodesContent(
                        codes = codes,
                        progress = progress,
                        timeRemain = timeRemain
                    )
                }
                is ApiResult.Error -> Unit // Ошибка уже отображается в Snackbar
            }
        }
    }

}

@Composable
fun CodesContent(
    modifier: Modifier = Modifier,
    codes: List<String> = List(10) { "$it" },
    progress: Float = 1f, // общее время таймера в секундах,
    timeRemain: Int = 20
) {
    Surface(modifier = modifier) {
        LazyColumn(modifier = modifier.padding(10.dp)) {
            items(codes) { code ->
                CodeItem(
                    code = code,
                    timerProgress = progress,
                    timerRemaining = timeRemain
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun CodePreview() {
    RequestCodesAppTheme {
        CodesContent()
    }
}