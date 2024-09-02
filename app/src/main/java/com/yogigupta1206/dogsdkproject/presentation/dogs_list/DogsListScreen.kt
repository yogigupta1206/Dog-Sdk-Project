package com.yogigupta1206.dogsdkproject.presentation.dogs_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.yogigupta1206.dogsdkproject.R
import com.yogigupta1206.dogsdkproject.domain.model.ImageData
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsListScreen(
    onNavigateBack: () -> Unit,
    viewModel: DogListViewModel = hiltViewModel()
) {

    val uiState by viewModel.dogListUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dogs List") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        content = { padding ->
            HomeScreenContent(
                padding,
                uiState,
                viewModel::fetchImage,
            )
        }
    )
}

@Composable
fun HomeScreenContent(
    padding: PaddingValues,
    uiState: DogListUiState,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(padding)
    ) {

        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            !uiState.errorMessage.isNullOrBlank()-> {
                ErrorView(message = uiState.errorMessage, onRetry = onRetry)
            }

            else ->{
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.imageList?.let {
                        items(it){ img ->
                            ImageView(img)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
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

@Composable
private fun ImageView(img: ImageData){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(img.url)
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