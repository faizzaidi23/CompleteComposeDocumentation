package com.example.a1_composedocumentationuiarchitecture.StateHoisting_5

/*
Of course. "Property drilling" is the term for when you pass data down through several
layers of nested components to get it to a deeply nested child that actually needs it.

Think of it like a chain of command. The General (Parent Composable) has a specific message
 (the state) that needs to get to a Soldier (Deeply Nested Child Composable).
  The General can't talk to the Soldier directly, so they tell the Colonel, who tells the Captain,
   who finally tells the Soldier. The message was "drilled" down through the hierarchy.

## Why It's Often Used (and Recommended)
You might think creating a single "wrapper" class to hold all the data and events and
just passing that one object down would be easier. However, the documentation recommends property drilling for two main reasons:

Clarity and Visibility: When you pass each piece of data and each function call (onClick, onValueChange)
as a separate parameter, you can see exactly what a composable does just by looking at its function signature.
It's like a clear job description. A wrapper class hides these details, making it harder to understand the composable's responsibilities at a glance.

Passing Only What's Needed: Property drilling encourages you to only pass the specific data a child needs.
If a button only needs an onClick function, you pass just that. If you passed a giant wrapper object,
that button would have access to all sorts of data and functions it doesn't need, which can lead to bugs and is considered bad practice.

In short, even though it can feel a bit repetitive to pass parameters down through multiple layers,
it makes your code clearer, more honest, and less error-prone. It's preferred because
it forces you to be explicit about what each component needs to do its job.
*/