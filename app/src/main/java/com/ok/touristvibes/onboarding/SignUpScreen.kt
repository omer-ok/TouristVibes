@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ok.touristvibes.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.ui.theme.TouristVibesTheme
import androidx.compose.material3.TextFieldColors
import com.ok.touristvibes.navigation.onBoardingNavigation.TouristVibeOnBoardingScreens


@Composable
fun SignUpScreen(navController: NavController){
    SignUpMainScreen(navController = navController)
}

@Composable
fun SignUpMainScreen(navController: NavController?) {
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.onboarding_bg),
                contentScale = ContentScale.FillWidth
            ),
    ) {
        Surface(modifier = Modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .align(Alignment.Center),
           // .background(colorResource(R.color.box_bg), shape = RoundedCornerShape(10.dp)),
            color = colorResource(R.color.box_bg),
            shape = RoundedCornerShape(10.dp)) {
            Column(){
                TopSignUpHeading()
                LabelView("Your Full Name")
                TextInputField("Muhammad Ali")
                LabelView("Your gender ")
                RadioButtons()
                LabelView("Your Email Address ")
                TextInputField("Ali@gmail.com")
                LabelView("Your Nationality (optional) ")
                DropDownField("Choose your nationality")
                Column(modifier = Modifier
                    .wrapContentWidth()
                    .padding(0.dp, 30.dp, 0.dp, 15.dp),
                    verticalArrangement = Arrangement.Bottom
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        SignUpButton(navController)
                    }
                }
            }
        }
        UserAgrement()
    }
}
@Composable
fun TopSignUpHeading(){
    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 10.dp),
        verticalArrangement = Arrangement.Top
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(20.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Divider(
                color = colorResource(R.color.divider),
                modifier = Modifier
                    .width(80.dp)
                    .height(1.dp)
                    .align(Alignment.CenterVertically),
            )
            Text(modifier = Modifier
                .align(Alignment.CenterVertically),
                text = " Complete Your Profile ",
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
                    .width(80.dp)
                    .height(1.dp)
                    .align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun LabelView(label :String){
    Row(modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth()
        .padding(16.dp, 14.dp, 0.dp, 5.dp)) {
        Text(modifier = Modifier,
            text = label,
            style = TextStyle(
                fontSize = 11.sp,
                lineHeight = 23.sp,
                fontWeight = FontWeight(300),
                color = colorResource(R.color.white),
            )
        )
    }
}
@Composable
fun TextInputField(hint  :String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        border = BorderStroke(1.dp, colorResource(R.color.divider)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
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
                placeholder = {
                    Text(hint,
                    style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                    fontWeight = FontWeight(300),
                    color = colorResource(R.color.hint_txt_color),

                    ))},
                maxLines = 2,
                textStyle = TextStyle(fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.black),),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .background(colorResource(R.color.white))
            )
        }
    }
}



@Composable
fun DropDownField(hint  :String){
    Card(
        modifier = Modifier
            .requiredHeight(48.dp)
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, colorResource(R.color.divider)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Spacer(modifier = Modifier.requiredWidth(10.dp))
            Text(modifier = Modifier
                .align(Alignment.CenterVertically),
                text = hint,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                    fontWeight = FontWeight(300),
                    color = colorResource(R.color.black),
                )
            )
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ){
                Image(modifier = Modifier
                    .padding(0.dp,0.dp,10.dp,0.dp),
                    painter = painterResource(id = R.drawable.drop_down_arrow),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                )
            }
        }
    }
}

@Composable
fun SignUpButton(navController: NavController?){
    Button(modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(43.dp)
        .padding(16.dp, 0.dp, 16.dp, 0.dp),
        onClick = {
            navController?.navigate(route = TouristVibeOnBoardingScreens.OtpVerificationScreen.name)
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.button_color)),
        //, shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
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
fun RadioButtons() {
    val radioOptions = listOf("Male", "Female")

    var selectedItem by remember {
        mutableStateOf(radioOptions[0])
    }

    Row(modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(1.dp)) {
        radioOptions.forEach { label ->
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(25.dp)
                    .selectable(
                        selected = (selectedItem == label),
                        onClick = { selectedItem = label },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (selectedItem == label){
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.radio_button_selected),
                        // screen readers will read the Text() compsable content
                        // if we pass label here, they end up reading the content twice
                        // so, pass null
                        contentDescription = null,
                        tint = colorResource(R.color.button_color)
                    )
                    Text(text = label,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_medium)),
                            fontWeight = FontWeight(500),
                            color = colorResource(R.color.white),

                            ))
                }else{
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.radios_unselected_bg),
                        // screen readers will read the Text() compsable content
                        // if we pass label here, they end up reading the content twice
                        // so, pass null
                        contentDescription = null,
                        tint = colorResource(R.color.hint_txt_color)
                    )
                    Text(text = label,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.white),

                            ))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EatExpressSignUpScreenPreview() {
    TouristVibesTheme {
        SignUpMainScreen(null)
    }
}