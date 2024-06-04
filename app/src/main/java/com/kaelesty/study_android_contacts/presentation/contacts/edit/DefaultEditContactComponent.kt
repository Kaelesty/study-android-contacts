package com.example.mvidecomposetest.presentation.contacts.edit

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase
import com.example.mvidecomposetest.presentation.componentScope
import com.example.mvidecomposetest.presentation.contacts.DefaultContactComponent
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore
import kotlinx.coroutines.launch

class DefaultEditContactComponent(
	componentContext: ComponentContext,
	val contact: Contact,
	private val onContactSaved: () -> Unit
): DefaultContactComponent(componentContext, contact.username, contact.phone) {

	init {
		componentScope().launch {
			store.labels.collect {
				when (it) {
					is ContactStore.Label.ContactSaved -> {
						onContactSaved()
					}
				}
			}
		}
	}

	override fun onSaveContact() {
		super.store.accept(ContactStore.Intent.SaveContact)
		onContactSaved()
	}
}