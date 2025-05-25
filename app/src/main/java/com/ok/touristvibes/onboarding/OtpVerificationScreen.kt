@file:OptIn(ExperimentalMaterial3Api::class)

package com.ok.touristvibes.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.navigation.onBoardingNavigation.TouristVibeOnBoardingScreens
import com.ok.touristvibes.ui.theme.TouristVibesTheme


@Composable
fun OtpVerificationScreen(navController: NavController){
    OtpVerificationMain(navController = navController)
}

@Composable
fun OtpVerificationMain(navController: NavController?){

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier,
                title = {
                    Row (modifier = Modifier
                        .fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "OTP Verification",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight(600),
                                color = colorResource(R.color.black),

                                )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(R.color.white) )
            )
        }


    ) { contentPadding ->
        // Screen content

        Text(text = "", modifier = Modifier.padding(contentPadding))
        Row (modifier = Modifier
            .padding(contentPadding)){
            Divider(
                color = colorResource(R.color.divider),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.CenterVertically),
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()) {
            Column(modifier = Modifier
                .offset(0.dp, 140.dp),) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center){
                    Text(
                        text = "We have sent a verification code to",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.black),

                            )
                    )
                }


                Row (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center){
                    Text(
                        text = "+971-9898989898",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                            fontWeight = FontWeight(600),
                            color = colorResource(R.color.black),

                            )
                    )
                }
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .offset(0.dp, 160.dp),
                horizontalArrangement = Arrangement.Center){
               // OtpInput()
            }
            ResendOtpButton(navController)
        }
    }
}

/*@Composable
fun OtpInput() {
    // a mutable state to handle OTP value changes…
    var otpValue: String by remember { mutableStateOf("") }

    // this config will be used for each cell
    val defaultCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.Black,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(
            color = Color.Black
        )
    )

    OhTeePeeInput(
        value = otpValue,
        onValueChange = { newValue, isValid ->
            otpValue = newValue
        },
        configurations = OhTeePeeConfigurations.withDefaults(
            activeCellConfig = defaultCellConfig,
            cellsCount = 5,
            emptyCellConfig = defaultCellConfig,
            cellModifier = Modifier
                .padding(horizontal = 4.dp)
                .size(48.dp),
        ),
    )
}*/

@Composable
fun ResendOtpButton(navController: NavController?){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Button(modifier = Modifier
            .requiredWidth(228.dp)
            .requiredHeight(40.dp)
            .offset(0.dp,180.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
            onClick = {
                navController?.navigate(route = TouristVibeOnBoardingScreens.OtpVerificationScreen.name)
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.divider)),
            //, shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
        ) {
            Text(modifier = Modifier
                .align(Alignment.CenterVertically),
                text = "Resend SMS in 17",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                    fontWeight = FontWeight(600),
                    color = colorResource(R.color.hint_txt_color),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OtpVerificationScreenPreview(){
    TouristVibesTheme {
        OtpVerificationMain(null)
    }
}