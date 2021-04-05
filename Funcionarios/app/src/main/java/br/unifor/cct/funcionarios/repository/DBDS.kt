package br.unifor.cct.funcionarios.repository

import androidx.lifecycle.LiveData
import br.unifor.cct.funcionarios.data.bd.dao.FuncionarioEntity
import br.unifor.cct.funcionarios.data.bd.dao.funcionarioDao
import java.util.*

class DBDS(private val  FuncionarioDAO:funcionarioDao)
    :FuncionarioRepository{
    override suspend fun insertFuncionario(name: String, salario: String, cpf: String, email: String): Long {
        val funcionario =
            FuncionarioEntity(
                name = name,
                salario = salario,
                cpf = cpf,
                email = email
            )
                return FuncionarioDAO.insert(funcionario)
    }

    override suspend fun updateFuncionario(id: Long, name: String, salario: String, cpf: String, email: String) {
        val funcionario =
            FuncionarioEntity(
                id = id,
                salario = salario,
                name = name,
                cpf = cpf,
                email = email
            )
            FuncionarioDAO.update(funcionario)

    }

    override suspend fun deleteFuncionario(id: Long) {
        FuncionarioDAO.delete(id)
    }

    override suspend fun deleteAllFuncionario() {
        FuncionarioDAO.deleteAll()
    }

    override suspend  fun getAllFuncionario(): List<FuncionarioEntity> {
     return  FuncionarioDAO.getAll()
    }
}