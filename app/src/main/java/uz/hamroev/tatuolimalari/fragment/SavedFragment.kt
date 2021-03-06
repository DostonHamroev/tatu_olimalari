package uz.hamroev.tatuolimalari.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.hamroev.tatuolimalari.R
import uz.hamroev.tatuolimalari.activity.OlimaActivity
import uz.hamroev.tatuolimalari.adapter.SavedAdapter
import uz.hamroev.tatuolimalari.cache.Cache
import uz.hamroev.tatuolimalari.databinding.FragmentSavedBinding
import uz.hamroev.tatuolimalari.savedDB.Save
import uz.hamroev.tatuolimalari.savedDB.SaveDatabase


class SavedFragment : Fragment() {


    lateinit var binding: FragmentSavedBinding
    lateinit var saveDatabase: SaveDatabase
    var shareMessage: String =
        "https://play.google.com/store/apps/details?id="


    var message: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)

        shareMessage = "https://play.google.com/store/apps/details?id=${activity!!.packageName}"
        saveDatabase = SaveDatabase.getInstance(binding.root.context)
        loadSavedData()



        return binding.root
    }

    private fun loadSavedData() {
        val list = saveDatabase.saveDao().getAllSavedOLima().reversed()
        if (list.isEmpty()) {
            binding.rvSaved.visibility = View.GONE
            binding.notSearchYetLinear.visibility = View.VISIBLE
        } else {
            binding.rvSaved.visibility = View.VISIBLE
            binding.notSearchYetLinear.visibility = View.GONE
            var savedAdapter = SavedAdapter(
                binding.root.context,
                list,
                object : SavedAdapter.OnSavedClickListener {
                    override fun onCLick(save: Save, position: Int) {
                        Cache.olimaPosition = save.idOlima - 1
                        startActivity(Intent(binding.root.context, OlimaActivity::class.java))
                    }

                    override fun onShare(save: Save, position: Int) {
                        val name = "TATU OLIMALARI"
                        message = "$name\n\n" +
                                "* * * * * * *\n\n" +
                                "??.??.??. (??????????) - ${save.name_full}" +
                                "\n\n?????? ????????(??????????) - ${save.universitet_name}" +
                                "\n\n?????????????????????????? ???????? ???? ?????????? - ${save.mutaxasislik}" +
                                "\n\n?????????? ???? ??????????????  ?????????????? - ${save.ilmiy_nashrlar}" +
                                "\n\n???????????? ?????????? ?????????????????? ???????? - ${save.umumiy_maqolalar}" +
                                "\n\n?????? ???????? ???????????? - ${save.jurnal_chet}" +
                                "\n\n?????? ???????? ?????????????????????? ???????????????????????? - ${save.konferensiya_chet}" +
                                "\n\n???????????????????? ???????????????? ???????????? - ${save.jurnal_uzb}" +
                                "\n\n???????????????????? ???????????????? ?????????????????????? ???????????????????????? - ${save.konferensiya_uzb}" +
                                "\n\n?????????????? ???? ?????????????????????????? (????????) - ${save.patent_soni}" +
                                "\n\n?????????? ????????????????, ???????????????????? ???? ??. - ${save.ilmiy_yonalish}" +
                                "\n\n?????????????? ?????????????????????? (?????????????????? ????????????????, ?????????????????? ???? ??.) - \n${save.erishgan_yutuqlar}" +
                                "\n\n??????????, ??????????????, ???????????????? ?????????? - ${save.address_phone}" +
                                "" +
                                "\n\n* * * * * * * " +
                                "\n\n\n" +
                                "${shareMessage}"

                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        val chooser = Intent.createChooser(intent, "Share using...")
                        startActivity(chooser)
                    }
                })
            binding.rvSaved.adapter = savedAdapter


        }

    }


}