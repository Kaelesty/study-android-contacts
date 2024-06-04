package com.example.mvidecomposetest.presentation.contacts

import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface ContactComponent {

	val model: StateFlow<ContactStore.State>

	fun onSaveContact()

	fun onUsernameChanged(name: String)

	fun onPhoneChanged(phone: String)
}