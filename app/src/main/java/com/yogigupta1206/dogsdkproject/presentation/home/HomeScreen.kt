package com.yogigupta1206.dogsdkproject.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.yogigupta1206.dogsdkproject.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(
    onNavigateToDogsList: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiHomeState.collectAsState()
    val photoNumber by viewModel.photoNumber.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is HomeViewModel.UiEvent.NavigateToDogsList -> {
                    onNavigateToDogsList(event.photoNumber)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        content = { padding ->
            HomeScreenContent1(
                padding,
                uiState,
                photoNumber,
                viewModel::getNextDogImage,
                viewModel::getPreviousDogImage,
                viewModel::fetchImage,
                viewModel::onPhotoNumberChange,
                viewModel::submit
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    Column {
        TopAppBar(title = { Text(text = "Star Wars Blaster Tournament") },
            navigationIcon = { /* Drawer Menu Icon (Optional) */ })
    }
}

@Composable
fun HomeScreenContent1(
    padding: PaddingValues,
    uiState: HomeUiState,
    photoNumber: String,
    onNavigateToNext: () -> Unit,
    onNavigateToPrevious: () -> Unit,
    onRetry: () -> Unit,
    onPhotoNoChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
    ){
        ImageView1(uiState, onRetry)
        Spacer(modifier = Modifier.height(16.dp))
        NextPrevNavigation(
            uiState = uiState,
            onNavigateToNext = onNavigateToNext,
            onNavigateToPrevious =onNavigateToPrevious,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ImageListDataView(
            photoNumber = photoNumber,
            onPhotoNoChange = onPhotoNoChange,
            onSubmit = onSubmit
        )
    }
}

@Composable
fun ImageView1(
    uiState: HomeUiState,
    onRetry: () -> Unit,
) {
    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxWidth().height(400.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        !uiState.errorMessage.isNullOrBlank()-> {
            ErrorView(message = uiState.errorMessage, onRetry = onRetry)
        }

        else ->{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiState.image?.url)
                    .crossfade(true)
                    .dispatcher(Dispatchers.IO)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .transformations(RoundedCornersTransformation(0.5f))
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = "Dog Image",
                modifier = Modifier.height(400.dp),
                contentScale = ContentScale.Crop
            )
        }
    }

}

@Composable
fun NextPrevNavigation(
    uiState: HomeUiState,
    onNavigateToNext: () -> Unit,
    onNavigateToPrevious: () -> Unit,
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onNavigateToPrevious() },
            enabled = uiState.isPreviousImageButtonEnabled
        ) {
            Text("Previous")
        }
        Button(onClick = { onNavigateToNext() }) {
            Text("Next")
        }
    }
}

@Composable
fun ImageListDataView(
    photoNumber: String,
    onPhotoNoChange: (String) -> Unit,
    onSubmit: () -> Unit
){
    TextField(
        value = photoNumber,
        onValueChange = onPhotoNoChange,
        label = { Text("Enter number (1-10)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Button(
        onClick = onSubmit,
    ) {
        Text("Submit")
    }

}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().height(400.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

