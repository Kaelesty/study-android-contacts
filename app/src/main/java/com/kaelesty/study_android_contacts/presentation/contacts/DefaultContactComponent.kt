package com.example.mvidecomposetest.presentation.contacts

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.mvidecomposetest.presentation.componentScope
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class DefaultContactComponent(
	private val componentContext: ComponentContext,
	initialName: String,
	initialPhone: String,
) : ContactComponent, ComponentContext by componentContext {

	lateinit var store: ContactStore

	@OptIn(ExperimentalCoroutinesApi::class)
	private val _model = store.stateFlow
	override val model: StateFlow<ContactStore.State>
		get() = _model


	override fun onUsernameChanged(name: String) {
		store.accept(ContactStore.Intent.ChangeUsername(name))
	}

	override fun onPhoneChanged(phone: String) {
		store.accept(ContactStore.Intent.ChangePhone(phone))
	}
}