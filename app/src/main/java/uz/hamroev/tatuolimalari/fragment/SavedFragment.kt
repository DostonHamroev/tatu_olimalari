package uz.hamroev.tatuolimalari.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.hamroev.tatuolimalari.R
import uz.hamroev.tatuolimalari.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {


    lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)



        return binding.root
    }


}