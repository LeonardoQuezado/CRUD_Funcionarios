package br.unifor.cct.funcionarios.repository

import androidx.lifecycle.LiveData
import br.unifor.cct.funcionarios.data.bd.dao.FuncionarioEntity
import java.util.*

interface FuncionarioRepository {

    suspend fun insertFuncionario(name: String, salario: String, cpf: String,  email:String): Long

    suspend fun updateFuncionario(id: Long, name: String, salario: String, cpf: String, email:String)

    suspend fun deleteFuncionario(id: Long)

    suspend fun deleteAllFuncionario()

    fun getAllFuncionario(): LiveData<List<FuncionarioEntity>>
}