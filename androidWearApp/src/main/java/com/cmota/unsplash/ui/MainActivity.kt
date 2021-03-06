package com.cmota.unsplash.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import com.cmota.unsplash.components.AddImagePreview
import com.cmota.unsplash.ui.theme.colorAccent
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@ExperimentalWearMaterialApi
class MainActivity : ComponentActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        unsplashViewModel.fetchImages()

        setContent {

            val images = unsplashViewModel.images.observeAsState()

            MaterialTheme {
                val coroutineScope = rememberCoroutineScope()
                val focusRequester = remember { FocusRequester() }
                val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()

                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

                Scaffold(
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
                        modifier = Modifier
                            .fillMaxSize()
                            .onRotaryScrollEvent {
                                coroutineScope.launch {
                                    scalingLazyListState.animateScrollBy(it.verticalScrollPixels)
                                }
                                true
                            }
                            .focusRequester(focusRequester)
                            .focusable(),
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