package com.formato.relevo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.text.InputType
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
            e.inputType = InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_FLAG_DECIMAL
            return e
        }

        fun numero(campo: EditText): Double {
            val texto = campo.text.toString()
                .replace(".", "")
                .replace(",", ".")

            return texto.toDoubleOrNull() ?: 0.0
        }

        pantalla.addView(titulo("FORMATO DE RELEVO", 28f))

        val fecha = EditText(this)
        fecha.hint = "Fecha"
        pantalla.addView(fecha)

        val operario = EditText(this)
        operario.hint = "Nombre del operario"
        pantalla.addView(operario)

        val turno = EditText(this)
        turno.hint = "Turno: Día / Noche"
        pantalla.addView(turno)

        pantalla.addView(titulo("LECTURAS DE DISPENSADORES", 24f))

        // DISPENSADOR 1
        pantalla.addView(titulo("DISPENSADOR 1", 21f))

        val d1Lectura1 = campo("Lectura 1 - Inicial")
        val d1Lectura2 = campo("Lectura 1 - Final")
        val d1Lectura3 = campo("Lectura 2 - Inicial")
        val d1Lectura4 = campo("Lectura 2 - Final")

        pantalla.addView(d1Lectura1)
        pantalla.addView(d1Lectura2)
        pantalla.addView(d1Lectura3)
        pantalla.addView(d1Lectura4)

        val ventaD1 = titulo("VENTA DISPENSADOR 1: $ 0", 18f)
        pantalla.addView(ventaD1)

        // DISPENSADOR 2
        pantalla.addView(titulo("DISPENSADOR 2", 21f))

        val d2Lectura1 = campo("Lectura 1 - Inicial")
        val d2Lectura2 = campo("Lectura 1 - Final")
        val d2Lectura3 = campo("Lectura 2 - Inicial")
        val d2Lectura4 = campo("Lectura 2 - Final")

        pantalla.addView(d2Lectura1)
        pantalla.addView(d2Lectura2)
        pantalla.addView(d2Lectura3)
        pantalla.addView(d2Lectura4)

        val ventaD2 = titulo("VENTA DISPENSADOR 2: $ 0", 18f)
        pantalla.addView(ventaD2)

        // CRÉDITOS
        pantalla.addView(titulo("CRÉDITOS Y LUBRICANTES", 22f))

        val creditos = campo("Total créditos")
        pantalla.addView(creditos)

        val lubricantes = campo("Total venta lubricantes")
        pantalla.addView(lubricantes)

        val totalVenta = titulo("VENTA TOTAL: $ 0", 20f)
        pantalla.addView(totalVenta)

        val efectivo = titulo("EFECTIVO A ENTREGAR: $ 0", 20f)
        pantalla.addView(efectivo)

        val observaciones = EditText(this)
        observaciones.hint = "Observaciones"
        observaciones.minLines = 3
        pantalla.addView(observaciones)

        val calcular = Button(this)
        calcular.text = "CALCULAR RELEVO"

        calcular.setOnClickListener {

            val venta1 = (numero(d1Lectura2) - numero(d1Lectura1)) +
                    (numero(d1Lectura4) - numero(d1Lectura3))

            val venta2 = (numero(d2Lectura2) - numero(d2Lectura1)) +
                    (numero(d2Lectura4) - numero(d2Lectura3))

            val total = venta1 + venta2 + numero(lubricantes)
            val efectivoEntregar = total - numero(creditos)

            ventaD1.text = "VENTA DISPENSADOR 1: $ $venta1"
            ventaD2.text = "VENTA DISPENSADOR 2: $ $venta2"
            totalVenta.text = "VENTA TOTAL: $ $total"
            efectivo.text = "EFECTIVO A ENTREGAR: $ $efectivoEntregar"
        }

        pantalla.addView(calcular)

        val guardar = Button(this)
        guardar.text = "GUARDAR RELEVO"

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
