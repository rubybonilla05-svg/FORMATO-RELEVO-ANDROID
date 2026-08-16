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
        pantalla.addView(fecha)

        val operario = EditText(this)
        operario.hint = "Nombre del operario"
        pantalla.addView(operario)

        val turno = EditText(this)
        turno.hint = "Turno: Día / Noche"
        pantalla.addView(turno)

        val tituloDispensadores = TextView(this)
        tituloDispensadores.text = "LECTURAS DE DISPENSADORES"
        tituloDispensadores.textSize = 20f
        tituloDispensadores.setTextColor(Color.BLACK)
        tituloDispensadores.setPadding(0, 30, 0, 15)
        pantalla.addView(tituloDispensadores)

        val dispensador1 = EditText(this)
        dispensador1.hint = "Dispensador 1"
        dispensador1.inputType = 2
        pantalla.addView(dispensador1)

        val dispensador2 = EditText(this)
        dispensador2.hint = "Dispensador 2"
        dispensador2.inputType = 2
        pantalla.addView(dispensador2)

        val lecturaInicial = EditText(this)
        lecturaInicial.hint = "Lectura inicial"
        lecturaInicial.inputType = 2
        pantalla.addView(lecturaInicial)

        val lecturaFinal = EditText(this)
        lecturaFinal.hint = "Lectura final"
        lecturaFinal.inputType = 2
        pantalla.addView(lecturaFinal)

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
