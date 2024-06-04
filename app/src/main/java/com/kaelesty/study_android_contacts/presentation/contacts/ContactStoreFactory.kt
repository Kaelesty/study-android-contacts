package com.kaelesty.study_android_contacts.presentation.contacts

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class ContactStoreFactory(
	private val storeFactory: StoreFactory
) {
	private val storeStore: Store<ContactStore.Intent, ContactStore.State, ContactStore.Label> =
		storeFactory.create(
			"ContactStore",
			initialState = ContactStore.State("", ""),

		)

	private sealed interface Action

	private sealed interface Msg {

		class ChangeUsername(val username: String): Msg

		class ChangePhone(val phone: String): Msg
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