package com.ok.touristvibes.mainActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ok.touristvibes.navigation.onBoardingNavigation.TouristVibeOnBoardingNavigation
import com.ok.touristvibes.ui.theme.TouristVibesTheme

class OnBoardingMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            TouristVibesTheme(darkTheme = false) {
                TouristVibeOnBoardingNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EatExpressPreview() {
    TouristVibesTheme {
        TouristVibeOnBoardingNavigation()
    }
}
