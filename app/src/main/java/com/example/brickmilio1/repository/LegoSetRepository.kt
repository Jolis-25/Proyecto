package com.example.brickmilio1.repository

import androidx.lifecycle.LiveData
import com.example.brickmilio1.dao.LegoSetDao
import com.example.brickmilio1.model.LegoSet

class LegoSetRepository(private val dao: LegoSetDao) {

    fun obtenerPorEstado(estado: String): LiveData<List<LegoSet>> =
        dao.obtenerPorEstado(estado)

    fun obtenerTodos(): LiveData<List<LegoSet>> =
        dao.obtenerTodos()

    suspend fun insertar(legoSet: LegoSet) = dao.insertar(legoSet)

    suspend fun actualizar(legoSet: LegoSet) = dao.actualizar(legoSet)

    suspend fun eliminar(legoSet: LegoSet) = dao.eliminar(legoSet)

    fun calcularTiempoMin(piezas: Int): Int {
        return when {
            piezas <= 500 -> (piezas * 0.5).toInt()
            piezas <= 1000 -> piezas * 1
            else -> (piezas * 1.5).toInt()
        }
    }

    fun formatearTiempo(minutos: Int): String {
        val horas = minutos / 60
        val mins = minutos % 60
        return when {
            horas == 0 -> "$mins min"
            mins == 0 -> "$horas h"
            else -> "$horas h $mins min"
        }
    }
}