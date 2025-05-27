package com.ok.touristvibes.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.models.TourData
import com.ok.touristvibes.models.getTourDetails
import com.ok.touristvibes.ui.theme.TouristVibesTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailPreview() {
    TouristVibesTheme {
        ProductDetailScreen(null,1)
    }
}

@Composable
fun ProductDetailScreen(navController: NavController?, foodId:Int){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.white))){
        Scaffold(
            topBar = { AddDetailScreenAppBar(navController) },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding),
                ) {
                    DetailScreenView(getTourDetails(foodId))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenView(tourData : TourData) {
    var rating by remember { mutableFloatStateOf(1f) } //default rating will be 1
    var painter: Painter? = null
    when (tourData.image) {
        "TowerLondon" -> {
            painter = painterResource(id = R.drawable.towerlondon)
        }

        "LondonEye" -> {
            painter = painterResource(id = R.drawable.londoneye)
        }

        "UberBoat" -> {
            painter = painterResource(id = R.drawable.uberboat)
        }

        "Shard" -> {
            painter = painterResource(id = R.drawable.shard)
        }

        "Westminster" -> {
            painter = painterResource(id = R.drawable.westminsterabbey)
        }

        "PaulCathedral" -> {
            painter = painterResource(id = R.drawable.paulcathedral)
        }

        "BigBen" -> {
            painter = painterResource(id = R.drawable.bigben)
        }

        "WindsorCastle" -> {
            painter = painterResource(id = R.drawable.castle)
        }

        "GreatFireofLondon" -> {
            painter = painterResource(id = R.drawable.londonfire)
        }

        "MocoMuseum" -> {
            painter = painterResource(id = R.drawable.mocolondon)
        }

        else -> {
            painter = painterResource(id = R.drawable.towerlondon)
        }
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = {
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .height(200.dp),
                    painter = painter!!,
                    contentDescription = "image description",
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = tourData.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_bold)),
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.black),
                            )
                        )
                           Text(modifier = Modifier,
                            text = tourData.name,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(R.color.black),
                            )
                        )
                    }

                        //AddToCartButton(null)
                    }
                    Row {
                        StarRatingBar(
                            maxStars = 5,
                            rating = tourData.rating,
                            onRatingChanged = {
                                rating = it
                            }
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 6.dp),
                            text = tourData.rating.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_medium)),
                                fontWeight = FontWeight(500),
                                color = colorResource(R.color.black),
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp),
                                text = tourData.description,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.lexend_deca_medium)),
                                    fontWeight = FontWeight(500),
                                    color = colorResource(R.color.black),
                                )
                            )
                        }
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddDetailScreenAppBar(navController: NavController?) {
        TopAppBar(
            modifier = Modifier,
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        text = "The Tourist Vibe",
                        style = TextStyle(
                            fontSize = 40.sp,
                            lineHeight = 1000.sp,
                            textDecoration = TextDecoration.Underline,
                            fontFamily = FontFamily(Font(R.font.great_vibes_regular)),
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(R.color.login_heading),
                        )
                    )
                }
            },
            navigationIcon = {
                IconButton({
                    navController?.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "menu items"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.app_bar_color)),
        )
    }
