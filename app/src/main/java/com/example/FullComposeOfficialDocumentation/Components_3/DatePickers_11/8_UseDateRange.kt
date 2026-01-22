package com.example.FullComposeOfficialDocumentation.Components_3.DatePickers_11

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UseDateRangeExample() {
    var selectedDateRange by remember { mutableStateOf<Pair<Long?, Long?>>(Pair(null, null)) }
    var showModal by remember { mutableStateOf(false) }

    Column {
        if (selectedDateRange.first != null && selectedDateRange.second != null) {
            val startDate = Date(selectedDateRange.first!!)
            val endDate = Date(selectedDateRange.second!!)
            val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            Text("Start date: ${formatter.format(startDate)}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("End date: ${formatter.format(endDate)}")
        } else {
            Text("No date range selected")
        }
    }

    if (showModal) {
        DateRangePickerModalExample(
            onDateRangeSelected = {
                selectedDateRange = it
                showModal = false
            },
            onDismiss = { showModal = false }
        )
    }
}
