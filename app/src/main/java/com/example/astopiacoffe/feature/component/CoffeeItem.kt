package com.example.astopiacoffe.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.astopiacoffe.feature.model.CoffeeModel
import com.example.astopiacoffe.ui.theme.coffeItemColor
import com.valentinilk.shimmer.shimmer

@Composable
fun CoffeItem(item: CoffeeModel?, onClick: () -> Unit = {}) {

    if (item != null) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(180.dp)
                .clickable {
                    onClick()
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.coffeItemColor,
            )
        ) {
            Row (modifier=Modifier.fillMaxWidth().background(Color.Transparent).fillMaxHeight()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f)
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.image)
                            .crossfade(true)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                    )


                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    ) {
                        Text(item.title+" #"+item.id, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(item.description, fontWeight = FontWeight.Light, fontSize = 12.sp)
                    }
                }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(180.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.coffeItemColor,
            )
        ) {
            Row (modifier=Modifier.fillMaxWidth().fillMaxHeight()){


                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .fillMaxSize()
                            .shimmerable(true),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                        .weight(0.7f)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .height(20.dp)
                            .shimmerable(true)
                    )
                    Spacer(modifier=Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .height(80.dp)
                            .shimmerable(true)
                    )

                }

            }
        }
    }

}


@Composable
fun Modifier.shimmerable(
    enabled: Boolean,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
): Modifier {
    if (!enabled) return this

    return this
        .shimmer() // 3rd party library call
        .background(color = color, shape = shape)
        .drawWithContent {
            // Do not draw the actual content.
        }
}

@Composable
@Preview(showBackground = true)
fun PreviewCoffeItem() {
    Column(modifier = Modifier.fillMaxSize()) {
        CoffeItem(CoffeeModel("description", 1, "https://images.unsplash.com/photo-1530373239216-42518e6b4063?auto=format&fit=crop&q=60&w=800&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8ZnJhcGlubyUyMG1vY2hhfGVufDB8fDB8fHww", null, "title"))
        //CoffeItem(null)

    }

}