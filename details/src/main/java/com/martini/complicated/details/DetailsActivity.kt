@file:OptIn(ExperimentalMaterial3Api::class)

package com.martini.complicated.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.martini.complicated.details.ui.theme.ComplicatedTheme
import com.martini.complicated.details.ui.theme.shimmer
import org.koin.androidx.compose.koinViewModel

class DetailsActivity : ComponentActivity() {

    companion object {
        const val songId = "songId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComplicatedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Complicated(
                        onBack = ::onBack
                    )
                }
            }
        }
    }

    private fun onBack() {
        finish()
    }
}

@Composable
fun Complicated(
    onBack: () -> Unit,
    getSongDetailsViewModel: GetSongDetailsViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Details") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        when (val state = getSongDetailsViewModel.state.value) {
            is GetSongDetailsState.Loading -> Loading(it)
            is GetSongDetailsState.Failure -> Failure(it)
            is GetSongDetailsState.Loaded -> Loaded(
                paddingValues = it,
                songDetails = state.songDetail
            )
        }
    }
}

@Composable
fun Loading(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Failure(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Icon(Icons.Filled.Info, contentDescription = "Failure")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Failed to get song details")
    }
}

@Composable
fun Loaded(
    paddingValues: PaddingValues,
    songDetails: SongDetails
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(songDetails.imageURL)
                .crossfade(true)
                .build(),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shimmer()
                )
            },
            contentDescription = songDetails.name
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Featuring",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
                items(songDetails.residents) {
                    Box {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.photoURL)
                                .crossfade(true)
                                .build(),
                            loading = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .shimmer()
                                )
                            },
                            contentDescription = it.name
                        )
                        Text(
                            text = it.name,
                            modifier = Modifier.align(Alignment.BottomCenter)
                                .background(Color.Black.copy(0.4f))
                                .fillMaxWidth()
                                .padding(16.dp),
                        )
                    }
                }
            }
        }
    }
}

