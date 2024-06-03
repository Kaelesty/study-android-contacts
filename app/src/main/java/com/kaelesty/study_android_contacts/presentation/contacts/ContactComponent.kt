package com.example.mvidecomposetest.presentation.contacts

import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface ContactComponent {

	@Serializable
	data class Model(
		val name: String,
		val phone: String,
	)

	val model: StateFlow<Model>

	fun onSaveContact()

	fun onUsernameChanged(name: String)

	fun onPhoneChanged(phone: String)
}