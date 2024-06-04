package com.kaelesty.study_android_contacts.presentation.contacts.list

import com.arkivanov.mvikotlin.core.store.Store
import com.example.mvidecomposetest.domain.Contact
import kotlinx.serialization.Serializable

interface ContactListStore: Store<ContactListStore.Intent, ContactListStore.State, ContactListStore.Label> {

	@Serializable
	class State(
		val contacts: List<Contact>
	)

	sealed interface Intent {

		object AddContact: Intent

		class EditContact(val contact: Contact): Intent
	}

	sealed interface Label {

		object AddContact: Label

		class EditContact(val contact: Contact): Label
	}
}