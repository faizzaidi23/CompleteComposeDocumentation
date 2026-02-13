package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

/*
BREAKDOWN OF USE CASES

Summary of use cases to understand how to clearly differentiate between the APIs
for merging, clearing, and hiding semantics.

═══════════════════════════════════════════════════════════════════════════════
1. WHEN CONTENT IS NOT INTENDED TO BE USED BY ACCESSIBILITY SERVICES
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ USE hideFromAccessibility                                                   │
├─────────────────────────────────────────────────────────────────────────────┤
│ WHEN: Content is possibly decorative or redundant, but still must be tested│
│                                                                             │
│ EXAMPLES:                                                                   │
│ - Watermarks on images                                                      │
│ - Decorative separators (•, |, /)                                          │
│ - Background patterns                                                       │
│ - Redundant icons next to descriptive text                                  │
│                                                                             │
│ CODE:                                                                       │
│   Modifier.semantics { hideFromAccessibility() }                           │
│                                                                             │
│ RESULT:                                                                     │
│ - Hidden from accessibility services ✓                                      │
│ - Still available for testing ✓                                             │
│ - Rendered visually ✓                                                       │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ USE clearAndSetSemantics{} with EMPTY LAMBDA                               │
├─────────────────────────────────────────────────────────────────────────────┤
│ WHEN: Parent and children semantics need to be cleared for ALL services    │
│                                                                             │
│ EXAMPLES:                                                                   │
│ - Completely non-interactive decorative containers                          │
│ - Elements that should be invisible to all semantic consumers               │
│                                                                             │
│ CODE:                                                                       │
│   Modifier.clearAndSetSemantics {}                                         │
│                                                                             │
│ RESULT:                                                                     │
│ - Hidden from accessibility services ✓                                      │
│ - Cleared from testing ✓                                                    │
│ - Rendered visually ✓                                                       │
│                                                                             │
│ WARNING: Use sparingly - affects testing too!                               │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ USE clearAndSetSemantics{/*content*/} with CONTENT                         │
├─────────────────────────────────────────────────────────────────────────────┤
│ WHEN: A component's semantics needs to be manually set                      │
│                                                                             │
│ EXAMPLES:                                                                   │
│ - Custom toggle components                                                  │
│ - Custom interactive elements                                               │
│ - Components requiring specific semantic properties                         │
│ - When default semantics don't accurately represent the component           │
│                                                                             │
│ CODE:                                                                       │
│   Modifier.clearAndSetSemantics {                                          │
│       stateDescription = "Custom state"                                    │
│       role = Role.Switch                                                   │
│   }                                                                         │
│                                                                             │
│ RESULT:                                                                     │
│ - Original semantics replaced ✓                                             │
│ - Custom semantics set ✓                                                    │
│ - Better accessibility representation ✓                                     │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
2. WHEN CONTENT SHOULD BE TREATED AS ONE ENTITY
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ USE MERGE SEMANTIC DESCENDANTS                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ WHEN: Content needs all of its children's information to be complete        │
│                                                                             │
│ EXAMPLES:                                                                   │
│ - User profile cards (avatar + name + info)                                 │
│ - List items with multiple text elements                                    │
│ - Article metadata (author + date + read time)                              │
│ - Cards with icon + title + description                                     │
│                                                                             │
│ CODE:                                                                       │
│   Modifier.semantics(mergeDescendants = true) {}                           │
│                                                                             │
│ RESULT:                                                                     │
│ - Children merged into parent ✓                                             │
│ - Single focusable element ✓                                                │
│ - All information announced together ✓                                      │
│ - Efficient navigation ✓                                                    │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
3. DECISION TREE
═══════════════════════════════════════════════════════════════════════════════

START: Does the element need to be accessible?
│
├─ NO → Is it needed for testing?
│   │
│   ├─ YES → Use hideFromAccessibility()
│   │          (Hides from a11y, keeps for testing)
│   │
│   └─ NO → Use clearAndSetSemantics {}
│              (Clears from everything)
│
└─ YES → Does it have multiple child elements?
    │
    ├─ YES → Should children be grouped?
    │   │
    │   ├─ YES → Use semantics(mergeDescendants = true)
    │   │          (Group children into one entity)
    │   │
    │   └─ NO → Keep separate
    │              (Each child is independently accessible)
    │
    └─ NO → Are default semantics correct?
        │
        ├─ YES → Do nothing
        │          (Use default semantics)
        │
        └─ NO → Use clearAndSetSemantics { /* custom */ }
                   (Replace with custom semantics)

