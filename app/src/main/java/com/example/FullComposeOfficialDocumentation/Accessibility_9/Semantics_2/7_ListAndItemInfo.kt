package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
List and Item Information:

In custom lists and grids with many items, it might be helpful for accessibility services to
also receive more detailed information, like the total number of items and indices.

By using collectionInfo and collectionItemInfo semantics on the list and items respectively,
in this long list, accessibility services can inform users of what item index they are at out
of the total collection, in addition to textual semantic information.
*/

@Composable
fun MilkyWayList(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val milkyWay = listOf(
        "Mercury", "Venus", "Earth", "Mars", "Jupiter",
        "Saturn", "Uranus", "Neptune", "Pluto"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = milkyWay.count(),
                    columnCount = 1
                )
            }
    ) {
        itemsIndexed(milkyWay) { index, text ->
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = index,
                            rowSpan = 1,
                            columnIndex = 0,
                            columnSpan = 1
                        )
                    },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/*
CollectionInfo parameters:

- rowCount: Total number of rows in the collection
- columnCount: Total number of columns in the collection

CollectionItemInfo parameters:

- rowIndex: The row index of this item
- rowSpan: How many rows this item spans
- columnIndex: The column index of this item
- columnSpan: How many columns this item spans
*/

@Composable
fun PlanetsList() {
    val planets = listOf(
        "Mercury", "Venus", "Earth", "Mars", "Jupiter",
        "Saturn", "Uranus", "Neptune"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = planets.size,
                    columnCount = 1
                )
            }
    ) {
        planets.forEachIndexed { index, planet ->
            Text(
                text = "$planet - Planet ${index + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = index,
                            rowSpan = 1,
                            columnIndex = 0,
                            columnSpan = 1
                        )
                    },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/*
Benefits:

- Accessibility services can inform users: "Item 3 of 8"
- Users know their position in the list
- Helps users navigate long lists more efficiently
- Provides context about the size of the collection
*/

@Composable
fun GridExample() {
    val items = (1..20).toList()
    val columns = 3
    val rows = items.size / columns

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = rows,
                    columnCount = columns
                )
            }
    ) {
        items.forEachIndexed { index, item ->
            val row = index / columns
            val col = index % columns

            Text(
                text = "Item $item",
                modifier = Modifier
                    .padding(8.dp)
                    .semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = row,
                            rowSpan = 1,
                            columnIndex = col,
                            columnSpan = 1
                        )
                    }
            )
        }
    }
}

/*
Use cases:

- Long scrollable lists
- Grid layouts
- Custom collection components
- Any UI with multiple items where position context is helpful
*/

