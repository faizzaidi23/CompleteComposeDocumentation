package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

/*
MERGING AND CLEARING SEMANTICS

As accessibility services navigate through elements on the screen, it is important that
these elements are grouped, separated, or even hidden at the right granularity.

THE PROBLEM:
- When every single low-level composable in your screen is highlighted independently,
  users have to interact a lot to move across the screen
- If elements merge together too aggressively, users might not understand which
  elements logically belong together
- If there are elements on the screen that are purely decorative, these could be
  hidden from accessibility services

THE SOLUTION:
Compose provides APIs for merging, clearing, and hiding semantics:

1. MERGE SEMANTICS (semantics(mergeDescendants = true))
   - Groups multiple composables into a logical unit
   - Accessibility services treat them as one entity
   - Use when children elements constitute a logical and sensible group

2. CLEAR AND SET SEMANTICS (clearAndSetSemantics)
   - Completely clears or overwrites semantic information
   - Use when creating custom components with specific accessibility needs
   - Can clear all semantics or replace them with new ones

3. HIDE SEMANTICS (hideFromAccessibility)
   - Hides elements from accessibility services
   - Use for decorative or redundant elements
   - Still keeps semantics for other use cases like testing

PROPER GRANULARITY IS KEY:
The right balance ensures users can efficiently navigate your app while understanding
the logical structure of the UI elements.
*/