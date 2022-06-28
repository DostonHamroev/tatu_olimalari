package uz.hamroev.tatuolimalari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.tatuolimalari.databinding.ItemOlima2Binding
import uz.hamroev.tatuolimalari.databinding.ItemOlimaBinding
import uz.hamroev.tatuolimalari.model.Olima
import uz.hamroev.tatuolimalari.model.Round
import uz.hamroev.tatuolimalari.olimaDB.OlimaEntity

class Olima2Adapter(
    var context: Context,
    var list: List<OlimaEntity>,
    var onOLimaClickListener: OnOLimaClickListener
) :
    RecyclerView.Adapter<Olima2Adapter.VHOLima>() {

    inner class VHOLima(var itemOlimaBinding: ItemOlima2Binding) :
        RecyclerView.ViewHolder(itemOlimaBinding.root) {

        fun onBind(olimaEntity: OlimaEntity, position: Int) {
            itemOlimaBinding.name.text = olimaEntity.name_full
            itemOlimaBinding.info.text = olimaEntity.universitet_name

            itemOlimaBinding.main.setOnClickListener {
                onOLimaClickListener.onCLick(olimaEntity, position)
            }
            itemOlimaBinding.shareBtn.setOnClickListener {
                onOLimaClickListener.onShare(olimaEntity, position)
            }

            itemOlimaBinding.saveBtn.setOnClickListener {
                onOLimaClickListener.onSave(olimaEntity, position)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOLima {
        return VHOLima(
            ItemOlima2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VHOLima, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnOLimaClickListener {
        fun onCLick(olimaEntity: OlimaEntity, position: Int)

        fun onShare(olimaEntity: OlimaEntity, position: Int)

        fun onSave(olimaEntity: OlimaEntity, position: Int)
    }
}