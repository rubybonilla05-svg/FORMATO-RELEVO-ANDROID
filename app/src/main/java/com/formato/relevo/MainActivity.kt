package com.formato.relevo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pantalla = LinearLayout(this)
        pantalla.orientation = LinearLayout.VERTICAL
        pantalla.setPadding(22, 22, 22, 22)

        val scroll = ScrollView(this)
        scroll.addView(pantalla)

        fun titulo(texto: String, tamano: Float): TextView {
            val t = TextView(this)
            t.text = texto
            t.textSize = tamano
            t.setTextColor(Color.BLACK)
            t.gravity = Gravity.CENTER
            t.setPadding(0, 10, 0, 10)
            return t
        }

        fun campo(texto: String): EditText {
            val e = EditText(this)
            e.hint = texto
            e.textSize = 18f
            e.setPadding(0, 5, 0, 5)
            return e
        }

        pantalla.addView(titulo("FORMATO DE RELEVO", 28f))

        pantalla.addView(campo("Fecha"))
        pantalla.addView(campo("Nombre del operario"))
        pantalla.addView(campo("Turno: Día / Noche"))

        pantalla.addView(titulo("LECTURAS DE DISPENSADORES", 24f))

        pantalla.addView(titulo("DISPENSADOR 1", 21f))
        pantalla.addView(campo("Lectura 1"))
        pantalla.addView(campo("Lectura 2"))
        pantalla.addView(campo("Lectura 3"))
        pantalla.addView(campo("Lectura 4"))

        pantalla.addView(titulo("DISPENSADOR 2", 21f))
        pantalla.addView(campo("Lectura 1"))
        pantalla.addView(campo("Lectura 2"))
        pantalla.addView(campo("Lectura 3"))
        pantalla.addView(campo("Lectura 4"))

        pantalla.addView(campo("Lectura inicial"))
        pantalla.addView(campo("Lectura final"))

        pantalla.addView(campo("Observaciones"))

        val guardar = Button(this)
        guardar.text = "GUARDAR RELEVO"
        guardar.textSize = 18f
        guardar.setOnClickListener {
            Toast.makeText(
                this,
                "Relevo guardado correctamente",
                Toast.LENGTH_LONG
            ).show()
        }

        pantalla.addView(guardar)

        setContentView(scroll)
    }
}
