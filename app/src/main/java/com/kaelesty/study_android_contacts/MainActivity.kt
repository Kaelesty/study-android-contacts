package com.kaelesty.study_android_contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.example.mvidecomposetest.presentation.root.DefaultRootComponent
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