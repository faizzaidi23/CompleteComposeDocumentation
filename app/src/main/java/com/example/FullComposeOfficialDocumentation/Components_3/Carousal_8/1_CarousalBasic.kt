package com.example.FullComposeOfficialDocumentation.Components_3.Carousal_8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
MULTI-BROWSE CAROUSEL

Scrollable list of differently sized items
Adapts dynamically based on window size
Recommended for browsing many items at once

COMMON USE CASES:
- Photo galleries
- Product browsing (e-commerce)
- Media content collections
- Image portfolios
- Feature showcases

API SURFACE PARAMETERS:

1. state: CarouselState
   Manages current item index and scroll position
   Create with rememberCarouselState { itemCount }

2. preferredItemWidth: Dp
   Suggests ideal width for items
   Carousel determines how many items fit on screen

3. itemSpacing: Dp
   Empty space between adjacent items

4. contentPadding: PaddingValues
   Padding around content area
   Adds space before first/after last item

5. modifier: Modifier
   Standard modifier for sizing and positioning

6. content: @Composable (Int) -> Unit
   Lambda to define UI for each item by index
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselBasicExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val contentDescription: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Item 1"),
            CarouselItem(1, Color(0xFF9C27B0), "Item 2"),
            CarouselItem(2, Color(0xFF673AB7), "Item 3"),
            CarouselItem(3, Color(0xFF3F51B5), "Item 4"),
            CarouselItem(4, Color(0xFF2196F3), "Item 5"),
        )
    }

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Box(
            modifier = Modifier
                .height(205.dp)
                .fillMaxWidth()
                .background(item.color, MaterialTheme.shapes.extraLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.contentDescription,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselWithDifferentSizesExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val contentDescription: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Item 1"),
            CarouselItem(1, Color(0xFF9C27B0), "Item 2"),
            CarouselItem(2, Color(0xFF673AB7), "Item 3"),
            CarouselItem(3, Color(0xFF3F51B5), "Item 4"),
            CarouselItem(4, Color(0xFF2196F3), "Item 5"),
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Small Items (120.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 120.dp,
            itemSpacing = 4.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Medium Items (186.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(205.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.extraLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Large Items (250.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 250.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.extraLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselWithTextExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val title: String,
        val description: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Cupcake", "Android 1.5"),
            CarouselItem(1, Color(0xFF9C27B0), "Donut", "Android 1.6"),
            CarouselItem(2, Color(0xFF673AB7), "Eclair", "Android 2.0-2.1"),
            CarouselItem(3, Color(0xFF3F51B5), "Froyo", "Android 2.2-2.2.3"),
            CarouselItem(4, Color(0xFF2196F3), "Gingerbread", "Android 2.3-2.3.7"),
        )
    }

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        preferredItemWidth = 200.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(item.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.title, color = Color.White, style = MaterialTheme.typography.headlineSmall)
                }
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselWithSpacingExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val contentDescription: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Item 1"),
            CarouselItem(1, Color(0xFF9C27B0), "Item 2"),
            CarouselItem(2, Color(0xFF673AB7), "Item 3"),
            CarouselItem(3, Color(0xFF3F51B5), "Item 4"),
            CarouselItem(4, Color(0xFF2196F3), "Item 5"),
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "No Spacing (0.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 186.dp,
            itemSpacing = 0.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.small),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Medium Spacing (16.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 186.dp,
            itemSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselWithContentPaddingExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val contentDescription: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Item 1"),
            CarouselItem(1, Color(0xFF9C27B0), "Item 2"),
            CarouselItem(2, Color(0xFF673AB7), "Item 3"),
            CarouselItem(3, Color(0xFF3F51B5), "Item 4"),
            CarouselItem(4, Color(0xFF2196F3), "Item 5"),
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Horizontal Padding (16.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Large Horizontal Padding (40.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            preferredItemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 40.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselClickableExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val title: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Cupcake"),
            CarouselItem(1, Color(0xFF9C27B0), "Donut"),
            CarouselItem(2, Color(0xFF673AB7), "Eclair"),
            CarouselItem(3, Color(0xFF3F51B5), "Froyo"),
            CarouselItem(4, Color(0xFF2196F3), "Gingerbread"),
        )
    }

    var selectedItem by remember { mutableStateOf<CarouselItem?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            preferredItemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            Card(
                onClick = { selectedItem = item },
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(205.dp)
                        .background(item.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                }
            }
        }

        selectedItem?.let { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Selected: ${item.title}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselCompleteExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val title: String,
        val description: String,
        val version: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Cupcake", "Sweet and simple", "1.5"),
            CarouselItem(1, Color(0xFF9C27B0), "Donut", "Delicious development", "1.6"),
            CarouselItem(2, Color(0xFF673AB7), "Eclair", "Elegant experience", "2.0-2.1"),
            CarouselItem(3, Color(0xFF3F51B5), "Froyo", "Frozen yogurt fun", "2.2-2.2.3"),
            CarouselItem(4, Color(0xFF2196F3), "Gingerbread", "Ginger greatness", "2.3-2.3.7"),
        )
    }

    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Android Desserts Gallery",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            preferredItemWidth = 200.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            Card(
                onClick = { selectedIndex = i },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedIndex == i) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(item.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item.title, color = Color.White, style = MaterialTheme.typography.headlineSmall)
                    }
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Version ${item.version}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Selected: ${items[selectedIndex].title}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    items[selectedIndex].description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "Android ${items[selectedIndex].version}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/*
BEST PRACTICES:

Use preferredItemWidth to suggest optimal item size
Carousel automatically fits multiple items based on screen width
Items adapt dynamically to available space
Ideal for browsing collections of images or products
Use Material shapes for consistent rounded corners
Keep itemSpacing consistent with overall design system
ContentPadding provides breathing room at edges
Combine with cards for richer item presentations
Consider accessibility with contentDescription
Works best with visual-first content
Perfect for photo galleries and media browsing
*/
