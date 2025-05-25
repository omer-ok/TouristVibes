@file:OptIn(ExperimentalMaterial3Api::class)

package com.ok.touristvibes.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
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
import com.ok.touristvibes.widgets.CountrySearchView
import kotlinx.coroutines.launch

@Composable
fun CountryInfoScreen(navController: NavController){
    CountryMain(navController)
}

@Composable
fun CountryMain(navController: NavController?){
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier,
                title = {

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController?.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(R.color.bar_color) ),
            )
        }
    ) { contentPadding ->
        // Screen content
        var searchValue by rememberSaveable { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(contentPadding),
            shape = RectangleShape,
            border = BorderStroke(0.dp, colorResource(R.color.divider)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier
                .requiredHeight(120.dp)
                .background(Color.White)) {
                Box(modifier = Modifier
                    .offset(0.dp,15.dp)){
                    CountrySearchView(searchValue) {
                        searchValue = it
                    }
                }
                Title()
            }
        }

        Column(modifier = Modifier.offset(0.dp,190.dp)) {
            Countries(searchValue) {
                scope.launch {
                    //sheetState.hide()
                    //onItemSelected(it)
                    navController?.popBackStack()
                }
            }
        }
    }
}

@Composable
fun TextInputSearchField(hint  :String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        border = BorderStroke(1.dp, colorResource(R.color.divider)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier
            .wrapContentHeight()
            .background(Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            var value by remember { mutableStateOf("") }
            Icon(
                modifier = Modifier.padding(start = 10.dp,end = 10.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                // screen readers will read the Text() compsable content
                // if we pass label here, they end up reading the content twice
                // so, pass null
                contentDescription = null,
                tint = colorResource(R.color.hint_txt_color)
            )
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
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                            fontWeight = FontWeight(300),
                            color = colorResource(R.color.hint_txt_color),

                            ))},
                maxLines = 2,
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.black)),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .background(colorResource(R.color.white))
            )
        }
    }
}

@Composable
fun Title(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .offset(0.dp, 28.dp)
        .padding(0.dp, 0.dp, 16.dp, 0.dp)){
        Icon(
            modifier = Modifier
                .padding(start = 0.dp,end = 20.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.red_bar_country),
            // screen readers will read the Text() compsable content
            // if we pass label here, they end up reading the content twice
            // so, pass null
            contentDescription = null,
            tint = colorResource(R.color.button_color)
        )
        Text(
            text = "Select Country Code",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                fontWeight = FontWeight(700),
                color = colorResource(R.color.heading_country_color),
                ))
    }
}
@Preview
@Composable
fun CountryInfoScreenPreview(){
    TouristVibesTheme {
        CountryMain(null)
    }
}