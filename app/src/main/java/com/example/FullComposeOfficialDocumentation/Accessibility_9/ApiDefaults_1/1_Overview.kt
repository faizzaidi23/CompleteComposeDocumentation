package com.example.FullComposeOfficialDocumentation.Accessibility_9.ApiDefaults_1

/*
Material, Compose UI, and Foundation APIs implement and offer many accessible practices by default.
They contain built-in semantics that follow their specific role and function.
This means most accessibility support is provided with little or no additional work.

Using the appropriate APIs for the appropriate purpose means that components usually come with predefined
accessibility behaviors that cover standard use cases. However, always double-check whether these defaults
fit your accessibility needs. If not, Compose provides ways to cover more specific requirements.

Understanding the default accessibility semantics and patterns in Compose APIs helps you use them with
accessibility in mind. It also helps you support accessibility in more custom components.
*/

/*
Key concepts in API Defaults:

1. Minimum Touch Target Sizes --> Any on-screen element that someone can click, touch, or interact with
   must be large enough for reliable interaction (minimum 48dp).

2. Graphic Elements --> When defining Image or Icon composables, you need to pass a textual description
   for visually impaired users.

3. Interactive Elements --> Material and Foundation Compose APIs make UI elements interactive through
   clickable and toggleable modifiers with built-in accessibility support.

4. Custom Components --> When building custom components, review similar components in Material library
   and mimic their accessibility behavior.
*/

