package br.unifor.cct.funcionarios.data.bd.dao

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "funcionario")

data class FuncionarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String,
    val salario:String,
    val cpf:String,
    val email: String
) : Parcelable





