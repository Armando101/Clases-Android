package com.example.armando.listatareas

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition
import java.util.*

class MainActivity : AppCompatActivity() {

    private var listaTarea: MutableList<Tarea> = mutableListOf()
    //Adaptador
    private val adaptador by lazy { makeAdapter(listaTarea) }
    private lateinit var realm: Realm
    private val AGREGAR_TAREA_CODE = 1024

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        // Crear la base datos y tener una referencia a ella.
        val config = RealmConfiguration.Builder().name("tareas.realm").build()
        realm = Realm.getInstance(config)
        tareasListView.adapter=adaptador

        //Aniade un onClickListener para cada elemento del List View
        tareasListView.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            tareaSeleccionada(position)
        }

        agregarButton.setOnClickListener{
            agregarTarea()
        }
        //Hacer una consulta por primera vez
        val tareasIniciales = realm.where(Tarea::class.java).and().findAll()
        if (tareasIniciales.isNotEmpty()) {
            tareasIniciales.forEach{
                listaTarea.add(it)
            }
        }
    }

    fun tareaSeleccionada(position: Int) {
        AlertDialog.Builder(this).setTitle("Tarea").setPositiveButton("Borrar", {
            _, _ ->
            val tarea = listaTarea.removeAt(position)

            val tareaBorrar = realm.where(Tarea::class.java).equalTo("fecha", tarea.fecha).and().equalTo("contenido", tarea.contenido).findAll()

            tareaBorrar.forEach {
                realm.beginTransaction()
                it.deleteFromRealm()
                realm.commitTransaction()
            }
        }).setNegativeButton("cancel", {
            dialog, _ -> dialog.cancel()
        }).create().show()
    }

    private fun  agregarTarea() {
        val intent = Intent(this, CrearTareaActivity::class.java)
        startActivityForResult(intent, AGREGAR_TAREA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (AGREGAR_TAREA_CODE == requestCode && Activity.RESULT_OK==resultCode) {
            val extra = data?.getStringExtra("TAREA")
            extra?.let {
                realm.beginTransaction()
                val tarea = realm.createObject(Tarea::class.java)
                tarea.fecha = Date()
                tarea.contenido = it
                realm.commitTransaction() //Aqui se almacena en la basede datos
            }
            //SELECT * FROM TAREAS;
            val consulta = realm.where(Tarea::class.java).findAll()
            listaTarea.clear()

            //for (tarea in consulta) {}
            consulta.forEach{tarea ->
                listaTarea.add(tarea)
            }
            adaptador.notifyDataSetChanged()
        }
    }

    private fun makeAdapter(list: List<Tarea>):ArrayAdapter<Tarea> = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)

}
















