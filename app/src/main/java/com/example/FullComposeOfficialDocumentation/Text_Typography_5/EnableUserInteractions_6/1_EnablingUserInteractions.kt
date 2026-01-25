package com.example.FullComposeOfficialDocumentation.Text_Typography_5.EnableUserInteractions_6

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/*
text selection is now flesible and can be done across composable layouts.
user ineractions in text are different from toher composable layouts as we can not add
a modifier to a portion of text composable
*/


/*
Selecting a text-->

By default composable are not selectable which means that uses can not select and copy
text from the app.
To enable text selection we have to wrap the text elements with a SelectionContainer composable
*/

@Composable
fun SelectableText(){
    SelectionContainer {
        Text("This text is selectable")
    }
}


//To disable a specific part


@Composable
fun PartiallySelectableText(){
    SelectionContainer {
        Column{
            Text("This text is selectable")
            Text("This one too")
            Text("This one as well we can select this text and do the changes here")

            DisableSelection {
                Text("This text can not be selected")
                Text("Neither this one")
            }

            Text("But again this text can be selected")
        }
    }
}