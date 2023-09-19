package com.andre.apps.filamentdroid.ui.first

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.andre.apps.filamentdroid.R
import com.andre.apps.filamentdroid.design.BackgroundSecondary
import com.andre.apps.filamentdroid.design.buttonStyle

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
        LazyRow(
            modifier = Modifier.weight(1.0f),
            contentPadding = PaddingValues(
                start = dimensionResource(R.dimen.margin_default),
                end = dimensionResource(R.dimen.margin_default)
            )
        ) {
            items(count = 4) { pos ->
                ModelItem(isSelected = currentPosition == pos, onItemClick = {
                    vm.selectItem(pos)
                })
            }
        }
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
fun ModelItem(isSelected: Boolean, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(height = dimensionResource(R.dimen.box_height))
            .aspectRatio(ratio = 542f / 622f)
            .clickable {
                onItemClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Model goes here"
        )
    }
}