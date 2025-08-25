package com.ziioz.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import com.ziioz.app.BuildConfig

class HealthViewModel : ViewModel() {
    private val http = OkHttpClient()

    var statusText by mutableStateOf<String?>(null)
        private set

    fun ping() {
        statusText = "Pinging…"
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = BuildConfig.API_BASE_URL + "health"
                val req = Request.Builder().url(url).build()
                http.newCall(req).execute().use { resp ->
                    val body = resp.body?.string().orEmpty()
                    val msg = "HTTP ${resp.code} • ${body.take(200)}"
                    withContext(Dispatchers.Main) { statusText = msg }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { statusText = "Error: ${e.message}" }
            }
        }
    }
}
