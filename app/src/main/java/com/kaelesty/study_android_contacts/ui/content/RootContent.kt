package com.example.mvidecomposetest.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.example.mvidecomposetest.presentation.contacts.ContactComponent
import com.example.mvidecomposetest.presentation.contacts.list.ContactListComponent
import com.example.mvidecomposetest.presentation.root.DefaultRootComponent
import com.example.mvidecomposetest.ui.theme.MviDecomposeTestTheme

@Composable
fun RootContent(
	component: DefaultRootComponent
) {
	MviDecomposeTestTheme {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			Children(stack = component.stack) {
				when (val instance = it.instance) {
					is ContactListComponent -> {
						Contacts(contactsListComponent = instance)
					}
					is ContactComponent -> {
						AddContact(component = instance)
					}
				}
			}
		}
	}
}