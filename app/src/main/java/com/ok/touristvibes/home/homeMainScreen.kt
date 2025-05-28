package com.ok.touristvibes.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import com.ok.touristvibes.models.getTourData
import com.ok.touristvibes.navigation.homeMainNavigation.HomeMainAppScreens
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


@Composable
fun HomeMainApp(navController: NavController?){
    Column(modifier = Modifier
        .fillMaxSize()){
        Scaffold(
            topBar = { AddAppBar() },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = colorResource(R.color.white)
                ) {
                    MainFoodList(navController)
                    FloatingActionButtons(navController)
                }
            }
        )
    }
}

@Composable
fun MainFoodList(navController: NavController?,foodList: List<TourData> = getTourData()){
   Column(modifier = Modifier.padding(12.dp)) {
       LazyColumn{
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
        .fillMaxWidth(),
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
@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (8f * density).dp
    val starSpacing = (0.2f * density).dp

    Row(
        modifier = Modifier
            .selectableGroup()
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20202020)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}

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
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar() {
    TopAppBar(modifier = Modifier,

        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(modifier = Modifier
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
        colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.app_bar_color)),
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
}