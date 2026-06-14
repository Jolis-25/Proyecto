package com.example.brickmilio1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.brickmilio1.db.AppDatabase
import com.example.brickmilio1.model.LegoSet
import com.example.brickmilio1.repository.LegoSetRepository
import kotlinx.coroutines.launch

class LegoSetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LegoSetRepository
    val todosLosLegos: LiveData<List<LegoSet>>

    init {
        val dao = AppDatabase.getDatabase(application).legoSetDao()
        repository = LegoSetRepository(dao)
        todosLosLegos = repository.obtenerTodos()
    }

    fun obtenerPorEstado(estado: String): LiveData<List<LegoSet>> =
        repository.obtenerPorEstado(estado)

    fun insertar(legoSet: LegoSet) = viewModelScope.launch {
        repository.insertar(legoSet)
    }

    fun actualizar(legoSet: LegoSet) = viewModelScope.launch {
        repository.actualizar(legoSet)
    }

    fun eliminar(legoSet: LegoSet) = viewModelScope.launch {
        repository.eliminar(legoSet)
    }

    fun calcularTiempoMin(piezas: Int): Int =
        repository.calcularTiempoMin(piezas)

    fun formatearTiempo(minutos: Int): String =
        repository.formatearTiempo(minutos)
}