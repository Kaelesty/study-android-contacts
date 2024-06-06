package com.example.mvidecomposetest.presentation.contacts.add

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation.contacts.DefaultContactComponent
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore

class DefaultAddContactComponent(
	componentContext: ComponentContext,
	onContactSaved: () -> Unit,
): DefaultContactComponent(componentContext, Contact(username = "", phone = ""), onContactSaved) {

	override fun onSaveContact() {
		super.store.accept(ContactStore.Intent.AddContact)
	}
}