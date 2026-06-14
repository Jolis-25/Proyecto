package com.example.brickmilio1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lego_sets")
data class LegoSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val numeroPiezas: Int,
    val categoria: String,
    val estado: String,
    val fotoUri: String? = null,
    val tiempoEstimadoMin: Int = 0
)