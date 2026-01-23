package com.example.FullComposeOfficialDocumentation.Components_3.Slider_24

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Advanced Slider with customization options.

Key parameters explained:

1. value: Current position of the slider (must be within valueRange)

2. onValueChange: Lambda called when user drags the slider
   - Updates the state with new slider position
   - Called continuously as user drags

3. valueRange: Defines the minimum and maximum values
   - Format: min..max (e.g., 0f..50f means slider goes from 0 to 50)
   - The slider position will be mapped to this range
   - Default is 0f..1f if not specified

4. steps: Number of discrete stops between min and max
   - If steps = 3, the slider will have 5 positions total:
     * Start position (min)
     * 3 intermediate steps
     * End position (max)
   - Example: valueRange 0f..50f with steps=3 creates positions: 0, 12.5, 25, 37.5, 50
   - steps = 0 means continuous movement (no discrete stops)

5. colors: Customizes the slider appearance
   - thumbColor: Color of the draggable circle/thumb
   - activeTrackColor: Color of the track before the thumb (filled portion)
   - inactiveTrackColor: Color of the track after the thumb (unfilled portion)

Why use these parameters:
- valueRange: To map slider to meaningful values (e.g., volume 0-100, temperature 0-50)
- steps: To restrict user to specific values (e.g., rating 1-5, preset levels)
- colors: To match app theme or indicate different states
*/

@Composable
fun AdvancedSlider() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.padding(16.dp)) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            steps = 3,
            /*
            The thumb snaps only to these 5 fixed values.

             You cannot stop at 10, 18, 44, etc.

               It jumps: 0 → 12.5 → 25 → 37.5 → 50
            */
            valueRange = 0f..50f
        )

        // Display current slider value
        Text(text = "Current value: ${sliderPosition.toInt()}")
    }
}