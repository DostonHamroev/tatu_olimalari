package uz.hamroev.tatuolimalari.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "tatuolima"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var olimaPosition: Int?
        get() = sharedPreferences.getInt("olimaposition", 0)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putInt("olimaposition", value)
            }
        }


}
