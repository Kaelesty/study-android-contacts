package com.example.mvidecomposetest.presentation.contacts.list

import com.example.mvidecomposetest.domain.Contact
import com.kaelesty.study_android_contacts.presentation.contacts.list.ContactListStore
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

	val model: StateFlow<ContactListStore.State>

	fun onAddContact()

	fun onEditContact(contact: Contact)
}