package com.example.FullComposeOfficialDocumentation.Components_3.BottomSheets_4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
═══════════════════════════════════════════════════════════════════════════════
                    HIDE ANIMATION & INVOKE ON COMPLETION
═══════════════════════════════════════════════════════════════════════════════

How does scope.launch { sheetState.hide() } work exactly?

And what is invokeOnCompletion doing?

This file explains the entire hide animation flow step-by-step

═══════════════════════════════════════════════════════════════════════════════
*/

/*
THE COMPLETE HIDE PATTERN:

scope.launch { sheetState.hide() }.invokeOnCompletion {
    if (!sheetState.isVisible) {
        showBottomSheet = false
    }
}

This pattern does THREE things:
1. Starts hide animation
2. Waits for animation to finish
3. Removes composable from UI tree

Let's break down each part...
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    PART 1: scope.launch { sheetState.hide() }
═══════════════════════════════════════════════════════════════════════════════

WHAT HAPPENS STEP-BY-STEP:

STEP 1: User clicks the hide button
────────────────────────────────────
onClick = {
    scope.launch { ... }
}
- onClick callback is triggered
- Execution continues immediately


STEP 2: scope.launch { } starts
────────────────────────────────
scope.launch {
    sheetState.hide()
}
- Creates a NEW coroutine
- Returns a Job object immediately
- Code inside { } runs asynchronously


STEP 3: sheetState.hide() begins
─────────────────────────────────
- hide() is a suspend function
- Starts the slide-down animation
- Function "suspends" (pauses) while animating
- UI thread is NOT blocked


STEP 4: Animation runs
──────────────────────
- Bottom sheet slides down smoothly
- Frame-by-frame animation updates
- Takes ~300ms (default animation duration)
- User can still interact with other UI


STEP 5: Animation completes
────────────────────────────
- hide() function resumes
- Returns from suspension
- Coroutine continues to completion


VISUAL TIMELINE:

Time 0ms:    Button clicked
             ↓
Time 1ms:    scope.launch starts
             ↓
Time 2ms:    sheetState.hide() begins animation
             onClick returns (UI free again!)
             ↓
Time 2-300ms: Animation running (sheet sliding down)
             UI stays responsive
             ↓
Time 300ms:  Animation complete
             hide() returns
             Coroutine finishes
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HideAnimationDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var animationStatus by remember { mutableStateOf("Ready") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Animation Status: $animationStatus")
        Button(onClick = { showBottomSheet = true }) {
            Text("Show Sheet")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Watch the status above!")

                Button(onClick = {
                    animationStatus = "Animation started..."

                    scope.launch {
                        sheetState.hide()
                        animationStatus = "Animation completed!"
                    }
                }) {
                    Text("Hide with Animation")
                }
            }
        }
    }
}

/*
NOTICE IN THE DEMO:
- When you click "Hide with Animation"
- Status immediately changes to "Animation started..."
- Sheet slides down smoothly
- After animation: "Animation completed!"

This shows the async nature of the animation.
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    PART 2: .invokeOnCompletion { }
═══════════════════════════════════════════════════════════════════════════════

WHAT IS invokeOnCompletion?

scope.launch { sheetState.hide() }
    .invokeOnCompletion { ... }
    ↑
    This is called when the coroutine FINISHES

It's a callback that runs AFTER the coroutine completes (successfully or not)

SYNTAX:
val job = scope.launch {
    // Coroutine work
}
job.invokeOnCompletion {
    // Called when job finishes
}

Or chained:
scope.launch {
    // Work
}.invokeOnCompletion {
    // After work completes
}


WHY DO WE NEED IT?

Without invokeOnCompletion:
───────────────────────────

scope.launch {
    sheetState.hide()
}
showBottomSheet = false  // ❌ WRONG! Happens IMMEDIATELY

Problem:
- showBottomSheet = false executes RIGHT AWAY
- Animation hasn't even started yet
- Sheet disappears instantly (no animation)


With invokeOnCompletion:
────────────────────────

scope.launch {
    sheetState.hide()
}.invokeOnCompletion {
    showBottomSheet = false  // ✅ CORRECT! Happens AFTER animation
}

Result:
- Animation runs first
- AFTER animation finishes → invokeOnCompletion called
- THEN showBottomSheet = false
- Sheet removed from composition AFTER smooth animation
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    THE COMPLETE PATTERN EXPLAINED
═══════════════════════════════════════════════════════════════════════════════

scope.launch { sheetState.hide() }.invokeOnCompletion {
    if (!sheetState.isVisible) {
        showBottomSheet = false
    }
}

PART BY PART:

1. scope.launch {
   - Starts a coroutine
   - Returns Job object

2. sheetState.hide()
   - Suspend function
   - Runs hide animation
   - Suspends while animating

3. }.invokeOnCompletion {
   - Registers callback
   - Called when coroutine finishes

4. if (!sheetState.isVisible) {
   - Safety check
   - Ensures sheet is actually hidden

5. showBottomSheet = false
   - Remove from composition
   - Clean up


WHY THE if (!sheetState.isVisible) CHECK?

Safety check for edge cases:
- User might cancel animation mid-way
- State might change during animation
- Ensures we only remove when actually hidden

Best practice: Always check state before cleanup
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvokeOnCompletionDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var eventLog by remember { mutableStateOf(listOf<String>()) }

    fun log(message: String) {
        eventLog = eventLog + message
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Event Log:", style = MaterialTheme.typography.titleMedium)
        eventLog.forEach { event ->
            Text("• $event", style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            log("Show button clicked")
            showBottomSheet = true
        }) {
            Text("Show Sheet")
        }

        Button(onClick = { eventLog = listOf() }) {
            Text("Clear Log")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                log("Dismissed by swipe/tap outside")
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Watch the event log above!")

                Button(onClick = {
                    log("Hide button clicked")

                    scope.launch {
                        log("Animation starting...")
                        sheetState.hide()
                        log("Animation finished!")
                    }.invokeOnCompletion {
                        log("invokeOnCompletion called")
                        if (!sheetState.isVisible) {
                            log("Sheet confirmed hidden")
                            showBottomSheet = false
                            log("Removed from composition")
                        }
                    }
                }) {
                    Text("Hide with Animation")
                }
            }
        }
    }
}

/*
EXPECTED LOG SEQUENCE:

1. Show button clicked
2. Hide button clicked
3. Animation starting...
4. Animation finished!
5. invokeOnCompletion called
6. Sheet confirmed hidden
7. Removed from composition

This shows the EXACT order of execution!
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    WHY TWO-STEP REMOVAL?
═══════════════════════════════════════════════════════════════════════════════

QUESTION: Why not just do showBottomSheet = false immediately?

onClick = {
    showBottomSheet = false  // Why not just this?
}


ANSWER: You lose the animation!

TWO-STEP PROCESS NEEDED:

Step 1: Animate the sheet (visual hide)
────────────────────────────────────────
sheetState.hide()
- Sheet slides down
- User sees smooth motion
- Takes ~300ms


Step 2: Remove from composition (actual hide)
──────────────────────────────────────────────
showBottomSheet = false
- Removes ModalBottomSheet from UI tree
- Frees memory
- Clean state


COMPARISON:

❌ Instant Removal (No animation):
showBottomSheet = false
→ Sheet disappears immediately
→ Jarring user experience
→ No Material Design motion


✅ Animated Removal (Smooth):
sheetState.hide() → animate
→ invokeOnCompletion → wait for animation
→ showBottomSheet = false → cleanup
→ Smooth, professional feel


═══════════════════════════════════════════════════════════════════════════════
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Try both approaches:", style = MaterialTheme.typography.titleMedium)

        Button(onClick = { showBottomSheet = true }) {
            Text("Show Sheet")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Compare these two approaches:")

                Button(
                    onClick = {
                        // ❌ Instant (no animation)
                        showBottomSheet = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("❌ Hide Instantly (Bad UX)")
                }

                Button(
                    onClick = {
                        // ✅ Animated (smooth)
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("✅ Hide with Animation (Good UX)")
                }
            }
        }
    }
}

/*
TRY IT:
1. Click "Show Sheet"
2. Try "Hide Instantly" - Notice the abrupt disappearance
3. Show sheet again
4. Try "Hide with Animation" - Notice the smooth slide down

The difference is night and day!
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    EXECUTION TIMELINE
═══════════════════════════════════════════════════════════════════════════════

DETAILED TIMELINE OF HIDE OPERATION:

T=0ms: User clicks hide button
       ↓
T=1ms: onClick callback executes
       scope.launch { ... } starts
       Returns Job object
       invokeOnCompletion registered
       onClick returns (UI free!)
       ↓
T=2ms: Coroutine begins execution
       sheetState.hide() called
       Animation starts
       Coroutine suspends
       ↓
T=2-300ms: ANIMATION RUNNING
          Sheet slides down frame by frame
          User can still interact with UI
          UI thread doing other work
          ↓
T=300ms: Animation completes
         hide() returns
         Coroutine resumes
         Coroutine reaches end
         ↓
T=301ms: Coroutine marked complete
         invokeOnCompletion callback triggered
         ↓
T=302ms: Callback executes
         Checks: !sheetState.isVisible
         True? Set showBottomSheet = false
         ↓
T=303ms: Recomposition triggered
         if (showBottomSheet) evaluates to false
         ModalBottomSheet removed from composition
         ↓
T=304ms: Cleanup complete
         Memory freed
         Done!


TOTAL TIME: ~300ms for smooth, professional animation
vs
INSTANT: 0ms but terrible user experience

═══════════════════════════════════════════════════════════════════════════════
*/

