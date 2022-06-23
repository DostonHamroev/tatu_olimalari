package uz.hamroev.tatuolimalari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.tatuolimalari.databinding.ItemRoundBinding
import uz.hamroev.tatuolimalari.model.Round

class RoundAdapter(
    var context: Context,
    var list: ArrayList<Round>,
    var onRoundClickListener: OnRoundClickListener
) :
    RecyclerView.Adapter<RoundAdapter.VhRound>() {

    inner class VhRound(var itemRoundBinding: ItemRoundBinding) :
        RecyclerView.ViewHolder(itemRoundBinding.root) {

        fun onBind(round: Round, position: Int) {
            itemRoundBinding.userName.text = round.username
            itemRoundBinding.userImage.setImageResource(round.userImage)

            itemRoundBinding.main.setOnClickListener {
                onRoundClickListener.onCLick(round, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhRound {
        return VhRound(ItemRoundBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhRound, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnRoundClickListener {

        fun onCLick(round: Round, position: Int)
    }
}