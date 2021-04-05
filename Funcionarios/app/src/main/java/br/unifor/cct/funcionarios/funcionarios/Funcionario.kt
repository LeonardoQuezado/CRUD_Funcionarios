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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.unifor.cct.funcionarios.R
import br.unifor.cct.funcionarios.data.bd.dao.appdatabase
import br.unifor.cct.funcionarios.data.bd.dao.funcionarioDao
import br.unifor.cct.funcionarios.extensao.hideKeyboard
import br.unifor.cct.funcionarios.repository.DBDS
import br.unifor.cct.funcionarios.repository.FuncionarioRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.funcionario_fragment.*
import kotlinx.android.synthetic.main.funcionario_item.*

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

    private val args: FuncionarioArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.funcionario_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.funcionario?.let{
            button_subscriber.text = getString(R.string.funcionario_button_update)
            input_email.setText(Funcionario.name)
            input_email.setText(Funcionario.email)
            input_email2.setText(Funcionario.cpf)
            input_email3.setText(Funcionario.salario)
            button_delete.visibility = View.VISIBLE



        }
        observeEvents()
        setListeners()
    }
    private fun observeEvents() {
        viewModel.funcionarioStateEventData.observe(viewLifecycleOwner) { FuncionarioState ->
            when (FuncionarioState) {
                is FuncionarioViewModel.FuncionarioState.Inserted -> {
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }
                is FuncionarioViewModel.FuncionarioState.Updated->{
                    hideKeyboard()
                    findNavController().popBackStack()
                }

                is FuncionarioViewModel.FuncionarioState.Deleted->{
                    hideKeyboard()
                    findNavController().popBackStack()
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
            val name = text_funcionario_name.toString()
            val salario = text_funcionario_salario.toString()
            val cpf  = text_funcionario_cpf.toString()
            val email = text_funcionario_data.toString()
            viewModel.addFuncionario(name, salario,cpf,email)
        }


button_delete.setOnClickListener(){
viewModel.removeFuncionario(id:args.funcionario?.id?:0)
}
    }
}