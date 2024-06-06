package com.kaelesty.study_android_contacts.presentation.contacts

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.mvidecomposetest.domain.AddContactUseCase
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase

class ContactStoreFactory(
	private val storeFactory: StoreFactory,
	private val addContactUseCase: AddContactUseCase,
	private val editContactUseCase: EditContactUseCase,
) {

	fun create(
		contact: Contact
	): ContactStore = object :
		ContactStore, Store<ContactStore.Intent, ContactStore.State, ContactStore.Label>
	by storeFactory.create(
		"ContactStore",
		initialState = ContactStore.State(contact.id, contact.phone, contact.username),
		reducer = ReducerImpl,
		executorFactory = ::ExecutorImpl
	) {}

	private sealed interface Action

	private sealed interface Msg {

		class ChangeUsername(val username: String): Msg

		class ChangePhone(val phone: String): Msg
	}

	private inner class ExecutorImpl:
		CoroutineExecutor<ContactStore.Intent, Action, ContactStore.State, Msg, ContactStore.Label>() {
		override fun executeIntent(intent: ContactStore.Intent) {
			when (intent) {
				is ContactStore.Intent.ChangePhone -> {
					dispatch(Msg.ChangePhone(intent.phone))
				}
				is ContactStore.Intent.ChangeUsername -> {
					dispatch(Msg.ChangeUsername(intent.username))
				}
				ContactStore.Intent.SaveContact -> {
					with(state()) {
						editContactUseCase(
							Contact(
								id, username, phone
							)
						)
					}
					publish(ContactStore.Label.ContactSaved)
				}

				ContactStore.Intent.AddContact -> {
					addContactUseCase(
						state().username, state().phone
					)
					publish(ContactStore.Label.ContactSaved)
				}
			}
		}
	}

	private object ReducerImpl: Reducer<ContactStore.State, Msg> {
		override fun ContactStore.State.reduce(msg: Msg): ContactStore.State {
			return when (msg) {
				is Msg.ChangePhone -> {
					copy(phone = msg.phone)
				}
				is Msg.ChangeUsername -> {
					copy(username = msg.username)
				}
			}
		}
	}
}