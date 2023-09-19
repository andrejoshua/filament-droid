package com.andre.apps.filamentdroid.ui.second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andre.apps.filamentdroid.design.BackgroundInfo
import com.andre.apps.filamentdroid.design.BackgroundSecondary
import com.andre.apps.filamentdroid.design.FilamentDroidTheme
import com.andre.apps.filamentdroid.domain.User
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Screen() {
    FilamentDroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundSecondary
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
}

@Composable
fun ModelView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Model is here")
    }
}

@Composable
fun ProfileView() {
    val vm: SecondPageViewModel = hiltViewModel()
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
            .padding(horizontal = 14.dp),
        contentPadding = PaddingValues(
            top = 14.dp,
            bottom = 14.dp
        )
    ) {
        item {
            Text(
                text = "My profile",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.size(14.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    model = user?.avatarUrl,
                    contentDescription = "User avatar URL",
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.size(36.dp))
            AddInformationSection(
                title = "Personal information",
                information = mapOf(
                    "Username" to (user?.username ?: ""),
                    "Email" to (user?.email ?: ""),
                    "Phone number" to (user?.phoneNumber ?: ""),
                    "Date of birth" to (user?.birthDateAsString ?: "")
                ),
            )
            Spacer(modifier = Modifier.size(28.dp))
            AddInformationSection(
                title = "Address",
                information = mapOf(
                    "Country" to (user?.country ?: ""),
                    "City" to (user?.city ?: ""),
                    "Street name" to (user?.streetName ?: ""),
                    "Street address" to (user?.streetAddress ?: ""),
                    "Zip code" to (user?.zipCode ?: "")
                ),
            )
            Spacer(modifier = Modifier.size(28.dp))
            AddInformationSection(
                title = "Subscription",
                information = mapOf(
                    "Plan" to (user?.plan ?: ""),
                    "Status" to (user?.status ?: ""),
                    "Payment method" to (user?.paymentMethod ?: ""),
                    "Term" to (user?.term ?: ""),
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
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(BackgroundInfo)
        ) {
            for (index in 0 until entries.count()) {
                val item = entries.get(index = index)
                InformationItem(label = item.key, value = item.value)
                if (index < entries.lastIndex)
                    Divider(color = Color.White, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun InformationItem(label: String, value: String) {
    Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(0.6f),
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = value,
            modifier = Modifier.weight(0.4f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
