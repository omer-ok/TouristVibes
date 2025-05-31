package com.ok.touristvibes.addProduct

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.navigation.homeMainNavigation.HomeMainAppScreens
import com.ok.touristvibes.ui.theme.TouristVibesTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TouristVibesAddProductPreview() {
    TouristVibesTheme {
        AddProduct(null)
    }
}
@Composable
fun AddProductOnBack (onExitApp: () -> Unit) {
    BackHandler(enabled = true) {
        onExitApp()
    }
}


@Composable
fun AddProduct(navController: NavController?){
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.londonphone),
                contentScale = ContentScale.FillWidth
            ),
    ) {
        Surface(modifier = Modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .align(Alignment.Center),
            // .background(colorResource(R.color.box_bg), shape = RoundedCornerShape(10.dp)),
            color = colorResource(R.color.box_bg),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(){
               /* TopSignUpHeading()
                LabelView("Your Full Name")*/

                AddImage(navController)

              /*  LabelView("Your Email Address ")
                TextInputField("Ali@gmail.com")
                LabelView("Your Nationality (optional) ")
                DropDownField("Choose your nationality")*/
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
                        AddProductButton(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun AddImage(navController: NavController?){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 16.dp, 0.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            onClick = {
               // CameraPreviewContent(viewModel,modifier)
                navController?.navigate(route = HomeMainAppScreens.CameraXScreen.name)
            },
            border = BorderStroke(1.dp, colorResource(R.color.white)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val painter: Painter? =painterResource(id = R.drawable.camera)
                Image(modifier = Modifier
                    .height(80.dp)
                    .width(80.dp),
                    painter = painter!!,
                    contentDescription = "image description",
                    contentScale = ContentScale.FillWidth)
            }
            Row(modifier = Modifier
                .wrapContentWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelImageView("Share your Vibe")
            }
        }
    }

    TextInputDescriptionField("Enter decription defining the vibe of the place ")

}

@Composable
fun LabelImageView(label :String){
    Row(modifier = Modifier
        .padding(8.dp, 0.dp, 8.dp, 8.dp),

        horizontalArrangement = Arrangement.Center) {
        Text(modifier = Modifier,
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 23.sp,
                fontWeight = FontWeight(500),
                color = colorResource(R.color.black),
            )
        )
    }
}

@Composable
fun TextInputDescriptionField(hint  :String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(108.dp)
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
fun AddProductButton(navController: NavController?){
    Button(modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(43.dp)
        .padding(16.dp, 0.dp, 16.dp, 0.dp),
        onClick = {
           // navController?.navigate(route = TouristVibeOnBoardingScreens.OtpVerificationScreen.name)
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

