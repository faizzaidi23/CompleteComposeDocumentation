package com.example.FullComposeOfficialDocumentation.Components_3.BottomSheets_4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
═══════════════════════════════════════════════════════════════════════════════
                    COROUTINE SCOPE EXPLAINED
═══════════════════════════════════════════════════════════════════════════════

What is rememberCoroutineScope() and why do we need it?

val scope = rememberCoroutineScope()

This gives you a CoroutineScope that is tied to the composable lifecycle.

THINK OF IT AS: A safe playground to run async operations that are bound to your UI

═══════════════════════════════════════════════════════════════════════════════
*/

/*
WHAT IS A COROUTINE SCOPE?

A CoroutineScope is a context that manages the lifecycle of coroutines.

ANALOGY:
Think of it as a "manager" that:
- Starts coroutines (async tasks)
- Tracks them while running
- Cancels them when no longer needed
- Prevents memory leaks

rememberCoroutineScope() specifically:
✓ Created when composable enters composition
✓ Cancelled when composable leaves composition
✓ Automatically cleans up coroutines
✓ Safe for UI operations

═══════════════════════════════════════════════════════════════════════════════
*/

/*
WHY DO WE NEED IT?

REASON 1: Suspend Functions Can't Be Called Directly
─────────────────────────────────────────────────────

sheetState.hide() is a SUSPEND FUNCTION

This WON'T work:

onClick = {
    sheetState.hide()  // ❌ ERROR: suspend function cannot be called here
}

ERROR MESSAGE:
"Suspend function 'hide' should be called only from a coroutine or another suspend function"


You MUST call it inside a coroutine:

onClick = {
    scope.launch {
        sheetState.hide()  // ✅ WORKS: Called inside coroutine
    }
}


REASON 2: Animations Take Time
───────────────────────────────

sheetState.hide() doesn't complete instantly - it runs an animation!

Without coroutines:
- UI thread would freeze waiting for animation
- App would feel janky and unresponsive

With coroutines:
- Animation runs asynchronously
- UI stays responsive
- Smooth user experience


REASON 3: Automatic Cleanup
────────────────────────────

When composable is removed:
- rememberCoroutineScope() automatically cancels all running coroutines
- Prevents memory leaks
- No manual cleanup needed

If you navigate away while animation is running:
- Coroutine is cancelled
- No crash, no leak
- Everything cleaned up automatically

═══════════════════════════════════════════════════════════════════════════════
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoroutineScopeExampleWrong() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("❌ WRONG APPROACH (Won't compile):", style = MaterialTheme.typography.titleMedium)

        Button(
            onClick = {
                // ❌ This will NOT compile
                // sheetState.hide()
                // Error: Suspend function 'hide' should be called only from coroutine
            }
        ) {
            Text("Try to hide (ERROR)")
        }
    }
}

/*
EXPLANATION OF ERROR:

sheetState.hide() is marked as "suspend"

suspend fun hide()

This means:
- It can pause execution without blocking the thread
- Must be called from a coroutine context
- Cannot be called from regular onClick callback

Regular onClick is NOT a coroutine context:
onClick = { }  // ❌ Not a coroutine

Coroutine context:
scope.launch { }  // ✅ IS a coroutine
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoroutineScopeExampleCorrect() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()  // ← Get the scope
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("✅ CORRECT APPROACH:", style = MaterialTheme.typography.titleMedium)

        Button(onClick = { showBottomSheet = true }) {
            Text("Show Sheet")
        }

        if (showBottomSheet) {
            Text("Sheet is visible!", color = MaterialTheme.colorScheme.primary)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Bottom Sheet Content")

                Button(
                    onClick = {
                        // ✅ Launch coroutine to call suspend function
                        scope.launch {
                            sheetState.hide()  // Now we can call suspend function
                        }
                    }
                ) {
                    Text("Hide with Animation")
                }
            }
        }
    }
}

/*
STEP-BY-STEP BREAKDOWN:

1. Create the scope:
   val scope = rememberCoroutineScope()
   - This creates a CoroutineScope
   - Tied to this composable's lifecycle

2. Button click happens:
   onClick = { ... }
   - Regular callback, NOT a coroutine

3. Launch coroutine:
   scope.launch { ... }
   - Creates a NEW coroutine
   - Runs asynchronously
   - Doesn't block UI

4. Call suspend function:
   sheetState.hide()
   - Now we're inside coroutine, so it works
   - Starts hide animation
   - Suspends while animating
   - UI stays responsive

5. Animation completes:
   - Coroutine resumes
   - Coroutine finishes
   - Scope automatically cleans up
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    LIFECYCLE MANAGEMENT
═══════════════════════════════════════════════════════════════════════════════

rememberCoroutineScope() vs Other Scopes:

┌─────────────────────────┬────────────────────────────────────────────┐
│ Scope Type              │ Lifecycle                                  │
├─────────────────────────┼────────────────────────────────────────────┤
│ GlobalScope             │ Lives forever (app lifetime)               │
│                         │ ⚠️ DANGEROUS - can cause memory leaks      │
├─────────────────────────┼────────────────────────────────────────────┤
│ viewModelScope          │ Lives as long as ViewModel                 │
│                         │ Use for business logic                     │
├─────────────────────────┼────────────────────────────────────────────┤
│ rememberCoroutineScope  │ Lives as long as composable                │
│                         │ ✅ Perfect for UI animations               │
└─────────────────────────┴────────────────────────────────────────────┘

LIFECYCLE EXAMPLE:

@Composable
fun MyScreen() {
    val scope = rememberCoroutineScope()  // ← Created

    Button(onClick = {
        scope.launch {
            delay(10000)  // Long running task
        }
    }) { Text("Click") }

}  // ← If screen navigates away, scope is CANCELLED


AUTOMATIC CLEANUP:

User clicks button → scope.launch starts coroutine
User navigates away → Composable leaves composition
                    → scope is cancelled
                    → All coroutines stopped
                    → No memory leak!

Without this:
- Coroutines would continue running
- Trying to update non-existent UI
- Crash or memory leak

═══════════════════════════════════════════════════════════════════════════════
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LifecycleDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var lifecycleMessage by remember { mutableStateOf("Scope is active") }

    DisposableEffect(Unit) {
        lifecycleMessage = "✅ Scope created with composable"

        onDispose {
            lifecycleMessage = "🗑️ Scope cancelled when composable removed"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lifecycle Demo", style = MaterialTheme.typography.titleLarge)
        Text(lifecycleMessage)

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
                Text("When you close this sheet:")
                Text("• scope.launch completes normally")
                Text("• If you navigate away mid-animation:")
                Text("• scope gets cancelled automatically")
                Text("• No memory leaks!")
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                    WHAT HAPPENS DURING scope.launch
═══════════════════════════════════════════════════════════════════════════════

LINE BY LINE EXECUTION:

scope.launch {
    sheetState.hide()
}

STEP 1: scope.launch { ... }
- Creates a new coroutine
- Returns immediately (doesn't wait)
- Coroutine runs asynchronously

STEP 2: sheetState.hide() starts
- Animation begins
- Function "suspends" (pauses without blocking)
- UI thread continues normally

STEP 3: Animation runs
- Frame by frame updates
- Sheet slides down
- UI stays responsive

STEP 4: Animation completes
- hide() function resumes
- Returns result
- Coroutine finishes

ENTIRE PROCESS:
- UI thread: NEVER blocked
- Animation: Smooth 60fps
- User: Can still interact with other UI
- App: Feels responsive

═══════════════════════════════════════════════════════════════════════════════
*/

