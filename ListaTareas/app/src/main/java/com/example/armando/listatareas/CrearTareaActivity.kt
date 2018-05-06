package com.example.armando.listatareas

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_tarea.*

class CrearTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)
        terminarButton.setOnClickListener {
            terminarClicked()
        }
    }
    fun terminarClicked() {
        val descipcionTarea = descripcionEditText.text.toString()
        if (!descipcionTarea.isEmpty()) {
            var intentResultado = Intent()
            intentResultado.putExtra("Tarea", descipcionTarea)
            setResult(Activity.RESULT_OK)
        }
        else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
