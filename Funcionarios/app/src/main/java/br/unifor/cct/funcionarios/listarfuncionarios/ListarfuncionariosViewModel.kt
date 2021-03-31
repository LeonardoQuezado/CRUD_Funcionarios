package br.unifor.cct.funcionarios.listarfuncionarios

import androidx.lifecycle.ViewModel
import br.unifor.cct.funcionarios.repository.FuncionarioRepository

class ListarfuncionariosViewModel(private val repository: FuncionarioRepository) : ViewModel() {

val allfuncionariosEvent = repository.getAllFuncionario()
}