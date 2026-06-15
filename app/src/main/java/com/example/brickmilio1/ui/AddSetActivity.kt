package com.example.brickmilio1.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.brickmilio1.R
import com.example.brickmilio1.model.LegoSet
import com.example.brickmilio1.viewmodel.LegoSetViewModel
import com.google.android.material.textfield.TextInputEditText

class AddSetActivity : AppCompatActivity() {

    private lateinit var viewModel: LegoSetViewModel
    private lateinit var etNombre: TextInputEditText
    private lateinit var etPiezas: TextInputEditText
    private lateinit var etCategoria: TextInputEditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_set)

        viewModel = ViewModelProvider(this)[LegoSetViewModel::class.java]

        val estadoRecibido = intent.getStringExtra("estado") ?: "por_armar"

        etNombre = findViewById(R.id.etNombreSet)
        etPiezas = findViewById(R.id.etPiezas)
        etCategoria = findViewById(R.id.etCategoria)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val piezasStr = etPiezas.text.toString().trim()
            val categoria = etCategoria.text.toString().trim()

            if (nombre.isEmpty() || piezasStr.isEmpty() || categoria.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val piezas = piezasStr.toInt()
            val tiempoMin = viewModel.calcularTiempoMin(piezas)

            val legoSet = LegoSet(
                nombre = nombre,
                numeroPiezas = piezas,
                categoria = categoria,
                estado = estadoRecibido,
                tiempoEstimadoMin = tiempoMin
            )

            viewModel.insertar(legoSet)

            val tiempo = viewModel.formatearTiempo(tiempoMin)
            Toast.makeText(this, "¡Set guardado! Tiempo estimado: $tiempo", Toast.LENGTH_LONG).show()
            finish()
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}