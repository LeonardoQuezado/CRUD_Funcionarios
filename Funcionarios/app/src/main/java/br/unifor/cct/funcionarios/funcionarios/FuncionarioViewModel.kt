package br.unifor.cct.funcionarios.funcionarios

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.unifor.cct.funcionarios.R
import br.unifor.cct.funcionarios.repository.FuncionarioRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class FuncionarioViewModel(private val repository: FuncionarioRepository) : ViewModel() {

    private val _funcionarioStateEventData = MutableLiveData<FuncionarioState>()
    val funcionarioStateEventData: LiveData<FuncionarioState>
        get() = _funcionarioStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData


    fun addFuncionario(name:String, salario:String, cpf:String,email: String) = viewModelScope.launch {
    try {
        val id = repository.insertFuncionario(name, salario, cpf, email)
            if (id>0){
                _funcionarioStateEventData.value=FuncionarioState.Inserted
                _messageEventData.value= R.string.funcionario_inserted_successful
            }

    }catch (ex: Exception){
        _messageEventData.value=R.string.funcionario_error_to_insert
        Log.e(TAG, ex.toString())
    }
}


    sealed class FuncionarioState {
        object Inserted : FuncionarioState()
    }
        companion object{
            private  val TAG = FuncionarioViewModel::class.java.simpleName
        }
}