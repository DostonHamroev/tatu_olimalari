package uz.hamroev.tatuolimalari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.tatuolimalari.databinding.ItemOlimaBinding
import uz.hamroev.tatuolimalari.databinding.ItemSavedBinding
import uz.hamroev.tatuolimalari.model.Olima
import uz.hamroev.tatuolimalari.model.Round
import uz.hamroev.tatuolimalari.olimaDB.OlimaEntity
import uz.hamroev.tatuolimalari.savedDB.Save

class SavedAdapter(
    var context: Context,
    var list: List<Save>,
    var onOLimaClickListener: OnSavedClickListener
) :
    RecyclerView.Adapter<SavedAdapter.VHOLima>() {

    inner class VHOLima(var itemSavedBinding: ItemSavedBinding) :
        RecyclerView.ViewHolder(itemSavedBinding.root) {

        fun onBind(save: Save, position: Int) {
            itemSavedBinding.name.text = save.name_full
            itemSavedBinding.info.text = save.universitet_name

            itemSavedBinding.shareBtn.setOnClickListener {
                onOLimaClickListener.onShare(save, position)
            }
            itemSavedBinding.main.setOnClickListener {
                onOLimaClickListener.onCLick(save, position)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOLima {
        return VHOLima(ItemSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHOLima, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnSavedClickListener {

        fun onCLick(save: Save, position: Int)

        fun onShare(save: Save, position: Int)

    }
}