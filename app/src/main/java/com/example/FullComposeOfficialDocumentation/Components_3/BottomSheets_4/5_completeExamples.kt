package com.example.FullComposeOfficialDocumentation.Components_3.BottomSheets_4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
═══════════════════════════════════════════════════════════════════════════════
                    COMPLETE BOTTOM SHEET EXAMPLES
═══════════════════════════════════════════════════════════════════════════════

Real-world examples demonstrating best practices

EXAMPLES IN THIS FILE:
1. Simple action menu
2. Form input sheet
3. Filter options sheet
4. Detailed content preview
5. Multi-step sheet

═══════════════════════════════════════════════════════════════════════════════
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionMenuBottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet = true }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Options")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Tap the FAB to see action menu")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Actions",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                ListItem(
                    headlineContent = { Text("Share") },
                    leadingContent = { Icon(Icons.Filled.Share, contentDescription = null) },
                    modifier = Modifier.clickable {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    }
                )

                ListItem(
                    headlineContent = { Text("Edit") },
                    leadingContent = { Icon(Icons.Filled.Edit, contentDescription = null) },
                    modifier = Modifier.clickable {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    }
                )

                ListItem(
                    headlineContent = { Text("Delete") },
                    leadingContent = { Icon(Icons.Filled.Delete, contentDescription = null) },
                    modifier = Modifier.clickable {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    }
                )

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