/*
REAL-WORLD ANALOGY:

Imagine you're cooking (UI thread):

WITHOUT COROUTINES:
You: "I need to bake a cake"
     [Stand in front of oven for 30 minutes doing nothing]
     [Can't do anything else]
     [Kitchen freezes]

WITH COROUTINES:
You: "Hey oven (coroutine), bake this cake"
     [Oven starts baking asynchronously]
     [You continue chopping vegetables]
     [You can cook other things]
     [When cake is done, oven notifies you]
     [Kitchen stays productive]

scope.launch = Delegating task to run separately
sheetState.hide() = The baking task
UI thread = You, free to do other work
*/

/*
COMMON PATTERNS:

PATTERN 1: Simple launch
─────────────────────────
scope.launch {
    sheetState.hide()
}

PATTERN 2: Multiple operations
───────────────────────────────
scope.launch {
    sheetState.hide()
    delay(500)
    // Do something after hide completes
}

PATTERN 3: With error handling
───────────────────────────────
scope.launch {
    try {
        sheetState.hide()
    } catch (e: Exception) {
        // Handle errors
    }
}

PATTERN 4: With completion callback (see next file)
────────────────────────────────────────────────────
scope.launch {
    sheetState.hide()
}.invokeOnCompletion {
    // Called when animation finishes
}
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          KEY TAKEAWAYS
═══════════════════════════════════════════════════════════════════════════════

1. rememberCoroutineScope()
   → Creates scope tied to composable lifecycle
   → Automatically cancelled when composable removed
   → Prevents memory leaks

2. scope.launch { }
   → Starts a new coroutine
   → Runs asynchronously
   → Doesn't block UI

3. Suspend functions (like sheetState.hide())
   → Must be called from coroutine
   → Can pause without blocking
   → Allow smooth animations

4. Why this pattern?
   → Keep UI responsive
   → Smooth animations
   → Automatic cleanup
   → No memory leaks

5. When to use?
   → Calling suspend functions
   → Running animations
   → Async UI operations
   → Any time-consuming UI task

═══════════════════════════════════════════════════════════════════════════════
*/

