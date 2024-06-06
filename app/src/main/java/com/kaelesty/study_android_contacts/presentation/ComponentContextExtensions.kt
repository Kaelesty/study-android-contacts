package com.example.mvidecomposetest.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

fun ComponentContext.componentScope() = CoroutineScope(Dispatchers.Main).apply {
	lifecycle.doOnDestroy { cancel() }
}