package com.ok.touristvibes.home


import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.ok.touristvibes.R
import com.ok.touristvibes.models.TourData
import com.ok.touristvibes.models.getTourData
import com.ok.touristvibes.navigation.homeMainNavigation.HomeMainAppScreens
import com.ok.touristvibes.navigation.utilz.ImageProductPager
import com.ok.touristvibes.profile.PhotoGrid
import com.ok.touristvibes.ui.theme.TouristVibesTheme
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TouristVibesProfileScreenPreview() {
    TouristVibesTheme {
        HomeMainApp(null)
    }
}
@Composable
fun ProfileScreenMainOnBack (onExitApp: () -> Unit) {
    BackHandler(enabled = true) {
        onExitApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainApp(navController: NavController?, modifier: Modifier = Modifier) {


    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(Unit) {
        // set the status bar icon color
        (context as ComponentActivity).enableEdgeToEdge(
            statusBarStyle = if (isDarkTheme) {
                SystemBarStyle.light(
                    scrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT
                )
            } else {
                SystemBarStyle.dark(
                    scrim = android.graphics.Color.TRANSPARENT
                )
            }
        )
    }

    val expandedAppBarHeight = 370.dp
    // header translation should be half of expandedAppBarHeight
    val headerTranslation = (expandedAppBarHeight / 2)

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = scrollBehavior.state
    val appBarExpanded by remember {
        // app bar expanded state
        // collapsedFraction 1f = collapsed
        // collapsedFraction 0f = expanded
        derivedStateOf { scrollState.collapsedFraction < 0.9f }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .zIndex(0f)
                    .fillMaxWidth()
                    // the app bar background color
                    .background(colorResource(R.color.white))
            ) {
                CollapsedAppBar(navController,visible = !appBarExpanded)
                TopAppBar(
                    title = {
                        AppBarHeader(
                            modifier = Modifier.graphicsLayer {
                                // set the header translationY based on the
                                // scroll behavior state collapsedFraction
                                translationY =
                                    scrollState.collapsedFraction * headerTranslation.toPx()
                            },
                            navController,
                            visible = appBarExpanded
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent
                    ),
                    expandedHeight = expandedAppBarHeight,
                    // remove extra padding on top (from status bar)
                    windowInsets = WindowInsets(0),
                    scrollBehavior = scrollBehavior
                )
            }
        },
    ) { innerPadding ->
        val cornerRadius = 16.dp
        Box(
            modifier = Modifier
                .zIndex(1f)
                .wrapContentSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(colorResource(R.color.white))
            )
            val coroutineScope = rememberCoroutineScope()
            val dragState = remember { mutableFloatStateOf(0f) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // manually draw the elevation
                    .drawBehind {
                        val shadowColor = Color.Black.copy(alpha = 0.25f)
                        val paint = Paint().asFrameworkPaint().apply {
                            color = android.graphics.Color.TRANSPARENT
                            setShadowLayer(
                                4.dp.toPx(), // shadow blur
                                0f, // shadow offset x
                                (-1).dp.toPx(), // shadow offset y
                                shadowColor.toArgb() // shadow color
                            )
                        }

                        drawIntoCanvas {
                            it.nativeCanvas.drawRoundRect(
                                0f,
                                0f,
                                size.width,
                                size.height / 2,
                                cornerRadius.toPx(),
                                cornerRadius.toPx(),
                                paint
                            )
                        }
                    }
                    // background with rounded corner on top side
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            topStart = cornerRadius,
                            topEnd = cornerRadius
                        )
                    )
            ) {
                CardListHeader(scrollBehavior = scrollBehavior)
                MainFoodList(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),navController)
              /*  val items = listOf(
                    "https://images.unsplash.com/photo-1568565110033-4eab8bcdd72c?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1556695736-d287caebc48e?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1425913397330-cf8af2ff40a1?q=80&w=3174&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1476231682828-37e571bc172f?q=80&w=3174&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?q=80&w=1920&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1564501911891-74a27d949ee1?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1510414842594-a61c69b5ae57?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1523590564318-491748f70ea7?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1486870591958-9b9d0d1dda99?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1568605117036-5fe5e7bab0b7?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1526481280693-3bfa7568e0f3?q=80&w=2971&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1533050487297-09b450131914?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1458869612855-bb6009d50368?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1509099395498-a26c959ba0b7?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1455577380025-4321f1e1dca7?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1593132808462-578ca7a387d9?q=80&w=3174&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1570129477492-45c003edd2be?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?q=80&w=3110&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=2881&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://plus.unsplash.com/premium_photo-1676968002767-1f6a09891350?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://plus.unsplash.com/premium_photo-1661595245288-65d1430d0d13?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://plus.unsplash.com/premium_photo-1682116752956-c880046f5361?q=80&w=3099&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://plus.unsplash.com/premium_photo-1686782502531-feb0e6a56eb5?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "https://plus.unsplash.com/premium_photo-1664358190450-2d84d93b9546?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )
                PhotoGrid(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    items = items,
                )*/
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListHeader(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val coroutineScope = rememberCoroutineScope()
    val dragState = remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            // detect user drag gesture so user able to scroll the content
            // by dragging the header on top of the content
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            // dragState.floatValue * 2 = scroll snap speed
                            val velocity = Velocity(0f, dragState.floatValue * 2)
                            scrollBehavior.nestedScrollConnection.onPostFling(
                                Velocity.Zero,
                                velocity
                            )
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        dragState.floatValue = dragAmount.y

                        // dragAmount.y * 0.6f = adjust the scroll speed
                        // to match with the LazyVerticalGrid scroll speed
                        // change the 0.6f multiplier if needed
                        val scrollDelta = Offset(0f, dragAmount.y * 0.6f)

                        val preConsumed = scrollBehavior.nestedScrollConnection.onPreScroll(
                            scrollDelta,
                            NestedScrollSource.UserInput
                        )
                        val remaining = scrollDelta - preConsumed
                        scrollBehavior.nestedScrollConnection.onPostScroll(
                            consumed = preConsumed,
                            available = remaining,
                            NestedScrollSource.UserInput
                        )
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(top = 12.dp)
        ) {
            // drag handle indicator
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(width = 36.dp, height = 5.dp)
                    .background(
                        color = colorResource(R.color.main_blue_color),
                        shape = CircleShape
                    )
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(modifier = Modifier
                .align(Alignment.Top),
                text = "Attractive Vibes",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_semi_bold)),
                    fontWeight = FontWeight(600),
                    color = colorResource(R.color.black),
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsedAppBar(
    navController: NavController?,
    modifier: Modifier = Modifier,
    visible: Boolean
) {
    TopAppBar(
        modifier = modifier,
        title = {
            // animate fadeIn fadeOut the avatar + name on the collapsed app bar
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween()),
                exit = fadeOut(animationSpec = tween())
            ) {
                // avatar + name on the collapsed app bar
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                                    fontSize = 15.sp,
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
                                    fontSize =12.sp,
                                    lineHeight = 27.sp,
                                    fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                                    fontWeight = FontWeight(100),
                                    color = colorResource(R.color.black),
                                )
                            )
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            // set background color for expanded state to transparent
            containerColor = Color.Transparent,
            // set background color for collapsed state to transparent
            scrolledContainerColor = Color.Transparent,
            // set navigation icon color
            navigationIconContentColor = colorResource(R.color.box_bg)
        )
    )
}

