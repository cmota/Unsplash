package com.cmota.unsplash.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.cmota.unsplash.R
import com.cmota.unsplash.components.AddImagePreview
import com.cmota.unsplash.ui.theme.colorAccent

private const val TAG = "MainActivity"

@ExperimentalWearMaterialApi
class MainActivity : ComponentActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        unsplashViewModel.fetchImages()

        setContent {

            val images = unsplashViewModel.images.observeAsState()

            MaterialTheme {
                val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()

                Scaffold(
                    timeText = {
                        stringResource(id = R.string.app_name)
                    },
                    vignette = {
                        Vignette(vignettePosition = VignettePosition.TopAndBottom)
                    },
                    positionIndicator = {
                        PositionIndicator(
                            scalingLazyListState = scalingLazyListState
                        )

                    }
                ) {
                    ScalingLazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(10.dp),
                        verticalArrangement = Arrangement.Center,
                        state = scalingLazyListState
                    ) {

                        items(images.value?.size ?: 0) { index ->
                            Log.d(TAG, "Images retrieved=${images.value?.size}")

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = colorAccent,
                                        text = images.value!![index].user.username
                                    )

                                    AddImagePreview(
                                        url = images.value!![index].urls.small ?: "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                    )
                                }
                        }
                    }
                }
            }
        }
    }
}