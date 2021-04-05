package br.unifor.cct.funcionarios.listarfuncionarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.unifor.cct.funcionarios.data.bd.dao.FuncionarioEntity
import br.unifor.cct.funcionarios.repository.FuncionarioRepository
import kotlinx.coroutines.launch

class ListarfuncionariosViewModel(private val repository: FuncionarioRepository) : ViewModel() {


    private val _allFuncionarioEvent = MutableLiveData<List<FuncionarioEntity>>()
    val allfuncionariosEvent : LiveData<List<FuncionarioEntity>>
        get() = _allFuncionarioEvent


    fun getFuncionarios() = viewModelScope.launch {
        _allFuncionarioEvent.postValue(repository.getAllFuncionario())
    }
}