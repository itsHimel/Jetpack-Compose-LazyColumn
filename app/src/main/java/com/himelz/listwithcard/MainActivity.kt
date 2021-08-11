package com.himelz.listwithcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himelz.listwithcard.ui.theme.ListWithCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListWithCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RecyclerView(dummyData())
                }
            }
        }
    }
}

@Composable
fun EachRow(user: User) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(8.dp)),
            elevation = 2.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.man_image),
                    contentDescription = "Man Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = CircleShape
                        )
                        .padding(3.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center

                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = user.description,
                    fontSize = 16.sp
                )
            }
        }

    }
}

@Composable
fun RecyclerView(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            EachRow(user)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ListWithCardTheme {
        RecyclerView(dummyData())
    }
}