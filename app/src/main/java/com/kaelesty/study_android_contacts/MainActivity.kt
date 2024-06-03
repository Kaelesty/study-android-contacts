package com.kaelesty.study_android_contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.kaelesty.study_android_contacts.presentation.root.DefaultRootComponent
import com.example.mvidecomposetest.ui.content.RootContent

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val componentContext = defaultComponentContext()

		//enableEdgeToEdge()
		setContent {
			RootContent(component = DefaultRootComponent(componentContext))
		}
	}
}