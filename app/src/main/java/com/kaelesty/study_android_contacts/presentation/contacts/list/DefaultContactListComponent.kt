package com.example.mvidecomposetest.presentation.contacts.list

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.GetContactsUseCase
import com.example.mvidecomposetest.presentation.componentScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
	private val componentContext: ComponentContext,
	private val onContactEditingRequested: (Contact) -> Unit,
	private val onContactAddingRequested: () -> Unit,
) : ContactListComponent, ComponentContext by componentContext {

	private val scope = componentScope()

	private val getContactsUseCase = GetContactsUseCase(RepositoryImpl)

	override val model: StateFlow<ContactListComponent.Model> = getContactsUseCase()
		.map {
			ContactListComponent.Model(it)
		}
		.stateIn(
			scope = scope,
			started = SharingStarted.Lazily,
			initialValue = ContactListComponent.Model(listOf())
		)

	override fun onAddContact() {
		onContactAddingRequested()
	}

	override fun onEditContact(contact: Contact) {
		onContactEditingRequested(contact)
	}
}