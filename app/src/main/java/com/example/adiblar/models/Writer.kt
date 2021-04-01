package com.example.adiblar.models

import java.io.Serializable

class Writer : Serializable {
    var id: Int? = null
    var name: String? = null
    var born: String? = null
    var img: String? = null
    var desc: String? = null

    constructor()
    constructor(id: Int?, name: String?, born: String?, img: String?, desc: String?) {
        this.id = id
        this.name = name
        this.born = born
        this.img = img
        this.desc = desc
    }


}