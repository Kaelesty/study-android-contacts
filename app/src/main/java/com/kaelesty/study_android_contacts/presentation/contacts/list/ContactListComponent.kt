package com.example.mvidecomposetest.presentation.contacts.list

import com.example.mvidecomposetest.domain.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

	data class Model(
		val contacts: List<Contact>
	)

	val model: StateFlow<Model>

	fun onAddContact()

	fun onEditContact(contact: Contact)
}