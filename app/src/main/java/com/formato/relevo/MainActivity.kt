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

        // ================= DISPENSADOR 1 =================

        pantalla.addView(titulo("DISPENSADOR 1", 22f))

        pantalla.addView(titulo("CARA 1", 18f))
        val d1c1Inicial = campo("Cara 1 - Lectura inicial")
        val d1c1Final = campo("Cara 1 - Lectura final")
        pantalla.addView(d1c1Inicial)
        pantalla.addView(d1c1Final)

        pantalla.addView(titulo("CARA 2", 18f))
        val d1c2Inicial = campo("Cara 2 - Lectura inicial")
        val d1c2Final = campo("Cara 2 - Lectura final")
        pantalla.addView(d1c2Inicial)
        pantalla.addView(d1c2Final)

        pantalla.addView(titulo("CARA 3", 18f))
        val d1c3Inicial = campo("Cara 3 - Lectura inicial")
        val d1c3Final = campo("Cara 3 - Lectura final")
        pantalla.addView(d1c3Inicial)
        pantalla.addView(d1c3Final)

        pantalla.addView(titulo("CARA 4", 18f))
        val d1c4Inicial = campo("Cara 4 - Lectura inicial")
        val d1c4Final = campo("Cara 4 - Lectura final")
        pantalla.addView(d1c4Inicial)
        pantalla.addView(d1c4Final)

        val ventaD1 = titulo("VENTA DISPENSADOR 1: 0", 20f)
        pantalla.addView(ventaD1)

        // ================= DISPENSADOR 2 =================

        pantalla.addView(titulo("DISPENSADOR 2", 22f))

        pantalla.addView(titulo("CARA 1", 18f))
        val d2c1Inicial = campo("Cara 1 - Lectura inicial")
        val d2c1Final = campo("Cara 1 - Lectura final")
        pantalla.addView(d2c1Inicial)
        pantalla.addView(d2c1Final)

        pantalla.addView(titulo("CARA 2", 18f))
        val d2c2Inicial = campo("Cara 2 - Lectura inicial")
        val d2c2Final = campo("Cara 2 - Lectura final")
        pantalla.addView(d2c2Inicial)
        pantalla.addView(d2c2Final)

        pantalla.addView(titulo("CARA 3", 18f))
        val d2c3Inicial = campo("Cara 3 - Lectura inicial")
        val d2c3Final = campo("Cara 3 - Lectura final")
        pantalla.addView(d2c3Inicial)
        pantalla.addView(d2c3Final)

        pantalla.addView(titulo("CARA 4", 18f))
        val d2c4Inicial = campo("Cara 4 - Lectura inicial")
        val d2c4Final = campo("Cara 4 - Lectura final")
        pantalla.addView(d2c4Inicial)
        pantalla.addView(d2c4Final)

        val ventaD2 = titulo("VENTA DISPENSADOR 2: 0", 20f)
        pantalla.addView(ventaD2)

        // ================= OTROS VALORES =================

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

        // ================= CALCULAR =================

        val calcular = Button(this)
        calcular.text = "CALCULAR RELEVO"

        calcular.setOnClickListener {

            val cara1D1 = numero(d1c1Final) - numero(d1c1Inicial)
            val cara2D1 = numero(d1c2Final) - numero(d1c2Inicial)
            val cara3D1 = numero(d1c3Final) - numero(d1c3Inicial)
            val cara4D1 = numero(d1c4Final) - numero(d1c4Inicial)

            val venta1 = cara1D1 + cara2D1 + cara3D1 + cara4D1

            val cara1D2 = numero(d2c1Final) - numero(d2c1Inicial)
            val cara2D2 = numero(d2c2Final) - numero(d2c2Inicial)
            val cara3D2 = numero(d2c3Final) - numero(d2c3Inicial)
            val cara4D2 = numero(d2c4Final) - numero(d2c4Inicial)

            val venta2 = cara1D2 + cara2D2 + cara3D2 + cara4D2

            val total = venta1 + venta2 + numero(lubricantes)
            val efectivoEntregar = total - numero(creditos)

            ventaD1.text = "VENTA DISPENSADOR 1: $venta1"
            ventaD2.text = "VENTA DISPENSADOR 2: $venta2"
            totalVenta.text = "VENTA TOTAL: $ $total"
            efectivo.text = "EFECTIVO A ENTREGAR: $ $efectivoEntregar"
        }

        pantalla.addView(calcular)

        // ================= GUARDAR =================

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
