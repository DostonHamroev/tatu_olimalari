package uz.hamroev.tatuolimalari.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.hamroev.tatuolimalari.R
import uz.hamroev.tatuolimalari.adapter.RoundAdapter
import uz.hamroev.tatuolimalari.databinding.FragmentHomeBinding
import uz.hamroev.tatuolimalari.model.Round

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var listRound: ArrayList<Round>
    lateinit var roundAdapter: RoundAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        loadRound()
        setRoundAdapter()






        return binding.root
    }

    private fun setRoundAdapter() {
        roundAdapter = RoundAdapter(
            binding.root.context,
            listRound,
            object : RoundAdapter.OnRoundClickListener {
                override fun onCLick(round: Round, position: Int) {

                }
            })
        binding.rvRound.adapter = roundAdapter
    }

    private fun loadRound() {
        listRound = ArrayList()
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
        listRound.add(Round("Saida\nBeknazarova", R.drawable.ic_launcher_background))
    }
}