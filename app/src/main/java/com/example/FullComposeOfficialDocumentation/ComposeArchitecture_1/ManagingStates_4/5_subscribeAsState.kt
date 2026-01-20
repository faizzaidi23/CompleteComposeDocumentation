package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

/*
Think of it like this: your app's UI (what the user sees) and your app's data (from the internet or database) need to talk to each other.

Jetpack Compose UI: Only understands one language: State. When a State object changes, the UI automatically updates.

RxJava: A popular system for handling data that changes over time. It speaks a different language, using things like Observable, Single, or Completable.

The problem is that Compose doesn't understand RxJava's language.

subscribeAsState() is simply a translator. It listens to the RxJava data stream and translates every new piece of data into a State object that Compose can understand.

So, the flow is: RxJava Observable -> subscribeAsState() (Translator) -> Compose State -> UI Updates.

## When Do You Need to Use This?
You use this concept almost exclusively for connecting older, existing data systems to your new Compose UI. It's a bridge to make old and new code work together.

Here are the main situations:

1. Migrating an Existing App to Compose
The Scenario: You have a big, successful app that was built years ago. All its networking and database logic was written using RxJava. Now, you want to start using Jetpack Compose for new screens or to slowly rewrite old ones.

The Need: You can't afford to stop and rewrite your entire app's data logic. You need your new Compose UI to work with your existing RxJava ViewModels.

The Solution: You use subscribeAsState() to connect your new Composable functions directly to the Observable streams in your old ViewModels. This lets you modernize your UI without touching your stable, working data layer.

2. Using a Library That Uses RxJava
The Scenario: You find a great third-party library that does exactly what you need (e.g., handles Bluetooth, real-time data, etc.). However, the library was created before Compose was popular, and it gives you data using RxJava.

The Need: You have to get the data from this library and show it in your modern Compose UI.

The Solution: You use subscribeAsState() to translate the library's RxJava output into a State object that your UI can easily display.

3. Your Team Prefers or is Expert in RxJava
The Scenario: Your development team has years of experience with RxJava. They are very skilled at using its powerful features to handle complex data operations. They can work faster and more reliably by writing the data logic in RxJava.

The Need: The team wants to use their powerful RxJava skills for the app's "brain" but also wants to use the new, efficient Jetpack Compose for the "face" of the app.

The Solution: They write the ViewModels using RxJava and then use subscribeAsState() as the simple bridge to connect to their Compose UI code.

In short, subscribeAsState() is a compatibility tool. You use it when your app's data logic is in RxJava, but your UI is in Compose.
*/

//
//class counterViewModel:ViewModel(){
//    val counter:Observable<Long>=Observable.interval(1,TimeUnit.SECONDS)
//}
//
//@Composable
//fun CounterScreen(counterViewModel: counterViewModel){
//
//    val currentCount by counterViewModel.counter.subscribeAsState(initial=0)
//
//    Box(
//        modifier=Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//
//    ){
//        Text(
//            text="$currentCount",
//            style=TextStyle(fontSize=50.sp)
//        )
//    }
//}