@Composable
fun AppBarHeader(
    modifier: Modifier = Modifier,
    navController: NavController?,
    visible: Boolean
) {
    // animate fadeIn fadeOut the expanded app bar header
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween()),
        exit = fadeOut(animationSpec = tween())
    ) {
        Column(
            // only add end padding
            // because the TopAppBar already has start padding
            modifier = modifier.padding(end = 16.dp, top = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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
                            fontSize = 15.sp,
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
                            fontSize =12.sp,
                            lineHeight = 27.sp,
                            fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                            fontWeight = FontWeight(100),
                            color = colorResource(R.color.black),
                        )
                    )
                }
            }
            SearchBar("Search vibes accross london")
            //Box(modifier = Modifier.height(20.dp)) {  }
            Column(modifier = Modifier) {
                ImageProductPager()
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
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
}
@Composable
fun SearchBar(hint  :String){
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .requiredHeight(48.dp)
            .padding(1.dp, 0.dp, 1.dp, 0.dp),
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

@Composable
fun MainFoodList(modifier: Modifier,navController: NavController?,foodList: List<TourData> = getTourData()){
    LazyVerticalGrid(
        modifier = modifier
            .wrapContentSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(items = foodList){ foodList ->
            MovieRow(foodList,navController){ selectedFoodItem ->
                Log.d("SelectedFoodItem",selectedFoodItem.name)
                navController?.navigate(route = HomeMainAppScreens.ProductDetailScreen.name+"/"+selectedFoodItem.id)
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
        .wrapContentSize(),
        onClick = {
            onItemClick(tourData)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
        Surface(modifier = Modifier
            .wrapContentSize()
            .align(Alignment.CenterHorizontally),
            color = Color.White,
        ) {
            Column {
                Image(modifier = Modifier
                    .height(200.dp),
                    painter = painter!!,
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                    horizontalArrangement  =  Arrangement.SpaceBetween){
                    Column(modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)) {
                        Text(modifier = Modifier,
                            text = tourData.name,
                            maxLines = 1,
                            style = TextStyle(
                                fontSize = 12.sp,
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
                            fontSize = 10.sp,
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
                                fontSize = 10.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.lexend_deca_medium)),
                                fontWeight = FontWeight(300),
                                color = colorResource(R.color.black),
                            )
                        )
                    }
                }
            }
        }
    }
}
