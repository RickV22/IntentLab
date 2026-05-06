package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val settingsManager = SettingsManager(this)

        lifecycleScope.launch {
            settingsManager.userNameFlow.collect { name ->
                tvWelcome.text = getString(R.string.bienvenido_nombre, name)
            }
        }
        btnWeb.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, "https://www.unibarranquilla.edu.co/portales-usuarios".toUri())
            startActivity(webIntent)
        }
    }
}