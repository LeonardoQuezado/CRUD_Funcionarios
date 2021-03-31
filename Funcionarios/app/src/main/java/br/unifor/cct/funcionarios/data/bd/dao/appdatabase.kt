package br.unifor.cct.funcionarios.data.bd.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FuncionarioEntity::class], version = 1)

abstract class appdatabase:RoomDatabase(){

abstract val funcionarioDao:funcionarioDao
    companion object {
        @Volatile
        private var INSTANCE: appdatabase? = null

        fun getInstance(context: Context): appdatabase {
            synchronized(this) {
                var instance: appdatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        appdatabase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }
        }
    }
}