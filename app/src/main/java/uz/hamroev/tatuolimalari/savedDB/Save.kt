package uz.hamroev.tatuolimalari.savedDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved")
class Save {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var idOlima: Int = 0


    var universitet_name: String = ""
    var name_full: String = ""
    var mutaxasislik: String = ""

    var ilmiy_nashrlar: Int = 0
    var umumiy_maqolalar: Int = 0

    var jurnal_chet: Int = 0
    var konferensiya_chet: Int = 0

    var jurnal_uzb: Int = 0
    var konferensiya_uzb: Int = 0

    var patent_soni: Int = 0

    var ilmiy_yonalish: String = ""
    var erishgan_yutuqlar: String = ""
    var address_phone: String = ""

    var position: Int = 0

    constructor()
    constructor(
        idOlima: Int,
        universitet_name: String,
        name_full: String,
        mutaxasislik: String,
        ilmiy_nashrlar: Int,
        umumiy_maqolalar: Int,
        jurnal_chet: Int,
        konferensiya_chet: Int,
        jurnal_uzb: Int,
        konferensiya_uzb: Int,
        patent_soni: Int,
        ilmiy_yonalish: String,
        erishgan_yutuqlar: String,
        address_phone: String,
        position: Int
    ) {
        this.idOlima = idOlima
        this.universitet_name = universitet_name
        this.name_full = name_full
        this.mutaxasislik = mutaxasislik
        this.ilmiy_nashrlar = ilmiy_nashrlar
        this.umumiy_maqolalar = umumiy_maqolalar
        this.jurnal_chet = jurnal_chet
        this.konferensiya_chet = konferensiya_chet
        this.jurnal_uzb = jurnal_uzb
        this.konferensiya_uzb = konferensiya_uzb
        this.patent_soni = patent_soni
        this.ilmiy_yonalish = ilmiy_yonalish
        this.erishgan_yutuqlar = erishgan_yutuqlar
        this.address_phone = address_phone
        this.position = position
    }


}