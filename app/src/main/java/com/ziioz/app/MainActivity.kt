// app/src/main/java/com/ziioz/app/MainActivity.kt
package com.ziioz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ziioz.app.ui.App   // ✅ add this import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }   // ✅ uses com.ziioz.app.ui.App
    }
}
