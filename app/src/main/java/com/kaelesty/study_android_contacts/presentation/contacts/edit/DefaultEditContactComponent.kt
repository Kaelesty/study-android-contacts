package com.example.mvidecomposetest.presentation.contacts.edit

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation.contacts.DefaultContactComponent
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore

class DefaultEditContactComponent(
	componentContext: ComponentContext,
	val contact: Contact,
	onContactSaved: () -> Unit,
): DefaultContactComponent(componentContext, contact, onContactSaved) {


	override fun onSaveContact() {
		super.store.accept(ContactStore.Intent.SaveContact)
	}
}