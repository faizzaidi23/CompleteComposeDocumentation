package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

/*
The key idea is that

Modifiers are not just a list of properties applied all at once.
They are applied in a strict sequence,one after the other. Each modifier in a chain wraps the next one


The Order of Operations is Crucial
The order you write your modifiers in the chain defines
 the order they are applied. The first modifier is the outermost one,
  and it can change the size and constraints
  for all the modifiers that come after it.

  Modifier
    .size(100.dp)   // 1. First, the size is constrained to exactly 100x100 dp.
    .padding(10.dp) // 2. Second, 10dp of padding is applied *inside* that 100x100 space.

    Result: The component occupies a total of 100x100 dp.
     The actual content area inside is smaller
     (80x80 dp) because the padding pushed it inward.



     Example 2: padding then size



     Modifier
    .padding(10.dp) // 1. First, a 10dp space is added around the component.
    .size(100.dp)   // 2. Second, the component itself is made 100x100 dp.

    Result: The component occupies a total of 120x120 dp (100dp for the size + 10dp of padding on all four sides).
*/

