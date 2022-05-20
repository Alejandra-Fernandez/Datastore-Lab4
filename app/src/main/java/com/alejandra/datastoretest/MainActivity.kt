package com.alejandra.datastoretest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.federicocotogno.datastoretest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var userManager: UserManager
    var periodo = ""
    var escuela = ""
    var cod = 0
    var nombre = ""
    var semestre = ""
    var duracion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference to our userManager class
        userManager = UserManager(this)

        buttonSave()

        observeData()

    }

    private fun observeData() {


        userManager.userPeriodoFlow.asLiveData().observe(this, {
            periodo = it
            tv_periodo.text = it.toString()
        })

    userManager.userEscuelaFlow.asLiveData().observe(this, {
        escuela = it
        tv_escuela.text = it.toString()
    })


userManager.userCodFlow.asLiveData().observe(this, {
    cod = it
    tv_cod.text = it.toString()
})


userManager.userNombreFlow.asLiveData().observe(this, {
    nombre = it
    tv_nombre.text = it.toString()
})

userManager.userSemestreFlow.asLiveData().observe(this, {
    semestre = it
    tv_semestre.text = it.toString()
})

userManager.userDuracionFlow.asLiveData().observe(this, {
    duracion = it
    tv_duracion.text = it.toString()
})
}

private fun buttonSave() {

    //Gets the user input and saves it
    btn_save.setOnClickListener {
        periodo  = et_periodo.text.toString()
        escuela  = et_escuela.text.toString()
        cod = et_cod.text.toString().toInt()
        nombre = et_nombre.text.toString()
        semestre = et_semestre.text.toString()
        duracion = et_duracion.text.toString()

        //Stores the values
        GlobalScope.launch {
            userManager.storeUser(periodo, escuela, cod, nombre, semestre, duracion)
        }
    }


    }

}



