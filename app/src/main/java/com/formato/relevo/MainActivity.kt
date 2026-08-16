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
        pantalla.setPadding(24, 24, 24, 24)
        pantalla.setBackgroundColor(Color.WHITE)

        val titulo = TextView(this)
        titulo.text = "FORMATO DE RELEVO"
        titulo.textSize = 26f
        titulo.setTextColor(Color.BLACK)
        titulo.gravity = Gravity.CENTER
        titulo.setPadding(0, 20, 0, 30)

        pantalla.addView(titulo)

        val fecha = EditText(this)
        fecha.hint = "Fecha"
        fecha.inputType = 1
        pantalla.addView(fecha)

        val operario = EditText(this)
        operario.hint = "Nombre del operario"
        pantalla.addView(operario)

        val turno = EditText(this)
        turno.hint = "Turno"
        pantalla.addView(turno)

        val observaciones = EditText(this)
        observaciones.hint = "Observaciones"
        observaciones.minLines = 3
        pantalla.addView(observaciones)

        val boton = Button(this)
        boton.text = "GUARDAR RELEVO"

        boton.setOnClickListener {
            Toast.makeText(
                this,
                "Relevo guardado correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }

        pantalla.addView(boton)

        setContentView(pantalla)
    }
}
