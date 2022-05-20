package com.alejandra.datastoretest

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    //Create the dataStore
    private val dataStore = context.createDataStore(name = "user_prefs")

    //Create some keys
    companion object {
        val USER_PERIODO_KEY = preferencesKey<String>("USER_PERIODO")
        val USER_ESCUELA_KEY = preferencesKey<String>("USER_ESCUELA")
        val USER_COD_KEY = preferencesKey<Int>("USER_COD")
        val USER_NOMBRE_KEY = preferencesKey<String>("USER_NOMBRE")
        val USER_SEMESTRE_KEY = preferencesKey<String>("USER_SEMESTRE")
        val USER_DURACION_KEY = preferencesKey<String>("USER_DURACION")

    }

    //Store user data
    suspend fun storeUser(periodo: String,escuela: String,cod: Int, nombre: String, semestre: String,duracion: String) {
        dataStore.edit {
            it[USER_PERIODO_KEY] = periodo
            it[USER_ESCUELA_KEY] = escuela
            it[USER_COD_KEY] = cod
            it[USER_NOMBRE_KEY] = nombre
            it[USER_SEMESTRE_KEY] = semestre
            it[USER_DURACION_KEY] = duracion

        }
    }
    val userPeriodoFlow: Flow<String> = dataStore.data.map {
        it[USER_PERIODO_KEY] ?: ""
    }

    val userEscuelaFlow: Flow<String> = dataStore.data.map {
        it[USER_ESCUELA_KEY] ?: ""
    }

    val userCodFlow: Flow<Int> = dataStore.data.map {
       it[USER_COD_KEY] ?: 0
    }


    val userNombreFlow: Flow<String> = dataStore.data.map {
        it[USER_NOMBRE_KEY] ?: ""
    }

    val userSemestreFlow: Flow<String> = dataStore.data.map {
        it[USER_SEMESTRE_KEY] ?: ""
    }

    val userDuracionFlow: Flow<String> = dataStore.data.map {
        it[USER_DURACION_KEY] ?: ""
    }


}