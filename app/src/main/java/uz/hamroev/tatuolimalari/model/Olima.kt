package uz.hamroev.tatuolimalari.model

import uz.hamroev.tatuolimalari.olimaDB.OlimaEntity

data class Olima(
    var listImage: ArrayList<Round>,
    var listOlima: List<OlimaEntity>
)
