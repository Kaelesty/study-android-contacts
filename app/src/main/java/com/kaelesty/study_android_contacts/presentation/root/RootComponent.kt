package com.example.mvidecomposetest.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.mvidecomposetest.presentation.contacts.ContactComponent
import com.example.mvidecomposetest.presentation.contacts.list.ContactListComponent

interface RootComponent {
	val stack: Value<ChildStack<*, Child>>

	sealed interface Child {

		class ContactList(val component: ContactListComponent): Child

		class AddContact(val component: ContactComponent): Child

		class EditContact(val component: ContactComponent): Child
	}
}