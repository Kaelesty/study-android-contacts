package com.example.mvidecomposetest.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation.contacts.add.DefaultAddContactComponent
import com.example.mvidecomposetest.presentation.contacts.edit.DefaultEditContactComponent
import com.example.mvidecomposetest.presentation.contacts.list.DefaultContactListComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
	private val componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

	private val navigation = StackNavigation<Config>()

	val stack: Value<ChildStack<Config, ComponentContext>> = childStack(
		source = navigation,
		initialConfiguration = Config.ContactList,
		handleBackButton = true,
		childFactory = ::child,
		serializer = Config.serializer()
	)

	private fun child(
		config: Config,
		componentContext: ComponentContext
	): ComponentContext {
		return when (config) {
			is Config.AddContact -> {
				DefaultAddContactComponent(
					componentContext,
					onContactSaved = {
						navigation.pop()
					}
				)
			}

			is Config.EditContact -> {
				DefaultEditContactComponent(
					componentContext,
					config.contact,
					onContactSaved = {
						navigation.pop()
					}
				)
			}

			is Config.ContactList -> {
				DefaultContactListComponent(
					componentContext,
					onContactAddingRequested = {
						navigation.push(Config.AddContact)
					},
					onContactEditingRequested = {
						navigation.push(Config.EditContact(it))
					}
				)
			}
		}
	}

	@Serializable
	sealed interface Config {

		@Serializable
		object ContactList : Config

		@Serializable
		object AddContact : Config

		@Serializable
		data class EditContact(val contact: Contact) : Config
	}
}