package com.formato.relevo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
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

        fun campoNumero(texto: String): EditText {
            val e = EditText(this)
            e.hint = texto
            e.textSize = 17f
            e.inputType =
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            return e
        }

        fun numero(campo: EditText): Double {
            val texto = campo.text.toString().trim().replace(",", ".")
            return texto.toDoubleOrNull() ?: 0.0
        }

        fun dinero(valor: Double): String {
            val formato = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
            return formato.format(valor)
        }

        // ================= ENCABEZADO =================

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

        // ================= PRECIOS =================

        pantalla.addView(titulo("PRECIOS DE COMBUSTIBLE", 22f))

        val precioAcpm = campoNumero("Precio ACPM por galón")
        val precioGasolina = campoNumero("Precio gasolina por galón")

        pantalla.addView(precioAcpm)
        pantalla.addView(precioGasolina)

        // ================= DISPENSADORES =================

        pantalla.addView(titulo("LECTURAS DE DISPENSADORES", 24f))

        val acpmInicial = mutableListOf<EditText>()
        val acpmFinal = mutableListOf<EditText>()
        val gasolinaInicial = mutableListOf<EditText>()
        val gasolinaFinal = mutableListOf<EditText>()

        // 2 dispensadores, cada uno con 2 caras
        for (dispensador in 1..2) {

            pantalla.addView(titulo("DISPENSADOR $dispensador", 23f))

            for (cara in 1..2) {

                pantalla.addView(titulo("CARA $cara", 20f))

                pantalla.addView(titulo("A.C.P.M.", 17f))

                val acpmIni =
                    campoNumero("ACPM - Lectura inicial")
                val acpmFin =
                    campoNumero("ACPM - Lectura final")

                pantalla.addView(acpmIni)
                pantalla.addView(acpmFin)

                acpmInicial.add(acpmIni)
                acpmFinal.add(acpmFin)

                pantalla.addView(titulo("GASOLINA", 17f))

                val gasIni =
                    campoNumero("Gasolina - Lectura inicial")
                val gasFin =
                    campoNumero("Gasolina - Lectura final")

                pantalla.addView(gasIni)
                pantalla.addView(gasFin)

                gasolinaInicial.add(gasIni)
                gasolinaFinal.add(gasFin)
            }
        }

        // ================= RESUMEN COMBUSTIBLE =================

        pantalla.addView(titulo("RESUMEN DE VENTAS", 24f))

        val ventaAcpm =
            titulo("VENTA ACPM: $ 0", 18f)

        val ventaGasolina =
            titulo("VENTA GASOLINA: $ 0", 18f)

        val ventaCombustible =
            titulo("VENTA COMBUSTIBLE: $ 0", 20f)

        pantalla.addView(ventaAcpm)
        pantalla.addView(ventaGasolina)
        pantalla.addView(ventaCombustible)

        // ================= CRÉDITOS =================

        pantalla.addView(titulo("CRÉDITOS POR CLIENTE", 22f))

        val listaCreditos = LinearLayout(this)
        listaCreditos.orientation = LinearLayout.VERTICAL
        pantalla.addView(listaCreditos)

        val camposCredito = mutableListOf<EditText>()

        fun agregarCredito() {

            val fila = LinearLayout(this)
            fila.orientation = LinearLayout.HORIZONTAL
            fila.setPadding(0, 5, 0, 5)

            val cliente = EditText(this)
            cliente.hint = "Nombre cliente"

            val valor = campoNumero("Valor crédito")

            val eliminar = Button(this)
            eliminar.text = "X"

            cliente.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f)

            valor.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f)

            eliminar.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f)

            fila.addView(cliente)
            fila.addView(valor)
            fila.addView(eliminar)

            listaCreditos.addView(fila)
            camposCredito.add(valor)

            eliminar.setOnClickListener {
                listaCreditos.removeView(fila)
                camposCredito.remove(valor)
            }
        }

        val agregarCredito = Button(this)
        agregarCredito.text = "AGREGAR CRÉDITO"

        agregarCredito.setOnClickListener {
            agregarCredito()
        }

        pantalla.addView(agregarCredito)

        // Agrega el primer crédito automáticamente
        agregarCredito()

        val totalCreditos =
            titulo("TOTAL CRÉDITOS: $ 0", 20f)

        pantalla.addView(totalCreditos)

        // ================= LUBRICANTES =================

        pantalla.addView(titulo("LUBRICANTES", 22f))

        val lubricantes =
            campoNumero("Total venta lubricantes")

        pantalla.addView(lubricantes)

        // ================= TOTALES =================

        val totalVenta =
            titulo("VENTA TOTAL: $ 0", 20f)

        val efectivo =
            titulo("EFECTIVO A ENTREGAR: $ 0", 22f)

        pantalla.addView(totalVenta)
        pantalla.addView(efectivo)

        val observaciones = EditText(this)
        observaciones.hint = "Observaciones"
        observaciones.minLines = 3
        pantalla.addView(observaciones)

        // ================= CALCULAR =================

        val calcular = Button(this)
        calcular.text = "CALCULAR RELEVO"

        calcular.setOnClickListener {

            var galonesAcpm = 0.0
            var galonesGasolina = 0.0

            // Hay 4 caras en total:
            // 2 del dispensador 1 + 2 del dispensador 2
            for (i in 0 until acpmInicial.size) {

                galonesAcpm +=
                    numero(acpmFinal[i]) - numero(acpmInicial[i])

                galonesGasolina +=
                    numero(gasolinaFinal[i]) - numero(gasolinaInicial[i])
            }

            val dineroAcpm =
                galonesAcpm * numero(precioAcpm)

            val dineroGasolina =
                galonesGasolina * numero(precioGasolina)

            val combustible =
                dineroAcpm + dineroGasolina

            var sumaCreditos = 0.0

            for (credito in camposCredito) {
                sumaCreditos += numero(credito)
            }

            val total =
                combustible + numero(lubricantes)

            val efectivoEntregar =
                total - sumaCreditos

            ventaAcpm.text =
                "VENTA ACPM: ${String.format("%.2f", galonesAcpm)} galones = ${dinero(dineroAcpm)}"

            ventaGasolina.text =
                "VENTA GASOLINA: ${String.format("%.2f", galonesGasolina)} galones = ${dinero(dineroGasolina)}"

            ventaCombustible.text =
                "VENTA COMBUSTIBLE: ${dinero(combustible)}"

            totalCreditos.text =
                "TOTAL CRÉDITOS: ${dinero(sumaCreditos)}"

            totalVenta.text =
                "VENTA TOTAL: ${dinero(total)}"

            efectivo.text =
                "EFECTIVO A ENTREGAR: ${dinero(efectivoEntregar)}"
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
