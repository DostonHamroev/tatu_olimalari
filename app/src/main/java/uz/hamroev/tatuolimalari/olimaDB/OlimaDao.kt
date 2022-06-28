package uz.hamroev.tatuolimalari.olimaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface OlimaDao {


    @Query("SELECT * from olima")
    fun getAllOlima(): List<OlimaEntity>

    @Query("SELECT * FROM olima WHERE name_full LIKE '%' || :name || '%'")
    fun search(name: String): List<OlimaEntity>

    @Query("SELECT * FROM olima WHERE id=:olimaId")
    fun getOlimaById(olimaId: Int): List<OlimaEntity>


}