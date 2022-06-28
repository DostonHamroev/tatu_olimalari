package uz.hamroev.tatuolimalari.olimaDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [OlimaEntity::class], version = 1, exportSchema = false)

abstract class OlimaDatabase : RoomDatabase() {

    abstract fun getOlimaDao(): OlimaDao

    companion object {
        @Volatile
        private var database: OlimaDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        OlimaDatabase::class.java,
                        "olima.db"
                    )
                        .createFromAsset("olima.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
        }
    }

    object GET {
        fun getOlimaDatabase(): OlimaDatabase = database!!
    }

}