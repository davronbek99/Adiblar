package com.example.adiblar.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WriterEntity {

    @PrimaryKey
    var id: Int? = null


    var name: String? = null

    var imgUrl: String? = null

    var born: String? = null

    var desc: String? = null

}