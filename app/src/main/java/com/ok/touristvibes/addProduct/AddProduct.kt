package com.ok.touristvibes.addProduct

import androidx.activity.compose.BackHandler
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ok.touristvibes.R
import com.ok.touristvibes.home.StarRatingBar
import com.ok.touristvibes.home.homeViewModel.CameraPreviewViewModel
import com.ok.touristvibes.models.TourData
import com.ok.touristvibes.navigation.onBoardingNavigation.TouristVibeOnBoardingScreens
import com.ok.touristvibes.navigation.utilz.LabelView
import com.ok.touristvibes.onboarding.DropDownField
import com.ok.touristvibes.onboarding.SignUpButton
import com.ok.touristvibes.onboarding.TextInputField
import com.ok.touristvibes.onboarding.TopSignUpHeading
import com.ok.touristvibes.onboarding.UserAgrement
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

                AddImage()

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
fun AddImage(modifier: Modifier = Modifier){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 16.dp, 0.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val viewModel = remember { CameraPreviewViewModel() }
        CameraPreviewScreen(viewModel)
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            onClick = {
               // CameraPreviewContent(viewModel,modifier)

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
                LabelImageView("Add Image")
            }
        }
    }

    TextInputDescriptionField("Enter decription defining the vibe of the place ")

}

@Composable
fun LabelImageView(label :String){
    Row(modifier = Modifier
        .padding(22.dp, 0.dp, 0.dp, 0.dp),

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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPreviewScreen(viewModel: CameraPreviewViewModel,
                        modifier: Modifier = Modifier) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    if (cameraPermissionState.status.isGranted) {

        CameraPreviewContent(viewModel,modifier)
    } else {
        Column(
            modifier = modifier.fillMaxSize().wrapContentSize().widthIn(max = 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown,
                // then gently explain why the app requires this permission
                "Whoops! Looks like we need your camera to work our magic!" +
                        "Don't worry, we just wanna see your pretty face (and maybe some cats).  " +
                        "Grant us permission and let's get this party started!"
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                "Hi there! We need your camera to work our magic! ✨\n" +
                        "Grant us permission and let's get this party started! \uD83C\uDF89"
            }
            Text(textToShow, textAlign = TextAlign.Center)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Unleash the Camera!")
            }
        }
    }
}

@Composable
fun CameraPreviewContent(
    viewModel: CameraPreviewViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    surfaceRequest?.let { request ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = modifier
        )
    }
}