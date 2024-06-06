package com.example.mvidecomposetest.presentation.contacts

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.AddContactUseCase
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase
import com.example.mvidecomposetest.presentation.componentScope
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStore
import com.kaelesty.study_android_contacts.presentation.contacts.ContactStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class DefaultContactComponent(
	private val componentContext: ComponentContext,
	contact: Contact,
	private val onContactSaved: () -> Unit
) : ContactComponent, ComponentContext by componentContext {

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

	val store: ContactStore = instanceKeeper.getStore {
		ContactStoreFactory(
			DefaultStoreFactory(), //
			AddContactUseCase(RepositoryImpl), // params should be injected by di
			EditContactUseCase(RepositoryImpl), //
		).create(contact)
	}

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