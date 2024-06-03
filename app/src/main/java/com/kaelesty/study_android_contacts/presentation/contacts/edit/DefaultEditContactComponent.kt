package com.example.mvidecomposetest.presentation.contacts.edit

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase
import com.example.mvidecomposetest.presentation.contacts.DefaultContactComponent

class DefaultEditContactComponent(
	componentContext: ComponentContext,
	val contact: Contact,
	private val onContactSaved: () -> Unit
): DefaultContactComponent(componentContext, contact.username, contact.phone) {

	private val editContactUseCase = EditContactUseCase(RepositoryImpl)

	override fun onSaveContact() {
		editContactUseCase(
			Contact(
				id = contact.id,
				super.model.value.name, super.model.value.phone
			)
		)
		onContactSaved()
	}
}