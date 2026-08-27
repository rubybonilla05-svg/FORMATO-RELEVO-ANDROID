package com.formato.relevo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.widget.*
import java.text.NumberFormat
import java.util.Locale

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
            t.setPadding(0, 14, 0, 14)
            return t
        }

        fun campo(texto: String): EditText {
            val e = EditText(this)
            e.hint = texto
            e.textSize = 17f
            e.inputType = InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_FLAG_DECIMAL
            return e
        }

        fun numero(campo: EditText): Double {
            val texto = campo.text.toString().trim()
                .replace(",", ".")

            return texto.toDoubleOrNull() ?: 0.0
        }

        fun dinero(valor: Double): String {
            val formato = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
            return formato.format(valor)
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

        pantalla.addView(titulo("PRECIOS DE COMBUSTIBLE", 22f))

        val precioAcpm = campo("Precio ACPM por galón")
        val precioGasolina = campo("Precio gasolina por galón")

        pantalla.addView(precioAcpm)
        pantalla.addView(precioGasolina)

        pantalla.addView(titulo("LECTURAS DE DISPENSADORES", 24f))

        // Listas para guardar todos los campos de las 8 caras
        val acpmInicial = mutableListOf<EditText>()
        val acpmFinal = mutableListOf<EditText>()
        val gasolinaInicial = mutableListOf<EditText>()
        val gasolinaFinal = mutableListOf<EditText>()

        // Creamos los 2 dispensadores y sus 4 caras
        for (dispensador in 1..2) {

            pantalla.addView(titulo("DISPENSADOR $dispensador", 23f))

            for (cara in 1..4) {

                pantalla.addView(titulo("CARA $cara", 20f))

                pantalla.addView(titulo("A.C.P.M.", 17f))

                val acpmIni = campo("ACPM - Lectura inicial")
                val acpmFin = campo("ACPM - Lectura final")

                pantalla.addView(acpmIni)
                pantalla.addView(acpmFin)

                acpmInicial.add(acpmIni)
                acpmFinal.add(acpmFin)

                pantalla.addView(titulo("GASOLINA", 17f))

                val gasIni = campo("Gasolina - Lectura inicial")
                val gasFin = campo("Gasolina - Lectura final")

                pantalla.addView(gasIni)
                pantalla.addView(gasFin)

                gasolinaInicial.add(gasIni)
                gasolinaFinal.add(gasFin)
            }
        }

        // Resultados
        pantalla.addView(titulo("RESUMEN DE VENTAS", 24f))

        val ventaCombustible = titulo("VENTA COMBUSTIBLE: $ 0", 20f)
        pantalla.addView(ventaCombustible)

        val ventaAcpm = titulo("VENTA ACPM: $ 0", 18f)
        pantalla.addView(ventaAcpm)

        val ventaGasolina = titulo("VENTA GASOLINA: $ 0", 18f)
        pantalla.addView(ventaGasolina)

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

        // Botón calcular
        val calcular = Button(this)
        calcular.text = "CALCULAR RELEVO"

        calcular.setOnClickListener {

            var totalGalonesAcpm = 0.0
            var totalGalonesGasolina = 0.0

            // Sumamos las 8 caras
            for (i in 0 until 8) {

                val ventaA = numero(acpmFinal[i]) - numero(acpmInicial[i])
                val ventaG = numero(gasolinaFinal[i]) - numero(gasolinaInicial[i])

                totalGalonesAcpm += ventaA
                totalGalonesGasolina += ventaG
            }

            val totalDineroAcpm =
                totalGalonesAcpm * numero(precioAcpm)

            val totalDineroGasolina =
                totalGalonesGasolina * numero(precioGasolina)

            val totalCombustible =
                totalDineroAcpm + totalDineroGasolina

            val totalGeneral =
                totalCombustible + numero(lubricantes)

            val efectivoEntregar =
                totalGeneral - numero(creditos)

            ventaAcpm.text =
                "VENTA ACPM: ${String.format("%.2f", totalGalonesAcpm)} galones = ${dinero(totalDineroAcpm)}"

            ventaGasolina.text =
                "VENTA GASOLINA: ${String.format("%.2f", totalGalonesGasolina)} galones = ${dinero(totalDineroGasolina)}"

            ventaCombustible.text =
                "VENTA COMBUSTIBLE: ${dinero(totalCombustible)}"

            totalVenta.text =
                "VENTA TOTAL: ${dinero(totalGeneral)}"

            efectivo.text =
                "EFECTIVO A ENTREGAR: ${dinero(efectivoEntregar)}"
        }

        pantalla.addView(calcular)

        // Botón guardar
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
