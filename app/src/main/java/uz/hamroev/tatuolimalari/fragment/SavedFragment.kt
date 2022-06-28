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
                                "Ф.И.Ш. (тўлиқ) - ${save.name_full}" +
                                "\n\nОТМ номи(тўлиқ) - ${save.universitet_name}" +
                                "\n\nМутахассислик номи ва шифри - ${save.mutaxasislik}" +
                                "\n\nИлмий ва услубий  нашрлар - ${save.ilmiy_nashrlar}" +
                                "\n\nУмумий илмий мақолалар сони - ${save.umumiy_maqolalar}" +
                                "\n\nЧет элда журнал - ${save.jurnal_chet}" +
                                "\n\nЧет элда конференция материаллари - ${save.konferensiya_chet}" +
                                "\n\nРеспублика миқёсида журнал - ${save.jurnal_uzb}" +
                                "\n\nРеспублика миқёсида конференция материаллари - ${save.konferensiya_uzb}" +
                                "\n\nПантент ва сертификатлар (сони) - ${save.patent_soni}" +
                                "\n\nИлмий йўналиши, иҳтиролари ва ҳ. - ${save.ilmiy_yonalish}" +
                                "\n\nҚўшимча маълумотлар (Эришилган ютуқлари, лойҳалари ва ҳ.) - \n${save.erishgan_yutuqlar}" +
                                "\n\nАдрес, телефон, электрон почта - ${save.address_phone}" +
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