package com.example

import io.ktor.client.engine.ios.Ios
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun getIp(callback: (IpResponse) -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        val ipResponse = ApiClient(Ios.create()).getIp()
        callback.invoke(ipResponse)
    }
}
