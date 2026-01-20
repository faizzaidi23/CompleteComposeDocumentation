package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Side_Effects_2

/*
Disposable effect--> is for any background task  that needs to be cleaned up when the UI disappears
*/

/*
Automatic Cleanup vs. Manual Cleanup
The key difference is what kind of background task you are running.

LaunchedEffect & rememberCoroutineScope
These effects deal with coroutines. A coroutine is a job that "lives" inside a CoroutineScope.

Their Cleanup: When a composable leaves the screen, the Compose framework automatically cancels the scope.
This cancellation signal travels down to all the coroutines running inside it, telling them to stop.
 The while(isActive) loop stops, delay is cancelled, and everything is cleaned up automatically.
 You don't need to write any special cleanup code.
*/