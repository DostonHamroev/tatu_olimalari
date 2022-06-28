package uz.hamroev.tatuolimalari.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.hamroev.tatuolimalari.adapter.Olima2Adapter
import uz.hamroev.tatuolimalari.databinding.FragmentSearchBinding
import uz.hamroev.tatuolimalari.model.Round
import uz.hamroev.tatuolimalari.olimaDB.OlimaDatabase
import uz.hamroev.tatuolimalari.olimaDB.OlimaEntity

class SearchFragment : Fragment() {


    lateinit var binding: FragmentSearchBinding
    lateinit var listImage: ArrayList<Round>

    var message: String = ""
    var shareMessage: String =
        "https://play.google.com/store/apps/details?id="


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        shareMessage = "https://play.google.com/store/apps/details?id=${activity!!.packageName}"

        searchOlima()



        return binding.root
    }

    private fun searchOlima() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isEmpty()) {
                    binding.notSearchYetLinear.visibility = View.VISIBLE
                    binding.rvSearch.visibility = View.GONE
                } else {
                    if (query.trim().length >= 2) {
                        filter(query.toString())
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "2 та белгидан кам бўлмасин !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()){
                    binding.notSearchYetLinear.visibility = View.VISIBLE
                    binding.rvSearch.visibility = View.GONE
                } else {
                    binding.notSearchYetLinear.visibility = View.GONE
                    binding.rvSearch.visibility = View.VISIBLE
                }
                return true
            }

        })
    }

    private fun filter(name: String) {
        try {
            val list = OlimaDatabase.GET.getOlimaDatabase().getOlimaDao().search(name)

            if (list.isEmpty()) {
                binding.notSearchYetLinear.visibility = View.VISIBLE
                binding.rvSearch.visibility = View.GONE
            } else {
                binding.notSearchYetLinear.visibility = View.GONE
                binding.rvSearch.visibility = View.VISIBLE
                var olimaAdapter = Olima2Adapter(
                    binding.root.context,
                    list,
                    object : Olima2Adapter.OnOLimaClickListener {
                        override fun onCLick(olimaEntity: OlimaEntity, position: Int) {


                        }

                        override fun onShare(olimaEntity: OlimaEntity, position: Int) {
                            val name = "TATU OLIMALARI"
                            message = "$name\n\n" +
                                    "* * * * * * *\n\n" +
                                    "Ф.И.Ш. (тўлиқ) - ${olimaEntity.name_full}" +
                                    "\n\nОТМ номи(тўлиқ) - ${olimaEntity.universitet_name}" +
                                    "\n\nМутахассислик номи ва шифри - ${olimaEntity.mutaxasislik}" +
                                    "\n\nИлмий ва услубий  нашрлар - ${olimaEntity.ilmiy_nashrlar}" +
                                    "\n\nУмумий илмий мақолалар сони - ${olimaEntity.umumiy_maqolalar}" +
                                    "\n\nЧет элда журнал - ${olimaEntity.jurnal_chet}" +
                                    "\n\nЧет элда конференция материаллари - ${olimaEntity.konferensiya_chet}" +
                                    "\n\nРеспублика миқёсида журнал - ${olimaEntity.jurnal_uzb}" +
                                    "\n\nРеспублика миқёсида конференция материаллари - ${olimaEntity.konferensiya_uzb}" +
                                    "\n\nПантент ва сертификатлар (сони) - ${olimaEntity.patent_soni}" +
                                    "\n\nИлмий йўналиши, иҳтиролари ва ҳ. - ${olimaEntity.ilmiy_yonalish}" +
                                    "\n\nҚўшимча маълумотлар (Эришилган ютуқлари, лойҳалари ва ҳ.) - \n${olimaEntity.erishgan_yutuqlar}" +
                                    "\n\nАдрес, телефон, электрон почта - ${olimaEntity.address_phone}" +
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


                        override fun onSave(olimaEntity: OlimaEntity, position: Int) {

                        }

                    })
                binding.rvSearch.adapter = olimaAdapter
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}