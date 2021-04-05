package br.unifor.cct.funcionarios.data.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao


interface funcionarioDao {

    @Insert
    suspend fun insert(funcionarioDao: FuncionarioEntity): Long

    @Update
    suspend fun update(funcionarioEntity: FuncionarioEntity)

    @Query("DELETE FROM funcionario WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM funcionario")
    suspend fun deleteAll()

    @Query("SELECT * FROM funcionario")
    suspend fun getAll(): List<FuncionarioEntity>
}