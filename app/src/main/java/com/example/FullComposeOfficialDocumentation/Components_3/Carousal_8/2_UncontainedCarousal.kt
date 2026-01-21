package com.example.FullComposeOfficialDocumentation.Components_3.Carousal_8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
UNCONTAINED CAROUSEL

Scrollable list with single-sized items flowing past screen edge
Items are not clipped at the boundaries
Can be customized with text or UI above/below items

COMMON USE CASES:
- Feature highlights
- Content previews
- Media browsing with metadata
- Product showcases with descriptions
- Story-style content

API SURFACE PARAMETERS:

1. state: CarouselState
   Manages current item index and scroll position
   Create with rememberCarouselState { itemCount }

2. itemWidth: Dp
   Fixed width for each carousel item
   All items have same width (key parameter for uncontained carousel)

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
fun UncontainedCarouselBasicExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val contentDescription: String
    )

    val carouselItems = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Item 1"),
            CarouselItem(1, Color(0xFF9C27B0), "Item 2"),
            CarouselItem(2, Color(0xFF673AB7), "Item 3"),
            CarouselItem(3, Color(0xFF3F51B5), "Item 4"),
            CarouselItem(4, Color(0xFF2196F3), "Item 5"),
        )
    }

    HorizontalUncontainedCarousel(
        state = rememberCarouselState { carouselItems.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        itemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = carouselItems[i]
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
fun UncontainedCarouselWithDifferentWidthsExample() {
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
            "Narrow Items (140.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 140.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Standard Items (186.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
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
            "Wide Items (280.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 280.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(320.dp)
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
fun UncontainedCarouselWithTextExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val title: String,
        val subtitle: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Cupcake", "Version 1.5"),
            CarouselItem(1, Color(0xFF9C27B0), "Donut", "Version 1.6"),
            CarouselItem(2, Color(0xFF673AB7), "Eclair", "Version 2.0-2.1"),
            CarouselItem(3, Color(0xFF3F51B5), "Froyo", "Version 2.2-2.2.3"),
            CarouselItem(4, Color(0xFF2196F3), "Gingerbread", "Version 2.3-2.3.7"),
        )
    }

    HorizontalUncontainedCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        itemWidth = 200.dp,
        itemSpacing = 12.dp,
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
                        .height(220.dp)
                        .background(item.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.title, color = Color.White, style = MaterialTheme.typography.headlineSmall)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = item.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UncontainedCarouselWithSpacingExample() {
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
            "Tight Spacing (4.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
            itemSpacing = 4.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Comfortable Spacing (12.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(items[i].color, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(items[i].contentDescription, color = Color.White)
            }
        }

        Text(
            "Spacious Layout (24.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
            itemSpacing = 24.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            Box(
                modifier = Modifier
                    .height(180.dp)
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
fun UncontainedCarouselWithContentPaddingExample() {
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
            "No Content Padding",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(0.dp)
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
            "With Horizontal Padding (32.dp)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 32.dp)
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
fun UncontainedCarouselClickableExample() {
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
        Text(
            "Click an item to select",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            itemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            Card(
                onClick = { selectedItem = item },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(205.dp)
                            .background(item.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.title,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        selectedItem?.let { item ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "You selected: ${item.title}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UncontainedCarouselWithIndicatorExample() {
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

    val carouselState = rememberCarouselState { items.count() }
    var currentIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalUncontainedCarousel(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            itemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            currentIndex = i
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(205.dp)
                        .background(item.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.title, color = Color.White, style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(items.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (index == currentIndex) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                            },
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UncontainedCarouselCompleteExample() {
    data class CarouselItem(
        val id: Int,
        val color: Color,
        val title: String,
        val description: String,
        val badge: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, Color(0xFFE91E63), "Cupcake", "Sweet and simple dessert", "NEW"),
            CarouselItem(1, Color(0xFF9C27B0), "Donut", "Delicious ring-shaped treat", "POPULAR"),
            CarouselItem(2, Color(0xFF673AB7), "Eclair", "Elegant French pastry", "TRENDING"),
            CarouselItem(3, Color(0xFF3F51B5), "Froyo", "Frozen yogurt delight", "SALE"),
            CarouselItem(4, Color(0xFF2196F3), "Gingerbread", "Spiced cookie favorite", "LIMITED"),
        )
    }

    val carouselState = rememberCarouselState { items.count() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    var currentIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Featured Desserts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalUncontainedCarousel(
            state = carouselState,
            modifier = Modifier.fillMaxWidth(),
            itemWidth = 220.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            currentIndex = i
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
                    Box {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(item.color),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(item.title, color = Color.White, style = MaterialTheme.typography.headlineMedium)
                        }
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = item.badge,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(items.size) { index ->
                Box(
                    modifier = Modifier
                        .size(
                            width = if (index == currentIndex) 24.dp else 8.dp,
                            height = 8.dp
                        )
                        .background(
                            color = if (index == currentIndex) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                            },
                            shape = MaterialTheme.shapes.small
                        )
                )
                if (index < items.size - 1) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}

/*
BEST PRACTICES:

Use itemWidth for fixed-size items (all items same width)
Items flow past screen edge without clipping
Ideal for showcasing content with additional UI elements
Perfect for media content with text overlays
Consistent item sizing creates predictable scrolling
Use contentPadding to control edge alignment
Combine with cards to add text and metadata
Works well for feature highlights and promotions
Consider adding position indicators for better UX
Items are not clipped at boundaries (key difference from multi-browse)
Great for content that needs breathing room
Suitable for storytelling and narrative flows
*/
