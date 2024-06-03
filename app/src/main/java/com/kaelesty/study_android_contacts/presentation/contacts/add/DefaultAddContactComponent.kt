package com.example.mvidecomposetest.presentation.contacts.add

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.AddContactUseCase
import com.example.mvidecomposetest.presentation.contacts.DefaultContactComponent

class DefaultAddContactComponent(
	componentContext: ComponentContext,
	private val onContactSaved: () -> Unit
): DefaultContactComponent(componentContext,"", "") {

	private val addContactUseCase = AddContactUseCase(RepositoryImpl)

	override fun onSaveContact() {
		addContactUseCase(super.model.value.name, super.model.value.phone)
		onContactSaved()
	}
}