package uz.hamroev.tatuolimalari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.tatuolimalari.databinding.ItemNavBinding
import uz.hamroev.tatuolimalari.model.Nav

class NavAdapter(
    var context: Context,
    var list: ArrayList<Nav>,
    var onNavCLickListener: OnNavCLickListener
) :
    RecyclerView.Adapter<NavAdapter.VhNav>() {


    inner class VhNav(var itemNavBinding: ItemNavBinding) :
        RecyclerView.ViewHolder(itemNavBinding.root) {

        fun onBind(nav: Nav, position: Int) {
            itemNavBinding.name.text = nav.navName
            itemNavBinding.icon.setImageResource(nav.icon)
            itemNavBinding.main.setOnClickListener {
                onNavCLickListener.onClick(nav, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhNav {
        return VhNav(ItemNavBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhNav, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size


    interface OnNavCLickListener {
        fun onClick(nav: Nav, position: Int)
    }
}