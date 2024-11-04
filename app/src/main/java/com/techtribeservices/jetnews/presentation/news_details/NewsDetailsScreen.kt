package com.techtribeservices.jetnews.presentation.news_details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.techtribeservices.jetnews.data.model.News
import com.techtribeservices.jetnews.presentation.State


@Composable
fun NewsDetailsScreen(navController: NavController, news: News) {
    NewsDetails(news = news)
}

@Composable
fun NewsDetails(news: News) {
    val viewModel: NewsDetailsViewModel = hiltViewModel()

    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    LaunchedEffect(state) {
        if(state.value is State.Success) {
            Toast.makeText(context, "News added to database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "News failed to add to database", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(model = news.image, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.FillWidth)

        ConstraintLayout(modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            val (backBtn, topSpace, summary, newsContent) = createRefs()

            Spacer(modifier = Modifier
                .height(350.dp)
                .constrainAs(topSpace) {
                    top.linkTo(parent.top)
                })
            Image(imageVector = Icons.Filled.ArrowBack, contentDescription = null,
                modifier = Modifier.height(24.dp)
                    .width(24.dp)
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    })
            Column(
                modifier = Modifier.padding(horizontal = 32.dp)
                    .background(Color.Gray)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .constrainAs(summary) {
                        top.linkTo(topSpace.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(topSpace.bottom)
                    }
            ) {
                Text(text = news.publishDate)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = news.title)
                Spacer(modifier = Modifier.height(8.dp))
                news.authors?.let { Text(text = it.joinToString(", ")) }
            }
            Box(
                modifier = Modifier
                    .constrainAs(newsContent) {
                        top.linkTo(topSpace.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        //height = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .padding(16.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(16.dp)
            ) {
                Text(text = news.text, fontSize = 14.sp, modifier = Modifier.fillMaxSize())

                Image(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp)
                        .width(48.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {
                                viewModel.addNews(news)
                        }
                )
            }
        }
    }
}