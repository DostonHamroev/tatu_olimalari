package uz.hamroev.tatuolimalari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.tatuolimalari.databinding.ItemOlimaBinding
import uz.hamroev.tatuolimalari.model.Olima
import uz.hamroev.tatuolimalari.model.Round
import uz.hamroev.tatuolimalari.olimaDB.OlimaEntity

class OlimaAdapter(
    var context: Context,
    var list: List<OlimaEntity>,
    var listImage: ArrayList<Round>,
    var onOLimaClickListener: OnOLimaClickListener
) :
    RecyclerView.Adapter<OlimaAdapter.VHOLima>() {

    inner class VHOLima(var itemOlimaBinding: ItemOlimaBinding) :
        RecyclerView.ViewHolder(itemOlimaBinding.root) {

        fun onBind(olimaEntity: OlimaEntity, round: Round, position: Int) {
            itemOlimaBinding.name.text = olimaEntity.name_full
            itemOlimaBinding.info.text = olimaEntity.universitet_name
            itemOlimaBinding.image.setImageResource(round.userImage)

            itemOlimaBinding.main.setOnClickListener {
                onOLimaClickListener.onCLick(olimaEntity, position)
            }
            itemOlimaBinding.shareBtn.setOnClickListener {
                onOLimaClickListener.onShare(olimaEntity, position)
            }

            itemOlimaBinding.saveBtn.setOnClickListener {
                onOLimaClickListener.onSave(olimaEntity, position, itemOlimaBinding.saveBtn)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOLima {
        return VHOLima(ItemOlimaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHOLima, position: Int) {
        return holder.onBind(list[position], listImage[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnOLimaClickListener {
        fun onCLick(olimaEntity: OlimaEntity, position: Int)

        fun onShare(olimaEntity: OlimaEntity, position: Int)

        fun onSave(olimaEntity: OlimaEntity, position: Int, saveView: ImageView)
    }
}