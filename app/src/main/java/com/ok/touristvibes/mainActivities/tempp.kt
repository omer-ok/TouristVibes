/*
package com.ok.touristvibes.mainActivities

//package com.ok.touristvibes.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import com.ok.touristvibes.models.TourData
import com.ok.touristvibes.models.getTourData
import com.ok.touristvibes.navigation.homeMainNavigation.HomeMainAppScreens
import com.ok.touristvibes.navigation.utilz.ImageProductPager
import com.ok.touristvibes.ui.theme.TouristVibesTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TouristVibesHomeMainPreview() {
    TouristVibesTheme {
        HomeMainApp(null)
    }
}
@Composable
fun HomeScreenOnBack (onExitApp: () -> Unit) {
    BackHandler(enabled = true) {
        onExitApp()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainApp(navController: NavController?){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Column(modifier = Modifier
        .fillMaxSize()){
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { AddAppBar(navController,scrollBehavior) },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = colorResource(R.color.white)
                ) {
                    Column(modifier = Modifier) {
                        SearchBar("Search vibes accross london")
                        Box(modifier = Modifier.height(20.dp)) {  }
                        Column(modifier = Modifier
                            .padding(16.dp, 0.dp, 16.dp, 16.dp),) {
                            ImageProductPager()
                        }
                        Text(modifier = Modifier
                            .padding(16.dp, 0.dp, 16.dp, 16.dp)
                            .align(Alignment.Start),
                            text = "Attractive Vibes",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 27.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                                fontWeight = FontWeight(600),
                                color = colorResource(R.color.black),
                            )
                        )
                        MainFoodList(navController)
                    }
                    FloatingActionButtons(navController)
                }
            }

        )
    }
}

@Composable
fun MainFoodList(navController: NavController?,foodList: List<TourData> = getTourData()){
    Column(modifier = Modifier.padding(12.dp)) {

        LazyVerticalGrid(
            modifier = Modifier
                .wrapContentSize(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = foodList){ foodList ->
                MovieRow(foodList,navController){ selectedFoodItem ->
                    Log.d("SelectedFoodItem",selectedFoodItem.name)
                    navController?.navigate(route = HomeMainAppScreens.ProductDetailScreen.name+"/"+selectedFoodItem.id)
                }
            }
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(tourData: TourData, navController: NavController?, onItemClick:(TourData) -> Unit ={}){
    var painter: Painter? =null
    var rating by remember { mutableFloatStateOf(1f) } //default rating will be 1
    var expanded by remember {
        mutableStateOf(false)
    }

    when(tourData.image){
        "TowerLondon" ->{
            painter =painterResource(id = R.drawable.towerlondon)
        }
        "LondonEye" ->{
            painter =painterResource(id = R.drawable.londoneye)
        }
        "UberBoat" ->{
            painter =painterResource(id = R.drawable.uberboat)
        }
        "Shard" ->{
            painter =painterResource(id = R.drawable.shard)
        }
        "Westminster" ->{
            painter =painterResource(id = R.drawable.westminsterabbey)
        }
        "PaulCathedral" ->{
            painter =painterResource(id = R.drawable.paulcathedral)
        }
        "BigBen" ->{
            painter =painterResource(id = R.drawable.bigben)
        }
        "WindsorCastle" ->{
            painter =painterResource(id = R.drawable.castle)
        }
        "GreatFireofLondon" ->{
            painter =painterResource(id = R.drawable.londonfire)
        }
        "MocoMuseum" ->{
            painter =painterResource(id = R.drawable.mocolondon)
        }else->{
        painter =painterResource(id = R.drawable.towerlondon)
    }
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .width(170.dp),
        onClick = {
            onItemClick(tourData)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterHorizontally),
            color = Color.White,
        ) {
            Column {
                Image(modifier = Modifier
                    .height(200.dp),
                    painter = painter!!,
                    contentDescription = "image description",
                    contentScale = ContentScale.FillWidth)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                    horizontalArrangement  =  Arrangement.SpaceBetween){
                    Column(modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)) {
                        Text(modifier = Modifier,
                            text = tourData.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_bold)),
                                fontWeight = FontWeight.Bold,
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
                    Text(modifier = Modifier
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
                Row(modifier = Modifier
                    .padding(start = 10.dp)) {
                    Icon(imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Down arrow",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                expanded = !expanded
                            },
                        tint = colorResource(R.color.button_color)
                    )
                }
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(modifier = Modifier
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

@Composable
fun AddToCartButton(navController: NavController?){
    val mContext = LocalContext.current
    Button(modifier = Modifier
        .requiredWidth(80.dp)
        .requiredHeight(43.dp),
        onClick = {
            mToast(mContext)
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.button_color)),
    ) {
        Text(modifier = Modifier
            .align(Alignment.CenterVertically),
            text = "Add",
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

private fun mToast(context: Context){
    Toast.makeText(context, "Order Added to cart", Toast.LENGTH_LONG).show()
}


*/
/*@Composable
fun CircleImageView() {
    Image(
        painter = painterResource(R.drawable.profile_avatar),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape) // clip to the circle shape
            .border(1.dp, Color.Gray, CircleShape)//optional
    )
}*//*


@Composable
fun SearchBar(hint  :String){
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .requiredHeight(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        border = BorderStroke(1.dp, colorResource(R.color.divider)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top) {
            var value by remember { mutableStateOf("") }
            Row{
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

                                ))
                    },
                    maxLines = 2,
                    textStyle = TextStyle(fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                        fontWeight = FontWeight(400),
                        color = colorResource(R.color.black),),
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(end = 40.dp)
                        .background(colorResource(R.color.white))


                )
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Circle Image",
                    //contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(colorResource(R.color.white))
                        .align(Alignment.CenterVertically)
                        .size(17.dp)
                        .clip(CircleShape) // clip to the circle shap

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar(navController: NavController?,scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(modifier = Modifier,
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp)
                    .clickable (onClick = {
                        navController?.navigate(route = HomeMainAppScreens.ProfileMainScreen.name)

                    }),
                horizontalArrangement = Arrangement.Start
            ){
                CircleImageView()
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(10.dp)) {
                    Text(modifier = Modifier
                        .align(Alignment.Start),
                        text = "Hello, Omer",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 27.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                            fontWeight = FontWeight(600),
                            color = colorResource(R.color.black),
                        )
                    )
                    Text(modifier = Modifier
                        .align(Alignment.Start),
                        text = "Find a Place to Vibe in London",
                        style = TextStyle(
                            fontSize =10.sp,
                            lineHeight = 27.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                            fontWeight = FontWeight(100),
                            color = colorResource(R.color.black),
                        )
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.app_bar_color)),
        scrollBehavior = scrollBehavior

    )
}


@Composable
fun CircleImageView() {
    Image(
        painter = painterResource(R.drawable.profile_avatar),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape) // clip to the circle shape
            .border(1.dp, Color.Gray, CircleShape)//optional
    )
}

@Composable
fun FloatingActionButtons(navController: NavController?) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(0.dp,0.dp,30.dp,60.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                navController?.navigate(route = HomeMainAppScreens.AddProductScreen.name)
            },
            containerColor = colorResource(R.color.button_color),
            contentColor = Color.White
        ) {
            // adding icon for button.
            Icon(Icons.Filled.Add, "")
        }
    }
}*/
