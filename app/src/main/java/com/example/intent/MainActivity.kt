package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingsManager = SettingsManager(this)
        val etName = findViewById<EditText>(R.id.etName)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    settingsManager.saveName(name, this@MainActivity)

                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    startActivity(intent)
                }
            } else {
                etName.error = "Por favor escribe un nombre"
            }
        }
    }
}