package com.example.brickmilio1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.brickmilio1.model.LegoSet

@Dao
interface LegoSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(legoSet: LegoSet)

    @Update
    suspend fun actualizar(legoSet: LegoSet)

    @Delete
    suspend fun eliminar(legoSet: LegoSet)

    @Query("SELECT * FROM lego_sets WHERE estado = :estado ORDER BY nombre ASC")
    fun obtenerPorEstado(estado: String): LiveData<List<LegoSet>>

    @Query("SELECT * FROM lego_sets ORDER BY nombre ASC")
    fun obtenerTodos(): LiveData<List<LegoSet>>
}