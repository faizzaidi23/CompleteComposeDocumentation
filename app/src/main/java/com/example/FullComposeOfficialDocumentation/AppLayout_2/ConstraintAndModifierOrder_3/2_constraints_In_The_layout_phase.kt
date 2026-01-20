package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

/*
Constraints-->

constraints are  a set of rules a parent layout gives to a child layout.
These rules tell the child the minimum and maximum width and height it is allowed to be

A child component must choose a final size that fits within the rules
given by its parent. A child cannot be smaller than the minimums or larger
 than the maximums.



 The Layout Process and Constraints
The three-step layout process you mentioned relies on these rules:

Measure Children: The parent first gives each child its set of constraints (the rules). The child then measures itself to determine its desired size, making sure that size obeys the rules. It reports this desired size back to the parent.

Decide Own Size: After hearing back from all its children, the parent decides on its own final size.

Place Children: The parent positions its children within its own boundaries.
*/


/*
Types of rules

Bounded:

 The child has both a minimum and a maximum size.
For example: "You must be at least 50 pixels wide, but no more than 200 pixels wide."

Unbounded:

 The child can be as large as it wants. The maximum size is essentially infinite. For example:
 "Be as tall as you need to be to fit all your content."
 This is common in a scrollable column.

Exact:

The child is given one specific size it must be. The minimum and maximum sizes are the same.
For example: "You must be exactly 100 pixels wide and 100 pixels tall."
 The Modifier.size(100.dp) creates this type of constraint.

Combination: The rules for width and height can be different. For example, a parent could give a child an exact width but a bounded height:
"You must be exactly 200 pixels wide, and your height can be anywhere
 from 50 to 300 pixels."

*/