package uz.hamroev.tatuolimalari.savedDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SaveDao {

    @Query("SELECT * from saved")
    fun getAllSavedOLima(): List<Save>

    @Insert
    fun addOlima(save: Save)
}