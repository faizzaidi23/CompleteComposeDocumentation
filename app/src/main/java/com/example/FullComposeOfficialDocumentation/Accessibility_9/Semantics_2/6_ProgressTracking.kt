package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Progress Tracking Components:

For custom components that track progress, you might want to notify users of their progress
changes, including the current progress value, its range, and step size.

You can do so with progressBarRangeInfo semantics—this ensures that accessibility services are
aware of progress changes, and can update users accordingly.

Different assistive technologies may also have unique ways of hinting at increasing and
decreasing progression.
*/

@Composable
fun ProgressInfoBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Upload Progress: ${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .semantics {
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = progress,
                        range = 0F..1F
                    )
                }
        )
    }
}

/*
The ProgressBarRangeInfo has three parameters:

- current: The current progress value
- range: The range of possible values (usually 0F..1F for percentage-based progress)
- steps: Optional parameter for discrete steps in the progress
*/

@Composable
fun ProgressBarExample() {
    var progress by remember { mutableFloatStateOf(0.3f) }

    ProgressInfoBar(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
Benefits:

- Accessibility services can announce progress changes to users
- Users are informed of upload/download progress
- Assistive technologies can provide unique feedback for progress changes
- Better user experience for users with accessibility needs
*/

@Composable
fun CustomProgressBar(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier
) {
    val progress = currentStep.toFloat() / totalSteps.toFloat()

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Step $currentStep of $totalSteps",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .semantics {
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = currentStep.toFloat(),
                        range = 0F..totalSteps.toFloat(),
                        steps = totalSteps - 1
                    )
                }
        )
    }
}

/*
Use cases:

- File uploads/downloads
- Multi-step forms
- Loading states
- Any process that has measurable progress
*/
