package com.example.quotescompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun QuotesDialog(quote: String, author: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.4f))
            .padding(16.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.padding(8.dp),
            onClick = onClick
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val (quoteText, quoteImage, quoterName, divider) = createRefs()
                Image(
                    imageVector = Icons.Filled.Close,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Black)
                        .constrainAs(quoteImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    contentDescription = "Quote Image",
                )
                Text(
                    text = quote,
                    modifier = Modifier.constrainAs(quoteText) {
                        top.linkTo(quoteImage.top)
                        start.linkTo(quoteImage.end, margin = 16.dp)
                        end.linkTo(parent.end)
                        horizontalBias = 0f
                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                    )
                )

                Box(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f))
                        .height(2.dp)
                        .fillMaxWidth(0.3f)
                        .constrainAs(divider) {
                            top.linkTo(quoteText.bottom)
                            start.linkTo(quoteImage.end, margin = 16.dp)
                            bottom.linkTo(quoterName.top)
                        }
                )

                Text(
                    text = author,
                    modifier = Modifier.constrainAs(quoterName) {
                        top.linkTo(quoteText.bottom, margin = 8.dp)
                        start.linkTo(quoteImage.end, margin = 16.dp)
                        end.linkTo(parent.end)
                        horizontalBias = 0f
                    },
                    style = TextStyle(
                        color = Color.Black.copy(alpha = 0.8f),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Thin,
                        fontSize = 16.sp,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuotesDialogPreview() {
    QuotesDialog("Quote", "item.author", {})
}