═══════════════════════════════════════════════════════════════════════════════
4. QUICK REFERENCE COMPARISON TABLE
═══════════════════════════════════════════════════════════════════════════════

┌──────────────────────┬──────────────┬─────────┬──────────┬─────────────────┐
│ API                  │ Accessibility│ Testing │ Visual   │ Best For        │
├──────────────────────┼──────────────┼─────────┼──────────┼─────────────────┤
│ hideFromAccessibility│ Hidden       │ Visible │ Visible  │ Decorative      │
│                      │              │         │          │ elements        │
├──────────────────────┼──────────────┼─────────┼──────────┼─────────────────┤
│ clearAndSetSemantics │ Cleared      │ Cleared │ Visible  │ Complete        │
│ (empty)              │              │         │          │ removal         │
├──────────────────────┼──────────────┼─────────┼──────────┼─────────────────┤
│ clearAndSetSemantics │ Custom       │ Custom  │ Visible  │ Custom          │
│ (with content)       │              │         │          │ components      │
├──────────────────────┼──────────────┼─────────┼──────────┼─────────────────┤
│ semantics            │ Merged       │ Merged  │ Visible  │ Grouping        │
│ (mergeDescendants)   │              │         │          │ related content │
└──────────────────────┴──────────────┴─────────┴──────────┴─────────────────┘

═══════════════════════════════════════════════════════════════════════════════
5. COMMON PATTERNS AND RECIPES
═══════════════════════════════════════════════════════════════════════════════

PATTERN 1: User Profile Card
┌─────────────────────────────────────────────────────────────────────────────┐
│ Row(Modifier.semantics(mergeDescendants = true)) {                         │
│     Image(contentDescription = null) // Avatar                             │
│     Column {                                                                │
│         Text("John Doe")                                                    │
│         Text("Software Engineer")                                           │
│     }                                                                       │
│ }                                                                           │
│ Result: "John Doe, Software Engineer" (announced together)                 │
└─────────────────────────────────────────────────────────────────────────────┘

PATTERN 2: Decorative Watermark
┌─────────────────────────────────────────────────────────────────────────────┐
│ Box {                                                                       │
│     MainContent()                                                           │
│     Watermark(Modifier.semantics { hideFromAccessibility() })              │
│ }                                                                           │
│ Result: Only MainContent is announced, watermark is hidden                 │
└─────────────────────────────────────────────────────────────────────────────┘

PATTERN 3: Custom Toggle
┌─────────────────────────────────────────────────────────────────────────────┐
│ Row(                                                                        │
│     Modifier                                                                │
│         .toggleable(value, onValueChange)                                  │
│         .clearAndSetSemantics {                                            │
│             stateDescription = if (value) "On" else "Off"                  │
│             role = Role.Switch                                             │
│         }                                                                   │
│ ) {                                                                         │
│     Icon(...)                                                               │
│     Text("Toggle me")                                                       │
│ }                                                                           │
│ Result: "On, Switch, Double tap to toggle"                                 │
└─────────────────────────────────────────────────────────────────────────────┘

PATTERN 4: List with Nested Actions
┌─────────────────────────────────────────────────────────────────────────────┐
│ Row(Modifier.clickable { openItem() }) {                                   │
│     Image(...)                                                              │
│     Text("Item title")                                                      │
│     IconButton(onClick = { bookmark() }) {  // Separate action             │
│         Icon(Icons.Default.Bookmark, "Bookmark")                           │
│     }                                                                       │
│ }                                                                           │
│ Result: Two separate focusable elements (row + button)                     │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
KEY PRINCIPLES
═══════════════════════════════════════════════════════════════════════════════

1. Start with defaults - Most components have good semantics by default
2. Test with TalkBack - Always verify with real accessibility services
3. Less is more - Don't over-merge or over-hide
4. Think like a user - Would this grouping make sense when navigating?
5. Document custom semantics - Explain why you're overriding defaults
6. Consider testing - Some APIs affect test discoverability
7. Respect merging rules - Nested clickables don't merge automatically
*/

