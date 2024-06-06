package com.kaelesty.study_android_contacts.presentation.contacts

import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.serialization.Serializable

interface ContactStore: Store<ContactStore.Intent, ContactStore.State, ContactStore.Label> {

	@Serializable
	data class State(
		val id: Int,
		val phone: String,
		val username: String,
	)

	sealed interface Label {

		object ContactSaved: Label
	}

	sealed interface Intent {

		class ChangeUsername(val username: String): Intent

		class ChangePhone(val phone: String): Intent

		object SaveContact: Intent

		object AddContact: Intent
	}
}