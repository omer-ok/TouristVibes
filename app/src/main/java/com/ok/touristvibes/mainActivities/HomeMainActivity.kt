package com.ok.touristvibes.mainActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ok.touristvibes.navigation.homeMainNavigation.HomeMainAppNavigation
import com.ok.touristvibes.ui.theme.TouristVibesTheme

//@AndroidEntryPoint
class HomeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            TouristVibesTheme (darkTheme = false) {
                HomeMainAppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeMainPreview() {
    TouristVibesTheme {
        HomeMainAppNavigation()
    }
}