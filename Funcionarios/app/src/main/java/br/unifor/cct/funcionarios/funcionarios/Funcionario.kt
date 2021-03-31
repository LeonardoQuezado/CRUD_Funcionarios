package br.unifor.cct.funcionarios.funcionarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import br.unifor.cct.funcionarios.R
import br.unifor.cct.funcionarios.data.bd.dao.appdatabase
import br.unifor.cct.funcionarios.data.bd.dao.funcionarioDao
import br.unifor.cct.funcionarios.extensao.hideKeyboard
import br.unifor.cct.funcionarios.repository.DBDS
import br.unifor.cct.funcionarios.repository.FuncionarioRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.funcionario_fragment.*

class Funcionario : Fragment() {

    companion object {
        fun newInstance() = Funcionario()
    }
    private val viewModel: FuncionarioViewModel by viewModels {

        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val funcionarioDao: funcionarioDao =
                    appdatabase.getInstance(requireContext()).funcionarioDao

                val repository: FuncionarioRepository = DBDS(funcionarioDao)
                return FuncionarioViewModel(repository) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.funcionario_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setListeners()
    }
    private fun observeEvents() {
        viewModel.funcionarioStateEventData.observe(viewLifecycleOwner) { FuncionarioState ->
            when (FuncionarioState) {
                is FuncionarioViewModel.FuncionarioState.Inserted -> {
                    hideKeyboard()
                    requireView().requestFocus()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }


    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }
    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val name = input_name.text.toString()
            val salario = input_email.text.toString()
            val cpf  = input_email.text.toString()
            val email = input_email.text.toString()
            viewModel.addFuncionario(name, salario,cpf,email)
        }
    }
}