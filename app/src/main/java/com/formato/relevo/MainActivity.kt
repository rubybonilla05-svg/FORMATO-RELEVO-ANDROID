package com.formato.relevo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val texto = TextView(this)
        texto.text = "FORMATO DE RELEVO"
        texto.textSize = 28f
        texto.setPadding(32, 32, 32, 32)

        setContentView(texto)
    }
}
