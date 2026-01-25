package com.example.FullComposeOfficialDocumentation.Text_Typography_5.EnableUserInteractions_6

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink

/*
 ═══════════════════════════════════════════════════════════════════════════════
 CUSTOM CLICK HANDLER FOR LINKS
 ═══════════════════════════════════════════════════════════════════════════════

 This demonstrates a PROGRAMMABLE link (not just a declarative one)

 Key difference from simple links:
 - Simple link: User clicks → Compose auto-opens browser
 - This version: User clicks → YOUR CODE runs → you decide what happens

 Think of it like HTML:
 - Simple: <a href="url">text</a>
 - This: <a href="url" onclick="myFunction()">text</a>

 Why you need this:
 - Log analytics ("User clicked Compose link")
 - Show confirmation dialog before opening
 - Open in in-app WebView instead of external browser
 - Block certain links based on conditions
 - Navigate inside app instead of browser
 - Track A/B experiments
 - Modify URL dynamically
 - Check if user is logged in before opening

 Real-world example:
 if (user.isLoggedIn) {
     openUrl(url)
 } else {
     showLoginDialog()
 }

 You CANNOT do this with the simple declarative link version
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 LINE: val uriHandler = LocalUriHandler.current
 ═══════════════════════════════════════════════════════════════════════════════

 WHAT IS LocalUriHandler?
 - A CompositionLocal provided by Compose
 - It's a global key (a holder) that holds a value of type UriHandler
 - Gives you an object that knows how to open URIs using the Android system

 WHAT DOES .current MEAN?
 LocalUriHandler.current means:
 "Give me the UriHandler for the current composable environment"

 Similar to:
 - LocalContext.current → gives Android Context
 - LocalDensity.current → gives screen density
 - LocalConfiguration.current → gives device config

 WHAT IS LocalUriHandler ACTUALLY?
 - NOT a normal class you instantiate
 - It's a CompositionLocal object (a provider)
 - Declared internally like: val LocalUriHandler = staticCompositionLocalOf<UriHandler> { ... }
 - LocalUriHandler → the provider/key
 - uriHandler → instance of UriHandler (the actual object you get)

 WHAT DOES uriHandler DO?
 It gives you one main function: uriHandler.openUri(url)

 This will:
 - Open browser for https://
 - Open dialer for tel:9876543210
 - Open email app for mailto:someone@gmail.com
 - Open maps for geo:28.6139,77.2090

 WHY IS IT CALLED URI AND NOT URL?

 URI vs URL:
 - URI = General identifier for a resource (broader term)
 - URL = A specific type of URI that gives a web location
 - All URLs are URIs, but not all URIs are URLs

 Examples:
 - https://google.com → URL + URI
 - mailto:someone@gmail.com → URI (not URL)
 - tel:9876543210 → URI (not URL)
 - geo:28.6139,77.2090 → URI (not URL)

 Compose uses URI because it can handle many types of links, not just web URLs

 Instead of writing low-level Android code:
 val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
 context.startActivity(intent)

 Compose gives you the clean abstraction:
 uriHandler.openUri("https://google.com")
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 LINE: Text(buildAnnotatedString { ... })
 ═══════════════════════════════════════════════════════════════════════════════

 IMPORTANT MISCONCEPTION TO CLEAR:
 You are NOT inside Text, you are inside a LAMBDA FUNCTION

 buildAnnotatedString { ... } is NOT UI code
 It's just a normal Kotlin lambda block

 Inside this lambda you can:
 - Declare variables (val link = ...)
 - Use if/else
 - Call functions
 - Do loops
 - Build objects

 It's exactly like any normal Kotlin function

 What's actually happening:

 Your code (inline):
 Text(buildAnnotatedString {
     val link = ...
     append("Hello")
 })

 Is the same as (separated):
 val annotated = buildAnnotatedString {
     val link = ...
     append("Hello")
 }
 Text(annotated)

 Step-by-step execution:
 1. buildAnnotatedString { ... } starts
 2. Kotlin executes everything inside the lambda:
    - Appends text
    - Creates link object
    - Applies link to some text
 3. The lambda returns an AnnotatedString
 4. That final AnnotatedString is passed into Text()
 5. Text() renders it

 So val link exists only while building the text
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 LINE: val link = LinkAnnotation.Url(...) { ... }
 ═══════════════════════════════════════════════════════════════════════════════

 WHAT ARE YOU STORING IN link?
 You are storing an OBJECT that contains:
 1. Data (URL string, styling)
 2. Behavior (the code to run when clicked)

 NOT just the URL
 NOT just the style
 But also the CODE that should run when it is clicked

 Think of it like a class instance:

 class MyLink(
     val url: String,
     val style: SpanStyle,
     val onClick: () -> Unit
 )

 val link = MyLink(
     url = "...",
     style = blue,
     onClick = { println("clicked") }
 )

 That's the exact same pattern

 The link object contains:
 1. The URL string → "https://developer.android.com/jetpack/compose"
 2. The styling information → TextLinkStyles(SpanStyle(color = Color.Blue))
 3. The click handler lambda → the code inside { ... }

 It is a SELF-CONTAINED CLICKABLE INSTRUCTION OBJECT

 Important: Closures
 The lambda can use variables from outside (like uriHandler)
 This works because Kotlin lambdas are CLOSURES
 They "capture" variables from the surrounding scope
 Even after buildAnnotatedString finishes, the lambda remembers uriHandler

 Mental model:
 link is not just data — it's an object that carries BEHAVIOR inside it
 Think of it as a button configuration:
 "When user clicks this text, run this code"
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TWO VERSIONS OF LinkAnnotation.Url
 ═══════════════════════════════════════════════════════════════════════════════

 VERSION 1: WITH LAMBDA (Custom behavior) - This file's approach

 LinkAnnotation.Url(
     "https://developer.android.com/jetpack/compose",
     TextLinkStyles(SpanStyle(color = Color.Blue))
 ) { annotation →
     val url = (annotation as LinkAnnotation.Url).url
     uriHandler.openUri(url)
 }

 This means: "When this link is clicked, run MY code"
 You are OVERRIDING the default behavior
 Compose does NOT auto-open the link anymore
 You must manually decide what to do

 VERSION 2: WITHOUT LAMBDA (Default behavior)

 LinkAnnotation.Url(
     "https://developer.android.com/",
     TextLinkStyles(style = SpanStyle(color = Color.Blue))
 )

 This means: "This is a normal link. Let Compose handle clicks"
 Compose will automatically:
 - Detect tap
 - Extract URL
 - Call system handler
 - Open browser

 WHY BOTH VERSIONS EXIST?

 Simple apps: Just open the link → Use no lambda
 Real apps: Need control (analytics, dialogs, etc) → Use lambda

 Comparison:

 ┌─────────────────────┬──────────────────┬──────────────────┐
 │ Feature             │ No Lambda        │ With Lambda      │
 ├─────────────────────┼──────────────────┼──────────────────┤
 │ Who handles click   │ Compose handles  │ You handle       │
 │ Write code?         │ No               │ Yes              │
 │ Analytics tracking  │ ❌               │ ✅               │
 │ Conditional logic   │ ❌               │ ✅               │
 │ Custom navigation   │ ❌               │ ✅               │
 │ Modify behavior     │ ❌               │ ✅               │
 └─────────────────────┴──────────────────┴──────────────────┘

 Analogy:
 No lambda = declarative link (like HTML <a> tag)
 Lambda = programmable link (like HTML with onclick handler)
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 LINE: val url = (it as LinkAnnotation.Url).url
 ═══════════════════════════════════════════════════════════════════════════════

 This code exists inside the lambda:

 LinkAnnotation.Url(...) {
     val url = (it as LinkAnnotation.Url).url  // ← This line
     uriHandler.openUri(url)
 }

 BREAKING IT DOWN PIECE BY PIECE:

 1. WHERE DOES 'it' COME FROM?

 This { ... } is a lambda function
 Compose calls this lambda when the link is clicked
 Compose passes ONE ARGUMENT into this lambda:
 - The annotation that was clicked

 Since you didn't name the parameter, Kotlin uses default name: it

 So: it = the clicked LinkAnnotation

 2. WHAT IS THE TYPE OF 'it'?

 Compose gives it as a general type: LinkAnnotation (generic)
 But your specific annotation is actually: LinkAnnotation.Url (specific)

 3. WHY IS THE CAST NEEDED?

 Without casting, this would fail:
 it.url   // ❌ Not allowed - Kotlin doesn't know it has .url

 Because Kotlin only knows:
 - it is LinkAnnotation (generic type)
 - But only LinkAnnotation.Url has the .url property

 So you must cast it:
 (it as LinkAnnotation.Url).url  // ✅ Now Kotlin knows it has .url

 4. WHAT DOES 'as' MEAN IN SIMPLE WORDS?

 it as LinkAnnotation.Url

 Means: "Treat this object as a Url-type link, not just a generic link"

 Like saying: "This animal is not just an Animal, it is a Dog"
 Only then can you access dog-specific features

 5. WHAT DOES THIS LINE ACTUALLY DO?

 val url = (it as LinkAnnotation.Url).url

 Step by step:
 - Take the clicked link object (it)
 - Cast it to LinkAnnotation.Url type
 - Extract its .url property (the URL string)
 - Store it in the url variable

 Example result:
 url = "https://developer.android.com/jetpack/compose"

 6. WHY IS THIS NEEDED IF WE ALREADY PROVIDED THE URL EARLIER?

 Because once you provide a custom lambda, Compose assumes:
 "Developer wants full control"

 So Compose does NOT auto-open the URL anymore

 Now it's YOUR responsibility to:
 - Decide what to do with the URL
 - Whether to open it
 - Whether to block it
 - Whether to log analytics
 - Whether to show a dialog

 That's why you must manually extract the URL and decide what to do

 7. COMPLETE FLOW WHEN USER CLICKS:

 Step 1: User taps the link
 Step 2: Compose detects the click
 Step 3: Compose finds the LinkAnnotation attached to that text
 Step 4: Compose sees it has a custom click handler lambda
 Step 5: Compose calls your lambda and passes the clicked annotation as 'it'
 Step 6: Your code runs:
         val url = (it as LinkAnnotation.Url).url  // Extract URL
         uriHandler.openUri(url)                    // Open it

 SIMPLE ANALOGY:
 Think of this like a button:

 Button(onClick = { println("Clicked") })

 Inside onClick:
 - You decide what happens
 - System does nothing automatically

 Same here:
 - Link click happens
 - Your lambda runs
 - You must decide what to do

 ONE SENTENCE SUMMARY:
 When user taps the link, Compose calls your lambda and gives you the clicked
 link as 'it'; you cast it to LinkAnnotation.Url, extract its URL, and manually
 open it using the system handler.
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 LINE: withLink(link) { append("jetpack compose") }
 ═══════════════════════════════════════════════════════════════════════════════

 WHAT IS withLink()?
 A function that attaches a LinkAnnotation to a specific piece of text

 Syntax:
 withLink(link) {
     append("text that becomes clickable")
 }

 What it does:
 1. You provide a link object (containing URL, style, and click handler)
 2. You provide text in the lambda
 3. withLink attaches that link object ONLY to that text

 Result:
 - Only "jetpack compose" becomes blue and clickable
 - The rest of the text ("Build better apps faster with ") is normal

 Think of it like HTML span:
 <span>Normal text <a href="...">clickable part</a></span>

 Alternative approach (inline - without storing in variable):

 withLink(
     LinkAnnotation.Url(
         "https://developer.android.com/",
         TextLinkStyles(style = SpanStyle(color = Color.Blue))
     )
 ) {
     append("jetpack compose")
 }

 Both work the same way. Storing in variable is cleaner when you need to:
 - Reuse the same link multiple times
 - Keep code organized
 - Add complex click logic

 How Compose tracks which text is linked:
 Internally, AnnotatedString stores:
 - The full text: "Build better apps faster with jetpack compose"
 - Annotations with ranges: [LinkAnnotation from index 31 to 46]

 When user clicks at position 35:
 - Compose checks: "Is there an annotation at position 35?"
 - Finds: LinkAnnotation.Url
 - Executes: The lambda stored in that annotation
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 COMPLETE EXECUTION FLOW - START TO FINISH
 ═══════════════════════════════════════════════════════════════════════════════

 COMPOSITION PHASE (When composable first runs):

 1. AnnotatedStringWithListenerSample() is called
 2. val uriHandler = LocalUriHandler.current
    → Gets the system URI handler from Compose environment

 3. buildAnnotatedString { ... } starts executing
    → This is just a Kotlin lambda, not UI code yet

 4. append("Build better apps faster with ")
    → Adds plain text to the builder

 5. val link = LinkAnnotation.Url(...) { ... }
    → Creates an object containing:
      • URL string
      • Blue color styling
      • Click handler lambda (which captures uriHandler via closure)

 6. withLink(link) { append("jetpack compose") }
    → Attaches the link annotation to the text "jetpack compose"
    → Internally marks: characters 31-46 are associated with this link object

 7. buildAnnotatedString returns final AnnotatedString
    → Contains: full text + metadata about where links are

 8. Text() receives the AnnotatedString and displays it
    → Renders "Build better apps faster with jetpack compose"
    → "jetpack compose" appears in blue


 USER INTERACTION PHASE (When user taps the link):

 1. User taps on "jetpack compose"

 2. Compose's gesture detection system captures the tap coordinates

 3. Compose checks: "What's at this position?"
    → Finds: LinkAnnotation.Url object attached to this text range

 4. Compose checks: "Does this annotation have a custom click handler?"
    → Yes, it has a lambda

 5. Compose calls the lambda and passes the annotation as 'it'
    → Lambda receives: it = the LinkAnnotation.Url object

 6. Inside lambda: val url = (it as LinkAnnotation.Url).url
    → Casts generic LinkAnnotation to specific LinkAnnotation.Url
    → Extracts the URL string
    → url = "https://developer.android.com/jetpack/compose"

 7. Inside lambda: uriHandler.openUri(url)
    → Calls the system handler (captured earlier via closure)
    → System creates Intent(ACTION_VIEW, Uri.parse(url))
    → Browser opens with the URL


 KEY TAKEAWAY:
 The link object is created once during composition and stored in the AnnotatedString.
 The lambda inside it is called later when user clicks.
 This separation of creation vs execution is what makes it programmable.
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 COMMON USE CASES FOR CUSTOM CLICK HANDLERS
 ═══════════════════════════════════════════════════════════════════════════════

 1. ANALYTICS TRACKING

 val link = LinkAnnotation.Url(url, styles) {
     analytics.logEvent("link_clicked", mapOf("url" to url))
     uriHandler.openUri((it as LinkAnnotation.Url).url)
 }


 2. CONFIRMATION DIALOG

 val link = LinkAnnotation.Url(url, styles) {
     showDialog = true  // Show "Are you sure you want to leave?"
     pendingUrl = (it as LinkAnnotation.Url).url
 }


 3. IN-APP WEBVIEW INSTEAD OF BROWSER

 val link = LinkAnnotation.Url(url, styles) {
     val clickedUrl = (it as LinkAnnotation.Url).url
     navController.navigate("webview/$clickedUrl")
 }


 4. LOGIN CHECK

 val link = LinkAnnotation.Url(url, styles) {
     if (userState.isLoggedIn) {
         uriHandler.openUri((it as LinkAnnotation.Url).url)
     } else {
         showLoginDialog = true
     }
 }


 5. DEEP LINK HANDLING (Navigate inside app)

 val link = LinkAnnotation.Url("myapp://profile/123", styles) {
     val deepLink = (it as LinkAnnotation.Url).url
     navController.navigate(deepLink.removePrefix("myapp://"))
 }


 6. DYNAMIC URL MODIFICATION

 val link = LinkAnnotation.Url(baseUrl, styles) {
     val url = (it as LinkAnnotation.Url).url
     val fullUrl = "$url?userId=${user.id}&session=${session.token}"
     uriHandler.openUri(fullUrl)
 }
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 FINAL SUMMARY - ONE PAGE REFERENCE
 ═══════════════════════════════════════════════════════════════════════════════

 WHAT THIS CODE DOES:
 Creates a clickable link inside text where YOU control what happens on click

 WHY USE THIS vs SIMPLE LINK:
 Simple link: User clicks → Browser opens (automatic)
 This approach: User clicks → Your code runs → You decide

 KEY COMPONENTS:

 1. LocalUriHandler.current
    → System tool to open URIs (web, email, phone, maps, etc.)

 2. buildAnnotatedString { }
    → Normal Kotlin lambda to build rich text (not UI code)

 3. LinkAnnotation.Url(url, styles) { lambda }
    → Object containing: data (URL, style) + behavior (click handler)

 4. val link = ...
    → Storing the link object (includes the lambda code)

 5. withLink(link) { append("text") }
    → Attaches link to specific text portion

 6. it as LinkAnnotation.Url
    → Casting to access URL property inside click handler

 7. uriHandler.openUri(url)
    → Manually opening the link (you control when/how/if)

 MENTAL MODEL:
 Think of the link object as a "smart button configuration":
 - It knows what it looks like (blue color)
 - It knows where it points (URL)
 - It knows what to do when clicked (lambda with your custom logic)

 You create this configuration once, attach it to text, and Compose executes
 the lambda later when user interacts with that text.
*/

@Composable
fun AnnotatedStringWithListenerSample(){
    /*
     Get the system URI handler
     This can open web links, phone numbers, emails, maps, etc.
    */
    val uriHandler = LocalUriHandler.current

    Text(buildAnnotatedString {
        // Regular non-clickable text
        append("Build better apps faster with ")

        /*
         Create a programmable link object
         This object contains:
         1. URL data
         2. Styling information
         3. Custom click behavior (the lambda)
        */
        val link = LinkAnnotation.Url(
            "https://developer.android.com/jetpack/compose",
            TextLinkStyles(SpanStyle(color = Color.Blue))
        ) {
            /*
             This lambda runs when user clicks the link

             'it' = the clicked LinkAnnotation
             We cast it to LinkAnnotation.Url to access the .url property
             Then we manually open it (plus we can add analytics, dialogs, etc)
            */
            val url = (it as LinkAnnotation.Url).url
            // You can add custom logic here:
            // - Log analytics
            // - Show confirmation
            // - Check user login status
            // - etc.
            uriHandler.openUri(url)
        }

        /*
         Apply the link to this specific text
         Only "jetpack compose" becomes clickable and runs the custom handler
        */
        withLink(link) {
            append("jetpack compose")
        }
    })
}