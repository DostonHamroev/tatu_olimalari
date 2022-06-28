package uz.hamroev.tatuolimalari.savedDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Save::class], version = 1)
abstract class SaveDatabase : RoomDatabase() {

    abstract fun saveDao(): SaveDao

    companion object {
        private var instance: SaveDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SaveDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    SaveDatabase::class.java,
                    "saved.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}