/*
KEY CONCEPTS SUMMARY:

1. scope.launch { }
   → Starts async coroutine
   → Returns immediately
   → Doesn't block UI

2. sheetState.hide()
   → Suspend function
   → Runs animation
   → Suspends while animating

3. .invokeOnCompletion { }
   → Callback after coroutine finishes
   → Perfect for cleanup
   → Called automatically

4. if (!sheetState.isVisible)
   → Safety check
   → Ensures sheet actually hidden
   → Best practice

5. showBottomSheet = false
   → Remove from composition
   → After animation completes
   → Clean state management

TWO-LAYER SYSTEM:
- Layer 1 (Animation): sheetState.hide()
- Layer 2 (Composition): showBottomSheet = false

Both needed for smooth, professional behavior!
*/

/*
COMMON MISTAKES TO AVOID:

❌ MISTAKE 1: Set false immediately
onClick = {
    showBottomSheet = false  // No animation!
}

❌ MISTAKE 2: Call hide without scope
onClick = {
    sheetState.hide()  // Won't compile!
}

❌ MISTAKE 3: Set false before animation
onClick = {
    scope.launch { sheetState.hide() }
    showBottomSheet = false  // Executes immediately!
}

✅ CORRECT: Wait for animation
onClick = {
    scope.launch { sheetState.hide() }.invokeOnCompletion {
        if (!sheetState.isVisible) {
            showBottomSheet = false
        }
    }
}
*/

