package com.ok.touristvibes.navigation.utilz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ok.touristvibes.R

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



