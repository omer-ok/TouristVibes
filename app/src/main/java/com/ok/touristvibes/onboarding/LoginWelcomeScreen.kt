@file:OptIn(ExperimentalMaterial3Api::class)

package com.ok.touristvibes.onboarding

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.mainActivities.HomeMainActivity
import com.ok.touristvibes.mainActivities.OnBoardingMainActivity
import com.ok.touristvibes.navigation.onBoardingNavigation.TouristVibeOnBoardingScreens
import com.ok.touristvibes.onboarding.model.Country
import com.ok.touristvibes.ui.theme.TouristVibesTheme


@Composable
fun LoginWelcomeScreen(navController: NavController){
    LoginMainScreen(navController = navController)
}

@Composable
fun LoginMainScreen(navController: NavController?) {

    Box(
        modifier = Modifier

            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.onboarding_bg),
                contentScale = ContentScale.FillWidth
            ),
    ) {

        Surface(modifier = Modifier
            .wrapContentWidth()
            .requiredHeight(200.dp)

            .align(Alignment.Center)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
            //.background(Color.Red, shape = RoundedCornerShape(10.dp)),
            color = colorResource(R.color.box_bg),
            shape = RoundedCornerShape(10.dp)) {
            Box {
                TopHeading(navController)
                PhoneNoInputField(navController)
                LoginButton(navController)
            }
        }
        UserAgrement()
    }
}


@Composable
fun UserAgrement(){
    Box(modifier = Modifier
        .offset(0.dp, 750.dp)
        .wrapContentHeight()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
        ){
            Row(modifier = Modifier
                .wrapContentHeight()
            ) {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(0.dp, 10.dp)
                ){
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "By Continuing, you agree to our",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.divider),
                        )
                    )
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(0.dp, 0.dp, 0.dp, 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(modifier = Modifier
                            .padding(0.dp,0.dp,20.dp,0.dp),
                            text = "Terms of Service",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight(500),
                                color = colorResource(R.color.white),
                                textDecoration = TextDecoration.combine(
                                    listOf(
                                        TextDecoration.Underline,
                                    )
                                )
                            )

                        )
                        Text(modifier = Modifier
                            .padding(0.dp,0.dp,20.dp,0.dp),
                            text = "Privacy Policy",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight(500),
                                color = colorResource(R.color.white),
                                textDecoration = TextDecoration.combine(
                                    listOf(
                                        TextDecoration.Underline
                                    )
                                )
                            )
                        )
                        Text(modifier = Modifier,
                            text = "Content Policy",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight(500),
                                color = colorResource(R.color.white),
                                textDecoration = TextDecoration.combine(
                                    listOf(
                                        TextDecoration.Underline
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun PhoneNoInputField(navController: NavController?){
    Card(
        modifier = Modifier
            .offset(0.dp, 65.dp)
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .requiredHeight(48.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, colorResource(R.color.divider)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.Start) {
            var openBottomSheet by rememberSaveable { mutableStateOf(false) }
            var selectedCountry by remember { mutableStateOf<Country?>(null) }
            Surface(modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
                color = Color.White,
                onClick = {
                    //openBottomSheet = true
                    navController?.navigate(route = TouristVibeOnBoardingScreens.CountryCodeSelectionScreen.name)
                    /*CountryInfoScreen(navController = navController!!, onItemSelected = {

                    })*/
                }){

                Row(modifier = Modifier
                    .fillMaxHeight()
                ) {
                    Spacer(modifier = Modifier.requiredWidth(10.dp))
                    Text(modifier = Modifier
                        .align(Alignment.CenterVertically),
                        text = "+971",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.black),
                        )
                    )

                    Spacer(modifier = Modifier.requiredWidth(3.dp))
                    Image(modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.drop_down_arrow),
                        contentDescription = "image description",
                        contentScale = ContentScale.None)
                    Spacer(modifier = Modifier.requiredWidth(10.dp))
                }
            }
            var value by remember { mutableStateOf("") }

            TextField(
                value = value,
                onValueChange = { value = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = colorResource(R.color.black),
                    disabledTextColor =  Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                placeholder = {Text(text = "Enter Phone No",
                    style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                    fontWeight = FontWeight(300),
                    color = colorResource(R.color.hint_txt_color),

                    ))},
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.black)),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Composable
fun LoginButton(navController: NavController?){
    val mContext = LocalContext.current
    val activity = OnBoardingMainActivity()
    // on below line we are finishing activity.
    Button(modifier = Modifier
        .offset(0.dp, 140.dp)
        .fillMaxWidth()
        .requiredHeight(40.dp)
        .padding(15.dp, 0.dp, 15.dp, 0.dp),
        onClick = {
            mContext.startActivity(Intent(mContext, HomeMainActivity::class.java))
            activity.finish()
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.button_color))
    ) {
        Text(modifier = Modifier
            .align(Alignment.CenterVertically),
            text = "Continue",
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 27.sp,
                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                fontWeight = FontWeight(600),
                color = colorResource(R.color.white),
            )
        )
    }
}


@Composable
fun TopHeading(navController: NavController?){
    Column(modifier = Modifier
        .padding(0.dp, 15.dp, 0.dp, 0.dp)
        .wrapContentHeight(),
        verticalArrangement = Arrangement.Top
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(20.dp)
            .clickable {
                navController?.navigate(route = TouristVibeOnBoardingScreens.SignUpScreen.name)
            },
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(
                color = colorResource(R.color.divider),
                modifier = Modifier
                    .width(100.dp)
                    .height(1.dp)
                    .align(Alignment.CenterVertically),
            )
            Text(modifier = Modifier
                .align(Alignment.CenterVertically),
                text = " Log in or sign up ",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_medium)),
                    fontWeight = FontWeight(500),
                    color = colorResource(R.color.login_heading),

                    )
            )

            Divider(
                color = colorResource(R.color.divider),
                modifier = Modifier
                    .width(100.dp)
                    .height(1.dp)
                    .align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun LoginScreen (onExitApp: () -> Unit) {
    BackHandler(enabled = true) {
        onExitApp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EatExpressLoginPreview() {
    TouristVibesTheme {
        LoginMainScreen(null)
    }
}