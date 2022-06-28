package uz.hamroev.tatuolimalari.app

import android.app.Application
import uz.hamroev.tatuolimalari.cache.Cache
import uz.hamroev.tatuolimalari.olimaDB.OlimaDatabase
import uz.hamroev.tatuolimalari.savedDB.SaveDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Cache.init(this)
        OlimaDatabase.init(this)
        SaveDatabase.getInstance(this)
    }
}