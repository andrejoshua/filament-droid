package com.andre.apps.filamentdroid.ui.second

import android.view.SurfaceView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.andre.apps.filamentdroid.R
import com.andre.apps.filamentdroid.design.BackgroundInfo
import com.andre.apps.filamentdroid.design.BackgroundSecondary
import com.andre.apps.filamentdroid.domain.User
import com.andre.apps.filamentdroid.renderer.Renderer3d
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecondScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val pagerState = rememberPagerState(pageCount = {
            2
        })
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ModelView()
                1 -> ProfileView()
            }
        }
    }
}

@Composable
fun ModelView() {
    val viewer: Renderer3d = remember { Renderer3d() }
    val lifecycleOwner = LocalLifecycleOwner.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = { context ->
            SurfaceView(context).apply {
                viewer.onSurface(this, lifecycleOwner.lifecycle)
                viewer.turnOn()
            }
        })
    }
}

@Composable
fun ProfileView() {
    val vm: SecondViewModel = hiltViewModel()
    val user by vm.getUser().observeAsState(null)

    ProfileSubview(user = user)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileSubview(user: User?) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSecondary)
            .padding(
                horizontal = dimensionResource(R.dimen.margin_default)
            ),
        contentPadding = PaddingValues(
            top = dimensionResource(R.dimen.margin_default),
            bottom = dimensionResource(R.dimen.margin_default)
        )
    ) {
        item {
            Text(
                text = stringResource(R.string.profile_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_default)))
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    model = user?.avatarUrl,
                    contentDescription = stringResource(R.string.content_desc_avatar),
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_large)))
            AddInformationSection(
                title = stringResource(R.string.profile_personal_info),
                information = mapOf(
                    stringResource(R.string.profile_username) to (user?.username ?: ""),
                    stringResource(R.string.profile_email) to (user?.email ?: ""),
                    stringResource(R.string.profile_phone_number) to (user?.phoneNumber ?: ""),
                    stringResource(R.string.profile_date_of_birth) to (user?.birthDateAsString
                        ?: "")
                ),
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_medium)))
            AddInformationSection(
                title = stringResource(R.string.profile_address),
                information = mapOf(
                    stringResource(R.string.profile_country) to (user?.country ?: ""),
                    stringResource(R.string.profile_city) to (user?.city ?: ""),
                    stringResource(R.string.profile_street_name) to (user?.streetName ?: ""),
                    stringResource(R.string.profile_street_address) to (user?.streetAddress
                        ?: ""),
                    stringResource(R.string.profile_zip_code) to (user?.zipCode ?: "")
                ),
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_medium)))
            AddInformationSection(
                title = stringResource(R.string.profile_subscription),
                information = mapOf(
                    stringResource(R.string.profile_plan) to (user?.plan ?: ""),
                    stringResource(R.string.profile_status) to (user?.status ?: ""),
                    stringResource(R.string.profile_payment_method) to (user?.paymentMethod
                        ?: ""),
                    stringResource(R.string.profile_term) to (user?.term ?: ""),
                ),
            )
        }
    }
}

@Composable
fun AddInformationSection(title: String, information: Map<String, String>) {
    val entries = information.entries.toList()

    Column {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.space_default)))
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.corner_info)))
                .background(BackgroundInfo)
        ) {
            for (index in 0 until entries.count()) {
                val item = entries.get(index = index)
                InformationItem(label = item.key, value = item.value)
                if (index < entries.lastIndex) Divider(color = Color.White, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun InformationItem(label: String, value: String) {
    Row(
        modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen.margin_default),
            vertical = dimensionResource(R.dimen.margin_default)
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(0.6f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.space_small)))
        Text(
            text = value,
            modifier = Modifier.weight(0.4f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
