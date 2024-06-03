package com.example.mvidecomposetest.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.example.mvidecomposetest.presentation.contacts.ContactComponent
import com.example.mvidecomposetest.presentation.contacts.list.ContactListComponent
import com.example.mvidecomposetest.presentation.root.RootComponent
import com.example.mvidecomposetest.ui.theme.MviDecomposeTestTheme
import com.kaelesty.study_android_contacts.ui.content.Contacts

@Composable
fun RootContent(
	component: RootComponent
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

					is RootComponent.Child.AddContact -> AddContact(component = instance.component)
					is RootComponent.Child.ContactList -> Contacts(contactsListComponent = instance.component)
					is RootComponent.Child.EditContact -> AddContact(component = instance.component)
				}
			}
		}
	}
}