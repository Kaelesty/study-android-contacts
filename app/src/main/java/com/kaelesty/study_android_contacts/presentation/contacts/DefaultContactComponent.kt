package com.example.mvidecomposetest.presentation.contacts

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class DefaultContactComponent(
	private val componentContext: ComponentContext,
	initialName: String,
	initialPhone: String,
) : ContactComponent, ComponentContext by componentContext {

	init {
		stateKeeper.register(KEY, strategy = ContactComponent.Model.serializer()) {
			_model.value
		}
	}

	private val _model = MutableStateFlow(
		stateKeeper.consume(
			KEY,
			strategy = ContactComponent.Model.serializer()
		) ?: ContactComponent.Model(initialName, initialPhone)
	)
	override val model: StateFlow<ContactComponent.Model>
		get() = _model.asStateFlow()


	override fun onUsernameChanged(name: String) {
		_model.value = _model.value.copy(name = name)
	}

	override fun onPhoneChanged(phone: String) {
		_model.value = _model.value.copy(phone = phone)
	}

	companion object {
		private const val KEY = "DefaultContactComponent"
	}
}