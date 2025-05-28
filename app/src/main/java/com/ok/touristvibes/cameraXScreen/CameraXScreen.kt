package com.ok.touristvibes.cameraXScreen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ok.touristvibes.ui.theme.TouristVibesTheme
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.ImageCaptureResult
import com.ujizin.camposer.state.ImplementationMode
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import java.io.File


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TouristVibesCameraXPreview() {
    TouristVibesTheme {
       // CameraXScreen(null)
        CameraSwitch()
    }
}
@Composable
fun AddProductOnBack (onExitApp: () -> Unit) {
    BackHandler(enabled = true) {
        onExitApp()
    }
}

@Composable
fun CameraXScreen(navController: NavController?){
    //val viewModel = remember { CameraPreviewViewModel() }
    //CameraPreviewScreen()
    CameraSwitch()
}


@Composable
fun CameraPreviewScreen(){
 /*   val cameraState = rememberCameraState()
    var camSelector by rememberCamSelector(CamSelector.Back)*/

    CameraSwitch()
 /*   CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
    ) {


        // Use back camera
        camSelector = CamSelector.Back
        // Use front camera
        camSelector = CamSelector.Front
        // Inverse camera selector
        camSelector = camSelector.inverse

        // Camera Preview UI
        ClickPic(cameraState)
    }*/

}

@Composable
fun CameraSwitch() {
    var implementationMode by remember { mutableStateOf(ImplementationMode.Compatible) }
    val cameraState = rememberCameraState()
    var camSelector by rememberCamSelector(CamSelector.Back) // Or CamSelector.Front
    val context = LocalContext.current
    CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
        implementationMode = implementationMode
    ) {
        /*Button(onClick = {
           // camSelector = camSelector. // Switch Camera
            implementationMode = implementationMode.inverse
        }) {
            Text("Switch")
        }*/
        /*Surface(
            onClick = {
                val file = File("TouristVibeFiles")
                cameraState.takePicture(file) { result ->
                    if (result is ImageCaptureResult.Success) { // result.savedUri might be useful to you
                        Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        ) {
            Text(text = "Take picture")
        }*/
    }

    /*var camSelector by rememberCamSelector(CamSelector.Back) // Or CamSelector.Front
    CameraPreview(camSelector = camSelector) {
        Button(onClick = {
            camSelector = camSelector.inverse // Switch Camera
        }) {
            Text("Switch")
        }
    }*/
}

/*@Composable
fun ClickPic(cameraState:CameraState){
    Button(modifier = Modifier
        .requiredWidth(80.dp)
        .requiredHeight(80.dp)
        .padding(16.dp, 0.dp, 16.dp, 0.dp),
        onClick = {
            //navController?.navigate(route = TouristVibeOnBoardingScreens.OtpVerificationScreen.name)
            //val file = File()
            val file = File("TouristVibeFiles")
            // Using files
            cameraState.takePicture(file) { result -> *//* ... *//* }
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.button_color)),
        //, shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
    ) {
        Text(modifier = Modifier
            .align(Alignment.CenterVertically),
            text = "Click",
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 27.sp,
                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                fontWeight = FontWeight(600),
                color = colorResource(R.color.white),
            )
        )
    }
}*/
/*
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPreviewScreen(viewModel: CameraPreviewViewModel,
                        modifier: Modifier = Modifier
) {
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
}*/
