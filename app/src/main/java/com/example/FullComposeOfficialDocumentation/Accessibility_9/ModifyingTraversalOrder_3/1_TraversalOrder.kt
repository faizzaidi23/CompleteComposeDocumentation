package com.example.FullComposeOfficialDocumentation.Accessibility_9.ModifyingTraversalOrder_3

/*
Modify Traversal Order - Overview:

Traversal order is the order in which accessibility services navigate through UI elements.
In a Compose app, elements are arranged in expected reading order, which is usually left-to-right,
then top-to-bottom.

However, there are some scenarios where Compose might need additional hints to determine the
correct reading order.

isTraversalGroup and traversalIndex are semantic properties that let you influence the traversal
order for accessibility services in scenarios where Compose's default sorting algorithm is not
sufficient.

- isTraversalGroup: Identifies semantically important groups that need customization
- traversalIndex: Adjusts the order of individual elements within those groups

You can use isTraversalGroup alone to signify that all elements within a group should be selected
together, or with traversalIndex for further customization.
*/

/*
Key Concepts:

1. Default Traversal Order:
   - Left-to-right
   - Top-to-bottom
   - Works for most standard layouts

2. When to Customize:
   - Non-standard layouts (circular, diagonal)
   - Multi-column layouts where columns should be read separately
   - Custom UI patterns that don't follow standard reading order

3. Tools Available:
   - isTraversalGroup: Group elements together for traversal
   - traversalIndex: Control the order within groups
*/