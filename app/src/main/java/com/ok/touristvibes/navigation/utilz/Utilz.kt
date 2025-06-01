package com.ok.touristvibes.navigation.utilz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

import com.ok.touristvibes.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlin.math.absoluteValue

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

//@OptIn(ExperimentalPagerApi::class)
@OptIn(FlowPreview::class)
@Composable
fun ImageProductPager() {
    val imageList = listOf(
        R.drawable.londoneye,
        R.drawable.towerlondon,
        R.drawable.westminsterabbey,
        R.drawable.shard
    )
    val nameList = listOf(
        "London Eye",
        "Tower of London",
        "westminsterabbey",
        "shard"
    )

    val items = imageList
    val pagerState = rememberPagerState { imageList.size }

   /* val multiplier = 4
    val pageCount = multiplier * items.size
    val initialPage = pageCount / 2

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { pageCount }
    )*/

// We want to automatically scroll every 3 seconds the pager isn't scrolled (user scrolling or automatically scrolling.).
    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }
            .debounce(3000) // delay until next automatic scrolling
            .collect { page ->
                pagerState
                    .animateScrollToPage(page + 1)
            }
    }

    HorizontalPager(
        state = pagerState
    ) { page ->
        // Define the content of each page here
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(items[page])
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator(
                    color = colorResource(R.color.fade_blue_color),
                    modifier = Modifier.padding(48.dp)
                )
            },
            contentDescription = " ",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(200.dp)
                .padding(end = 10.dp),
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    HorizontalTabs(
        items = items,
        pagerState = pagerState
    )
}

//@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalTabs(
    items: List<Int?>?,
    pagerState: PagerState,
) {
    val dotRadius = 4.dp
    val dotSpacing = 8.dp

    Box(
        modifier = Modifier
            .height(dotRadius * 2)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        ) {
            items?.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(dotRadius * 2)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) colorResource(R.color.main_blue_color) else colorResource(R.color.fade_blue_color)
                        ),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        R.drawable.londoneye,
        R.drawable.towerlondon,
        R.drawable.westminsterabbey,
        R.drawable.shard
    )



/*    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        AutoSlidingCarousel(
            itemsCount = images.size,
            itemContent = { index ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageList[index])
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )
            }
        )
    }*/

/*    val pagerState = rememberPagerState { imageList.size }

    Column(
        modifier
            .defaultMinSize(minHeight = 300.dp)
            .fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 10.dp,

            contentPadding = PaddingValues(horizontal = 10.dp)
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .graphicsLayer {
                        val pageOffset =
                            (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue

                        lerp(
                            start = 75.dp,
                            stop = 100.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleY = scale / 100.dp
                        }
                    },
                contentScale = ContentScale.Crop
            )
        }

    }*/

}

