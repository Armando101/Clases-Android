package com.example.armando.listatareas
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
class Tarea: RealmObject() {
    var fecha: Date? = null
    var contenido: String? = null
}
