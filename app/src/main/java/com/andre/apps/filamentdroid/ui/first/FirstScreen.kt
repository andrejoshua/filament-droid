package com.andre.apps.filamentdroid.ui.first

import android.view.SurfaceView
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.andre.apps.filamentdroid.R
import com.andre.apps.filamentdroid.design.BackgroundSecondary
import com.andre.apps.filamentdroid.design.buttonStyle
import com.andre.apps.filamentdroid.renderer.Renderer3d

@Composable
fun FirstScreen(onButtonClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Models(onButtonClick)
    }
}

@Composable
fun Models(onButtonClick: () -> Unit) {
    val vm: FirstViewModel = hiltViewModel()
    val currentPosition by vm.getSelectedPosition().observeAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(R.dimen.margin_default)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = dimensionResource(R.dimen.margin_default)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))
            ) {
                for (position in 0 until 4) {
                    ModelItem(
                        position = position,
                        isSelected = position == currentPosition,
                        onItemClick = {
                            vm.selectItem(position)
                        })
                }
            }
        }
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_default)))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier.size(
                    width = dimensionResource(R.dimen.button_width),
                    height = dimensionResource(R.dimen.button_height)
                ),
                onClick = {
                    onButtonClick.invoke()
                },
                colors = ButtonDefaults.buttonColors(containerColor = BackgroundSecondary)
            ) {
                Text(
                    text = stringResource(R.string.button_ok),
                    style = buttonStyle
                )
            }
        }
    }
}

@Composable
fun ModelItem(position: Int, isSelected: Boolean, onItemClick: () -> Unit) {
    val viewer = remember(key1 = position) {
        Renderer3d()
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width = dimensionResource(R.dimen.button_width)),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = { context ->
            SurfaceView(context).apply {
                setOnClickListener {
                    onItemClick.invoke()
                }
                viewer.onSurface(this, lifecycleOwner.lifecycle)
            }
        }, update = {
            if (isSelected) {
                viewer.turnOn()
            } else {
                viewer.turnOff()
            }
        })
    }
}