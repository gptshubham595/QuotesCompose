package com.example.quotescompose.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ListItem(
    val id: Int,
    val text: String
)

@Composable
fun RecompositionDemo(): Int {
    var items by remember {
        mutableStateOf(List(2) { index ->
            ListItem(id = index, text = "Item ${index + 1}")
        })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Button to add item at start
            Button(onClick = {
                Log.d("Button Click","Adding at start")
                items = listOf(
                    ListItem(
                        id = items.size,
                        text = "New Start ${items.size + 1}"
                    )
                ) + items
            }) {
                Text("Add at Start")
            }

            // Button to add item at end
            Button(onClick = {
                Log.d("Button Click","Adding at end")
                items = items + ListItem(
                    id = items.size,
                    text = "New End ${items.size + 1}"
                )
            }) {
                Text("Add at End")
            }
        }

        // LazyColumn without keys
        Text("Without Keys:", modifier = Modifier.padding(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(items) { item ->
                ListItemWithoutKey(item)
            }
        }

        // LazyColumn with keys
        Text("With Keys:", modifier = Modifier.padding(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                ListItemWithKey(item)
            }
        }
    }

    return 4
}

@Composable
fun ListItemWithoutKey(item: ListItem) {
    SideEffect {
        Log.d("Recompose","Recomposing WITHOUT key: Item ${item.id}")
    }

    Text(
        text = item.text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun ListItemWithKey(item: ListItem) {
    SideEffect {
        Log.d("Recompose","Recomposing WITH key: Item ${item.id}")
    }

    Text(
        text = item.text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}