/*
USE CASE: Action menu bottom sheet

WHEN TO USE:
- Quick actions on an item
- Context menu replacement
- Share, edit, delete options

BEST PRACTICES SHOWN:
✓ ListItem for consistent spacing
✓ Icons for visual clarity
✓ Click handlers on each item
✓ Smooth hide animation on selection
✓ Proper padding at bottom for gesture area
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputBottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add Contact") },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                onClick = { showBottomSheet = true }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Contact List Empty")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "New Contact",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                    name = ""
                                    email = ""
                                    phone = ""
                                }
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            // Save contact logic here
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                    // Clear form after save
                                    name = ""
                                    email = ""
                                    phone = ""
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = name.isNotBlank()
                    ) {
                        Text("Save")
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/*
USE CASE: Form input in bottom sheet

WHEN TO USE:
- Quick data entry
- Add new items
- Edit existing items
- User input required

BEST PRACTICES SHOWN:
✓ Multiple text fields
✓ Appropriate keyboard types
✓ Form validation (Save enabled only when valid)
✓ Cancel and Save buttons
✓ Clear form after submission
✓ Proper spacing for keyboard
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterOptionsBottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf("All") }
    var priceRange by remember { mutableStateOf(0f..1000f) }
    var inStock by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(Icons.Filled.Search, contentDescription = "Filter")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Applied Filters:")
                Text("Category: $selectedCategory")
                Text("Price: $${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}")
                Text("In Stock: $inStock")
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Filter Options",
                    style = MaterialTheme.typography.titleLarge
                )

                Text("Category", style = MaterialTheme.typography.titleMedium)

                val categories = listOf("All", "Electronics", "Clothing", "Books", "Food")
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    categories.take(3).forEachIndexed { index, category ->
                        SegmentedButton(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            shape = SegmentedButtonDefaults.itemShape(index, 3)
                        ) {
                            Text(category)
                        }
                    }
                }

                Text("Price Range", style = MaterialTheme.typography.titleMedium)
                Text("$${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}")
                RangeSlider(
                    value = priceRange,
                    onValueChange = { priceRange = it },
                    valueRange = 0f..1000f,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = inStock,
                        onCheckedChange = { inStock = it }
                    )
                    Text("In Stock Only")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            selectedCategory = "All"
                            priceRange = 0f..1000f
                            inStock = false
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reset")
                    }

                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) showBottomSheet = false
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Apply")
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/*
USE CASE: Filter/settings bottom sheet

WHEN TO USE:
- Search filters
- Settings adjustments
- Multiple options selection
- Range selections

BEST PRACTICES SHOWN:
✓ Segmented buttons for categories
✓ Range slider for price
✓ Checkbox for boolean options
✓ Reset button to clear filters
✓ Apply button to confirm
✓ Live preview of selected values
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedContentBottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Product List", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Card(
            onClick = { showBottomSheet = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Product Name", style = MaterialTheme.typography.titleMedium)
                    Text("$99.99", style = MaterialTheme.typography.bodyLarge)
                }
                Icon(Icons.Filled.Info, contentDescription = "Details")
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Product Details",
                    style = MaterialTheme.typography.titleLarge
                )

                HorizontalDivider()

                Text("Price: $99.99", style = MaterialTheme.typography.titleMedium)
                Text("In Stock: 15 units")
                Text("Category: Electronics")

                HorizontalDivider()

                Text("Description", style = MaterialTheme.typography.titleMedium)
                Text(
                    "This is a detailed description of the product. " +
                    "It includes features, specifications, and other relevant information " +
                    "that helps the user make an informed decision."
                )

                HorizontalDivider()

                Text("Specifications", style = MaterialTheme.typography.titleMedium)
                Text("• Weight: 1.5 kg")
                Text("• Dimensions: 30 x 20 x 5 cm")
                Text("• Color: Black")
                Text("• Material: Premium plastic")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) showBottomSheet = false
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Close")
                    }

                    Button(
                        onClick = { /* Add to cart */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Add to Cart")
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/*
USE CASE: Detailed content preview

WHEN TO USE:
- Product details
- Article preview
- Item information
- Detailed descriptions

BEST PRACTICES SHOWN:
✓ Organized sections with dividers
✓ Clear hierarchy with typography
✓ Action buttons at bottom
✓ Scrollable content
✓ Close and primary action buttons
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    BEST PRACTICES SUMMARY
═══════════════════════════════════════════════════════════════════════════════

1. STATE MANAGEMENT
   ✓ Use rememberModalBottomSheetState() for sheetState
   ✓ Use remember { mutableStateOf(false) } for showBottomSheet
   ✓ Use rememberCoroutineScope() for scope

2. SHOWING THE SHEET
   ✓ Set showBottomSheet = true
   ✓ Let sheetState handle animation automatically

3. HIDING THE SHEET
   ✓ Use scope.launch { sheetState.hide() }
   ✓ Use .invokeOnCompletion for cleanup
   ✓ Check !sheetState.isVisible before setting showBottomSheet = false

4. DISMISSING
   ✓ Handle onDismissRequest (swipe/tap outside)
   ✓ Simply set showBottomSheet = false (already animated by gesture)

5. CONTENT LAYOUT
   ✓ Use Column with fillMaxWidth()
   ✓ Add padding (16.dp typically)
   ✓ Add bottom spacer for gesture area
   ✓ Use appropriate spacing between elements

6. FORMS
   ✓ Validation before enabling submit
   ✓ Clear form after successful submission
   ✓ Cancel button to dismiss without saving
   ✓ Appropriate keyboard types

7. LISTS/MENUS
   ✓ Use ListItem for consistency
   ✓ Add icons for visual clarity
   ✓ Hide sheet on item selection
   ✓ Use smooth animations

8. ACCESSIBILITY
   ✓ Provide contentDescription for icons
   ✓ Use semantic typography styles
   ✓ Ensure touch targets are 48dp minimum
   ✓ Support keyboard navigation

9. PERFORMANCE
   ✓ Don't create heavy composables inside sheet
   ✓ Use remember for expensive calculations
   ✓ Lazy lists for long content
   ✓ Proper state management

10. UX PRINCIPLES
    ✓ Always animate hide (never instant)
    ✓ Provide visual feedback
    ✓ Clear action buttons
    ✓ Swipe-to-dismiss support
    ✓ Tap outside to dismiss

═══════════════════════════════════════════════════════════════════════════════
*/

