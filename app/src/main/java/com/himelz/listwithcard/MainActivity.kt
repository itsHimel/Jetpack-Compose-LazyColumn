package com.himelz.listwithcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "ListWithCard"
                                )
                            },
                            elevation = 8.dp,
                            navigationIcon = {
                                IconButton(onClick = {Toast.makeText(this@MainActivity, "Menu Navigation Clicked", Toast.LENGTH_SHORT).show()}) {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = "Menu Navigation"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {Toast.makeText(this@MainActivity, "Notification Clicked", Toast.LENGTH_SHORT).show()}) {
                                    Icon(
                                        Icons.Filled.Notifications,
                                        contentDescription = "Notification"
                                    )
                                }
                                IconButton(onClick = {Toast.makeText(this@MainActivity, "Search Clicked", Toast.LENGTH_SHORT).show()}) {
                                    Icon(
                                        Icons.Filled.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { Toast.makeText(this, "FAB Clicked", Toast.LENGTH_SHORT).show() }) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add"
                            )
                        }
                    }
                ) {
                    RecyclerView(
                        dummyData(),
                    )
                }
            }
        }
    }
}

@Composable
fun EachRow(user: User,
            onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(180.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth()
                .clickable (onClick = onItemClick),
            shape = RoundedCornerShape(CornerSize(8.dp)),
            elevation = 2.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
//                    .fillMaxWidth()
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
    val context = LocalContext.current
    LazyColumn {
        itemsIndexed(users) { index, user ->
            EachRow(
                user,
                onItemClick = {
                    Toast.makeText(context, "Item Clicked  $index", Toast.LENGTH_SHORT).show()
                }
            )
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