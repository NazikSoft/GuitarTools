package com.naziksoft.guitartools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected fun runCoroutine(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context) { block() }
}