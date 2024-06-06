package com.kaelesty.study_android_contacts.presentation.contacts.list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.GetContactsUseCase
import kotlinx.coroutines.launch

class ContactListStoreFactory(
	private val storeFactory: StoreFactory,
	private val getContactsUseCase: GetContactsUseCase,
) {


	fun create(): ContactListStore = object : ContactListStore by storeFactory.create(
		name = "ContactListStore",
		initialState = ContactListStore.State(listOf()),
		reducer = ReducerImpl,
		executorFactory = ::ExecutorImpl,
		bootstrapper = BootstrapperImpl()
	) as ContactListStore {}

	private sealed interface Action {
		class UpdateContactList(val contacts: List<Contact>): Action
	}

	private sealed interface Msg {
		class UpdateContactList(val contacts: List<Contact>): Msg
	}

	private inner class BootstrapperImpl: CoroutineBootstrapper<Action>() {
		override fun invoke() {
			scope.launch {
				getContactsUseCase().collect {
					dispatch(Action.UpdateContactList(it))
				}
			}
		}
	}

	private inner class ExecutorImpl:
		CoroutineExecutor<ContactListStore.Intent, Action, ContactListStore.State, Msg, ContactListStore.Label>() {
		override fun executeAction(action: Action) {
			when (action) {
				is Action.UpdateContactList -> {
					dispatch(Msg.UpdateContactList(action.contacts))
				}
			}
		}

		override fun executeIntent(intent: ContactListStore.Intent) {
			when (intent) {
				ContactListStore.Intent.AddContact -> publish(ContactListStore.Label.AddContact)
				is ContactListStore.Intent.EditContact -> publish(ContactListStore.Label.EditContact(intent.contact))
			}
		}
	}

	private object ReducerImpl: Reducer<ContactListStore.State, Msg> {
		override fun ContactListStore.State.reduce(msg: Msg): ContactListStore.State {
			return when (msg) {
				is Msg.UpdateContactList -> {
					copy(contacts = msg.contacts)
				}
			}
		}
	}
}