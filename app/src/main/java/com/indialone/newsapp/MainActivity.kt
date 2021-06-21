package com.indialone.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.glide.rememberGlidePainter
import com.indialone.newsapp.model.Articles
import com.indialone.newsapp.model.NewsEntity
import com.indialone.newsapp.ui.theme.NewsAppTheme
import com.indialone.newsapp.viewmodel.NewsViewModel
import com.indialone.newsapp.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NewsTopHeadlinesList()
                }
            }
        }
    }
}

@Composable
fun NewsTopHeadlinesList() {
    val newsViewModel: NewsViewModel = viewModel(
        factory = ViewModelFactory(),
        modelClass = NewsViewModel::class.java
    )

    // top headlines news entity
    val topHeadlinesNewsEntity: NewsEntity =
        newsViewModel.getTopHeadlinesNews().observeAsState(NewsEntity()).value

    // everything news entity
    val everythingNewsEntity: NewsEntity =
        newsViewModel.getEverythingNews().observeAsState(NewsEntity()).value

    // top headlines by sources news entity
    val topHeadlinesBySourcesNewsEntity: NewsEntity =
        newsViewModel.getTopHeadlinesBySourcesNews().observeAsState(NewsEntity()).value

    // everything by query news entity
    val everythingQueryNewsEntity: NewsEntity =
        newsViewModel.getEverythingQueryNews().observeAsState(NewsEntity()).value

    // everything by query to date news entity
    val everythingQueryToNewsEntity: NewsEntity =
        newsViewModel.getEverythingQueryToNews().observeAsState(NewsEntity()).value

    // top headlines log
    Log.e("top headlines response", "${topHeadlinesNewsEntity.status}")
    Log.e("top headlines results", "${topHeadlinesNewsEntity.totalResults}")

    // everything log
    Log.e("everything response", "${everythingNewsEntity.status}")
    Log.e("everything results", "${everythingNewsEntity.totalResults}")

    // top headlines by query
    Log.e("headlines-sources res", "${topHeadlinesBySourcesNewsEntity.status}")
    Log.e("headlines-sources num", "${topHeadlinesBySourcesNewsEntity.totalResults}")

    // everything query
    Log.e("everything-query res", "${everythingQueryNewsEntity.status}")
    Log.e("everything-query num", "${everythingQueryNewsEntity.totalResults}")

    // everything query to date
    Log.e("everything-query-to res", "${everythingQueryToNewsEntity.status}")
    Log.e("everything-query-to num", "${everythingQueryToNewsEntity.totalResults}")


    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(horizontal = 5.dp)
    ) {



        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Top Headlines",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                topHeadlinesNewsEntity.articles?.let {
                    itemsIndexed(topHeadlinesNewsEntity.articles!!) { index, item ->
                        NewsItemLayout(item)
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .width(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Text(
                text = "Top Headlines from TechCrunch",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                topHeadlinesBySourcesNewsEntity.articles?.let {
                    itemsIndexed(topHeadlinesBySourcesNewsEntity.articles!!) { index, item ->
                        NewsItemLayout(item)
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .width(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Text(
                text = "Everything By Domain wsj.com",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                everythingNewsEntity.articles?.let {
                    itemsIndexed(everythingNewsEntity.articles!!) { index, item ->
                        NewsItemLayout(item)
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .width(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }


        item {
            Text(
                text = "Everything Today",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                everythingQueryNewsEntity.articles?.let {
                    itemsIndexed(everythingQueryNewsEntity.articles!!) { index, item ->
                        NewsItemLayout(item)
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .width(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Text(
                text = "Everything From 21-05-2021 to Today",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                everythingQueryToNewsEntity.articles?.let {
                    itemsIndexed(everythingQueryToNewsEntity.articles!!) { index, item ->
                        NewsItemLayout(item)
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .width(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }


    }
}


@Composable
fun NewsItemLayout(articles: Articles?) {

    articles?.let {
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(500.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            elevation = 10.dp,
            border = BorderStroke(1.dp, Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = if (articles.title == null) "" else articles.title!!,
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Author : " + if (articles.author == null) "" else articles.author,
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Published At : ${articles.publishedAt!!}",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = rememberGlidePainter(request = articles.urlToImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = if (articles.url == null) "" else articles.url!!,
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = if (articles.description == null) "" else articles.description!!,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = if (articles.content == null) "" else articles.content!!,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        NewsTopHeadlinesList()
    }
}