package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

/*
collectAsStateWithLifecycle--->This is the pro version this is also takes care about the android lifecycle like
it does not fetch data when the app is not open and this must be the default use


collectAsState---this does not know about the android lifecycle

Warning: If you use this in a standard Android app,
the Flow will continue to be collected even when
your app is in the background, which is inefficient.
*/