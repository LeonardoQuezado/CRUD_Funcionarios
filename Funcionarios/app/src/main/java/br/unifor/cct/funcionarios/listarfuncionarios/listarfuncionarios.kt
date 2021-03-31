package br.unifor.cct.funcionarios.listarfuncionarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.unifor.cct.funcionarios.R
import br.unifor.cct.funcionarios.data.bd.dao.FuncionarioEntity
import br.unifor.cct.funcionarios.data.bd.dao.appdatabase
import br.unifor.cct.funcionarios.data.bd.dao.funcionarioDao
import br.unifor.cct.funcionarios.funcionarios.FuncionarioViewModel
import br.unifor.cct.funcionarios.repository.DBDS
import br.unifor.cct.funcionarios.repository.FuncionarioRepository
import kotlinx.android.synthetic.main.listarfuncionarios_fragment.*

class listarfuncionarios : Fragment(R.layout.listarfuncionarios_fragment) {


    private val viewModel: ListarfuncionariosViewModel by viewModels {

        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val funcionarioDao: funcionarioDao =
                    appdatabase.getInstance(requireContext()).funcionarioDao

                val repository: FuncionarioRepository = DBDS(funcionarioDao)
                return ListarfuncionariosViewModel(repository) as T
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        confgViewListeners()

    }
    private fun observeViewModelEvents(){
        viewModel.allfuncionariosEvent.observe(viewLifecycleOwner) {allFuncionarios->
           val funcionarioListAdapter = FuncionarioListAdapter (allFuncionarios).apply {
               onItemClick = {

                  // findNavController().navigate(directions)
               }
           }

            with(recycler_funcionarios) {
                setHasFixedSize(true)
                adapter = funcionarioListAdapter
            }
        }





    }


    private fun  confgViewListeners(){

        fabAddfunc.setOnClickListener{
            findNavController().navigate(R.id.funcionario)
        }

    }




}


