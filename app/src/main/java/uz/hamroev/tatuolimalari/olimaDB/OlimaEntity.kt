package uz.hamroev.tatuolimalari.olimaDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "olima")
class OlimaEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name_full: String = ""
    var universitet_name: String = ""
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
    var address_phone   : String = ""


}
