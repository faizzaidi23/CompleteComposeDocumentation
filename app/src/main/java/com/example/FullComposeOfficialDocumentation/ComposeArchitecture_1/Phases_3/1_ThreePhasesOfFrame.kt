package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Phases_3

/*
There are three phases

1->Composition----what to show works top to bottom

2->Layout---where to place it

3->Drawing---Drawing-->this is painting the pixels
*/



/*
Layout Phase


During the layout phase, the tree is traversed using the following three step algorithm:

Measure children: A node measures its children if any exist.
Decide own size: Based on these measurements, a node decides on its own size.
Place children: Each child node is placed relative to a node's own position.
*/