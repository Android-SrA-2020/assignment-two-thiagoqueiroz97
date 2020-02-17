package com.example.assignment2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.assignment2.databinding.FragmentCheatBinding
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class CheatFragment : Fragment() {

    private lateinit var binding: FragmentCheatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cheat, container, false)

        val data = CheatFragmentArgs.fromBundle(arguments!!)

        binding.apply {

            questionTxt.text = data.question

            btnCheat.setOnClickListener {
                binding.answerTxt.text = data.answer
            }

            btnCancel.setOnClickListener {
                    view:View-> view.findNavController().navigate(R.id.action_cheatFragment_to_mainFragment)
            }
        }
        return binding.root
    }

}
