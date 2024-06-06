package com.example.mvidecomposetest.presentation.contacts.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.GetContactsUseCase
import com.example.mvidecomposetest.presentation.componentScope
import com.kaelesty.study_android_contacts.presentation.contacts.list.ContactListStore
import com.kaelesty.study_android_contacts.presentation.contacts.list.ContactListStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultContactListComponent(
	private val componentContext: ComponentContext,
	private val onContactEditingRequested: (Contact) -> Unit,
	private val onContactAddingRequested: () -> Unit,
) : ContactListComponent, ComponentContext by componentContext {


	private val store = instanceKeeper.getStore {
		ContactListStoreFactory(
			DefaultStoreFactory(),
			GetContactsUseCase(RepositoryImpl),
		).create()
	}

	init {
		componentScope().launch {
			store.labels.collect {
				when (it) {
					is ContactListStore.Label.AddContact -> {
						onContactAddingRequested()
					}
					is ContactListStore.Label.EditContact -> {
						onContactEditingRequested(it.contact)
					}
				}
			}
		}
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	override val model: StateFlow<ContactListStore.State> = store.stateFlow

	override fun onAddContact() {
		store.accept(ContactListStore.Intent.AddContact)
	}

	override fun onEditContact(contact: Contact) {
		store.accept(ContactListStore.Intent.EditContact(contact))
	}
}