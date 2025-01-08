package com.example.quotescompose.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.quotescompose.Quote
import com.example.quotescompose.QuoteManager

@Composable
fun QuoteScreen() {

    if (QuoteManager.quoteList.isEmpty()) {
        QuoteManager.initializeQuotes()
    }
    val list = rememberSaveable { mutableStateOf(QuoteManager.quoteList) }

    val isDialogShown = rememberSaveable { mutableStateOf(false) }

    val quoteImage = rememberSaveable { mutableIntStateOf(0) }
    val quote = rememberSaveable { mutableStateOf("") }
    val author = rememberSaveable { mutableStateOf("") }

    val search = rememberSaveable { mutableStateOf("") }

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
    ) {
        val (appName, quoteslist, searchBox) = createRefs()
        Text(
            text = "QuotesApp",
            modifier = Modifier
                .constrainAs(appName) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = TextStyle(fontSize = 32.sp)
        )
        OutlinedTextField(
            value = search.value,
            onValueChange = {
                search.value = it
                list.value = QuoteManager.searchQuotes(it)
            },
            label = { Text("Search") },
            modifier = Modifier
                .constrainAs(searchBox) {
                    top.linkTo(appName.bottom, margin = 4.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    verticalBias = 0f
                }
                .padding(16.dp)
                .fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(quoteslist) {
                    top.linkTo(searchBox.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 40.dp)
                    verticalBias = 0f
                    height = Dimension.fillToConstraints
                }
                .padding(horizontal = 16.dp), content = {
                itemsIndexed(
                    list.value, key = { _, it -> it.quote }) { index, item: Quote ->
                    QuotesListItem(
                        quote = item.quote, author = item.author, onClick = {
                            quoteImage.intValue = index
                            quote.value = item.quote
                            author.value = item.author
                            isDialogShown.value = true
                        })
                }
            })
    }



    if (isDialogShown.value) {
        QuotesDialog(
            quote = quote.value, author = author.value, onClick = {
                isDialogShown.value = false
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuoteScreen() {
    QuoteScreen()
}
