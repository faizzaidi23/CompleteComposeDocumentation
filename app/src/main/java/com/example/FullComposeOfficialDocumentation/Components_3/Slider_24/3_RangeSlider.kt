package com.example.FullComposeOfficialDocumentation.Components_3.Slider_24

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
RangeSlider allows users to select two values within a range.
This creates a range with a start and end point.

Common use cases:
- Price range filtering (min and max price)
- Age range selection
- Temperature range
- Date range selection
- Any scenario requiring a minimum and maximum value

Key differences from regular Slider:
- Regular Slider: Single value (e.g., volume level)
- RangeSlider: Two values (e.g., price from $20 to $80)

How it works:
1. value: A ClosedFloatingPointRange (e.g., 20f..80f)
   - .start gives the lower bound (20f)
   - .endInclusive gives the upper bound (80f)

2. onValueChange: Called when either thumb is dragged
   - Receives the new range as a parameter
   - Updates the state with both start and end values

3. steps: Creates discrete snap points between min and max
   - With steps=5 and valueRange 0f..100f:
     * Creates positions: 0, 20, 40, 60, 80, 100
   - Both thumbs snap to these positions

4. onValueChangeFinished: Called when user releases the thumb
   - Useful for triggering actions after selection
   - Example: Apply price filter, make API call, save preference

5. valueRange: The overall min and max bounds
   - The selected range must be within these bounds
   - Example: 0f..100f means both thumbs can move between 0 and 100
*/

@Composable
fun RangeSliderExample() {
    // State holds both start and end values as a range
    var sliderPosition by remember { mutableStateOf(0f..100f) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Select a range:")

        RangeSlider(
            value = sliderPosition,
            steps = 5,
            onValueChange = { range ->
                // Update state with new range
                sliderPosition = range
            },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // Called when user lifts their finger
                // You can trigger actions here like filtering, saving, etc.
            }
        )

        // Display the selected range
        Text(
            text = "Selected range: ${sliderPosition.start.toInt()} - ${sliderPosition.endInclusive.toInt()}",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}