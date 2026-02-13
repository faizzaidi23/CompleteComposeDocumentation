package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content_4

/*
Implementing pinch-to-zoom gestures to support scalable content in the app.
This is the standard platform-consistent method for improving accessibility,
allowing users to intuitively adjust the size of text and UI elements to fit their needs.

The app can define custom scaling behavior with granular control and contextual behavior
that offers an experience users often discover more quickly than system-level features
like screen magnification.

SCALING STRATEGIES:
The strategies covered cause the UI to reflow and reorganize to fit the screen's width.
This provides a significant accessibility benefit by eliminating the need for horizontal panning
and the frustrating "zig-zag" motion that would be required to read long lines of text.

TWO MAIN APPROACHES:
1. Density Scaling: Scales everything proportionally (text, components, images, spacing)
2. Font Scaling: Only affects text elements while layout and non-text components stay the same size

RECOMMENDATIONS BY UI TYPE:
- Reading-intensive layouts (news articles, messaging): Use density or font scaling
- Visually structured layouts (app stores, social feeds): Use density scaling to preserve
  visual relationships between images and text
*/