package uz.hamroev.tatuolimalari.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import uz.hamroev.tatuolimalari.R
import uz.hamroev.tatuolimalari.cache.Cache
import uz.hamroev.tatuolimalari.databinding.ActivityOlimaBinding
import uz.hamroev.tatuolimalari.model.Round
import uz.hamroev.tatuolimalari.olimaDB.OlimaDatabase

class OlimaActivity : AppCompatActivity() {
    lateinit var binding: ActivityOlimaBinding

    lateinit var listRound: ArrayList<Round>
    var message: String = ""
    var shareMessage: String =
        "https://play.google.com/store/apps/details?id="

    private val TAG = "OlimaActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOlimaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        shareMessage = "https://play.google.com/store/apps/details?id=$packageName"

        binding.backButton.setOnClickListener {
            finish()
        }

        loadRound()
        loadOLimaData()

        val userImage = listRound[Cache.olimaPosition!!].userImage
        binding.userImage.setImageResource(userImage)

        share()

    }

    private fun share() {
        val listOlima = OlimaDatabase.GET.getOlimaDatabase().getOlimaDao()
            .getOlimaById(Cache.olimaPosition!! + 1)

        binding.shareButton.setOnClickListener {

            listOlima.forEach { olimaEntity ->
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
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            val chooser = Intent.createChooser(intent, "Share using...")
            startActivity(chooser)
        }
    }


    private fun loadRound() {
        listRound = ArrayList()
        listRound.add(Round("Назирова\n Элмира \nШодмоновна", R.drawable.ic1_elmira))
        listRound.add(Round("Абдуллаева\n Замира \nШамшаддиновна", R.drawable.ic2_zamira))
        listRound.add(Round("Пузий\n Анастасия \nНиколаевна", R.drawable.ic3_anastasiya))
        listRound.add(Round("Хидирова\n Чарос \nМуродиллаевна", R.drawable.ic4_charoy))
        listRound.add(Round("Юсупова\n Зайнабхон \nДжуманазаровна", R.drawable.ic5_zaynabxon))
        listRound.add(Round("Умарова\n Дилдора \nБахтияровна", R.drawable.ic6_dildora))
        listRound.add(Round("Позилова\n Шахноза \nХайдаралиевна", R.drawable.ic7_shaxnoza))
        listRound.add(Round("Мурадова\n Алевтина \nАлександровна", R.drawable.ic8_alevtina))
        listRound.add(Round("Зарипова\n Дилноза \nАнваровна", R.drawable.ic9_dilnoza))
        listRound.add(
            Round(
                "Мухамедиева\n Дилдора \nКабуловна",
                R.drawable.ic10_dildora_muxamedeva
            )
        )

        listRound.add(Round("Туляганова\n Восила \nАбдусаторовна", R.drawable.ic11_vosila))
        listRound.add(Round("Акбарова\n Марғуба \nХамидовна", R.drawable.ic12_marguba))
        listRound.add(Round("Султонова\n Махбуба \nОдиловна", R.drawable.ic13_maxbuba))
        listRound.add(Round("Мирзаева\n Малика \nБахадировна", R.drawable.ic14_malika))
        listRound.add(Round("Анарова\n Шаҳзода \nАманбаевна", R.drawable.ic15_shaxzoda))
        listRound.add(Round("Усманова\n Наргиза \nБаҳтиёрбековна", R.drawable.ic16_nargiza))
        listRound.add(Round("Ганиева\n Барно \nИлхомовна", R.drawable.ic17_barno))
        listRound.add(Round("Очилова\n Озода \nОдиловна", R.drawable.ic18_ozoda))
        listRound.add(Round("Маликова\n Нодира \nТургуновна", R.drawable.ic19_nodira))
        listRound.add(Round("Отакузиева\n Зухра \nМаратдаевна", R.drawable.ic20_zuxra))

        listRound.add(Round("Хожиева\n Насиба \nЖумабаевна", R.drawable.ic21_nasiba))
        listRound.add(Round("Латипова\n Нодира \nХалимовна", R.drawable.ic22_nodira_latipova))
        listRound.add(
            Round(
                "Махкамова\n Надира \nРахмановна",
                R.drawable.ic23_nadira_maxkamova
            )
        )
        listRound.add(Round("Артикова\n Муаззам \nАхмедовна", R.drawable.ic24_muazzam))
        listRound.add(
            Round(
                "Таштемирова\n Надира \nНематиллаевна",
                R.drawable.ic25_nodira_toshtemirova
            )
        )
        listRound.add(Round("Хайдарова\n Мархамат \nЮнусовна", R.drawable.ic26_marxamat))
        listRound.add(Round("Ибрагимова\n Найира \nАнваровна", R.drawable.ic27_nayira))
        listRound.add(Round("Бекназарова\n Саида \nСафибуллаевна", R.drawable.ic28_saida))
        listRound.add(Round("Абдуллаева\n Симела \nХристофоровна ", R.drawable.ic29_simela))
        listRound.add(Round("Доспанова\n Дилара \nУракбаевна", R.drawable.ic30_dilara))

        listRound.add(Round("Зайнутдинова\n Динара \nТалатовна ", R.drawable.ic31_dinara))
        listRound.add(Round("Шарипова\n Азиза \nАбдуманаповна", R.drawable.ic32_aziza))
        listRound.add(Round("Мухитдинова\n Мунирахон \nРавшановна", R.drawable.ic33_munira))
        listRound.add(Round("Шахакимова\n Мавжуда \nТашполатовна", R.drawable.ic34_mavjuda))
        listRound.add(
            Round(
                "Рахматуллаева\n Махирахон \nФайзуллаевна",
                R.drawable.ic35_maxiraxon
            )
        )
        listRound.add(Round("Арипова\n Умида \nХайруллаевна", R.drawable.ic36_umida))
        listRound.add(Round("Алламуратова\n Замира \nЖумамуратовна", R.drawable.ic37_zamira))
        listRound.add(
            Round(
                "Абидова\n Шахноза \nБаходировна",
                R.drawable.ic38_shaxnoza_adibova
            )
        )
        listRound.add(Round("Марышева\n Лариса \nТимофеевна", R.drawable.ic39_larisa))
        listRound.add(Round("Абдужаппарова\n Мубарак \nБалтабаевна", R.drawable.ic40_muborak))

        listRound.add(Round("Сулейманова\n Галина \nНиколаевна ", R.drawable.ic41_galina))
        listRound.add(Round("Исмоилова\n Гулнора \nФайзуллаевна", R.drawable.ic42_gulnora))
        listRound.add(Round("Закирова\n Мадина \nРинатовна", R.drawable.ic43_madina))
        listRound.add(Round("Абдурашидова\n Камола \nТургунбаевна", R.drawable.ic44_kamola))
        listRound.add(Round("Сагдуллаева\n Дилбар \nШухратовна", R.drawable.ic45_dilbar))
        listRound.add(Round("Туленова\n Гулмира \nЖондорвна", R.drawable.ic46_gulmira))
        listRound.add(Round("Сададдинова\n Санобар \nСабировна", R.drawable.ic47_sanobar))
        listRound.add(Round("Исламова\n Одила \nАбдураимовна ", R.drawable.ic48_odila))


    }

    private fun loadOLimaData() {
        val listOlima = OlimaDatabase.GET.getOlimaDatabase().getOlimaDao()
            .getOlimaById(Cache.olimaPosition!! + 1)
        listOlima.forEach { she ->
            binding.apply {
                olimaName.text = she.name_full.trim()
                universitetInfo.text = she.universitet_name.trim()
                mutaxasislik.text = she.mutaxasislik.trim()
                ilmiyNashlar.text = she.ilmiy_nashrlar.toString().trim()
                umumiyMaqolalar.text = she.umumiy_maqolalar.toString().trim()
                jurnalChet.text = she.jurnal_chet.toString().trim()
                jurnalUzb.text = she.jurnal_uzb.toString().trim()
                konferensiyaChet.text = she.konferensiya_chet.toString().trim()
                konferensiyaUzb.text = she.konferensiya_uzb.toString().trim()
                patentSoni.text = she.patent_soni.toString().trim()
                ilmiyYonalish.text = she.ilmiy_yonalish.toString().trim()
                erishganYutuqlar.text = she.erishgan_yutuqlar.toString().trim()
                addressPhone.text = she.address_phone.toString().trim()
            }
        }
    }
}