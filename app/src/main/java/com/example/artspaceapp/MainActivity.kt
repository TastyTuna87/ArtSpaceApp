package com.example.artspaceapp

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ArtSpaceApp() {
        var currentIndex by remember { mutableStateOf(0) }
        val imageResources = listOf(
            R.drawable.pexels_pixabay_462162,
            R.drawable.pexels_lumn_167699,
            R.drawable.pexels_pixabay_147411,
            R.drawable.pexels_pixabay_158063,

        )
        val textResources = listOf(
            stringResource(R.string.gulf),
            stringResource(R.string.forest_in_fog),
            stringResource(R.string.house_lake),
            stringResource(R.string.lake_with_bridge),

        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize()
                .padding(bottom = 30.dp, top = 85.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ArtCard(drawableId = imageResources[currentIndex],
                    textLabelString = textResources[currentIndex])
            DescriptionWithButtons(
                currentIndex = currentIndex,
                totalImages = imageResources.size,
                onNextClick = { currentIndex = (currentIndex + 1) % imageResources.size },
                onPreviousClick = { currentIndex = (currentIndex - 1 + imageResources.size) % imageResources.size }
            )

        }
    }

    @Composable
    fun ArtCard(drawableId: Int, textLabelString: String) {
        Spacer(modifier = Modifier.padding(20.dp))
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 35.dp,
                        ambientColor = Color.Gray,
                        spotColor = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                    ),

                ) {
                ArtImage(drawableId = drawableId, textLabelString = textLabelString)
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
    }

    @Composable
    fun DescriptionWithButtons(
        currentIndex: Int,
        totalImages: Int,
        onNextClick: () -> Unit,
        onPreviousClick: () -> Unit
        ) {

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {



            Row(
                modifier = Modifier
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(
                    onClick = { onPreviousClick()},
                    modifier = Modifier
                        .size(width = 150.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(73,93,146)
                    )
                ) {
                    Text(text = "Previous")
                }

                Spacer(modifier = Modifier.padding(15.dp))

                ElevatedButton(
                    onClick = { onNextClick() },
                    modifier = Modifier
                        .size(width = 150.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(73,93,146)
                    )
                    ) {
                    Text(text = "Next")
                }

            }
        }
    }

    @Composable
    fun ArtImage(
        modifier: Modifier = Modifier,
        textLabelString: String,
        drawableId: Int,
    ) {
        Column  (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(25.dp))
            Image(
                painter = painterResource(drawableId),
                contentDescription = "stringResource(textLabelId)",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.padding(25.dp))
            Text(
                text = textLabelString,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                //style = TextStyle(background = Color.Gray),

                )
            Spacer(modifier = Modifier.padding(25.dp))

        }
    }
}


