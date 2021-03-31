package br.unifor.cct.funcionarios.listarfuncionarios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unifor.cct.funcionarios.R
import br.unifor.cct.funcionarios.data.bd.dao.FuncionarioEntity
import kotlinx.android.synthetic.main.funcionario_item.view.*

class FuncionarioListAdapter(
    private val funcionario:List<FuncionarioEntity>
) : RecyclerView.Adapter<FuncionarioListAdapter.FuncionarioListViewHolder>() {
    var onItemClick:((entity: FuncionarioEntity) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.funcionario_item, parent, false)
        return FuncionarioListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuncionarioListViewHolder, position: Int) {
        holder.bindView(funcionario[position])
    }
    override fun getItemCount() = funcionario.size

    inner class FuncionarioListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewFuncionaroName: TextView = itemView.text_funcionario_name
        private val textViewFuncionarioSalario: TextView = itemView.text_funcionario_salario
        private val textViewFuncionaroCpf: TextView = itemView.text_funcionario_cpf
        private val textViewFuncionarioEmail: TextView = itemView.text_funcionario_data

        fun bindView(funcionario: FuncionarioEntity) {
            textViewFuncionaroName.text = funcionario.name
            textViewFuncionarioSalario.text = funcionario.salario
            textViewFuncionaroCpf.text = funcionario.cpf
            textViewFuncionarioEmail.text = funcionario.email
                itemView.setOnClickListener{
                    onItemClick?.invoke(funcionario)
                }

        }
    }
}