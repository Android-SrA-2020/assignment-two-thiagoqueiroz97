package com.example.assignment2


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.assignment2.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true))

    private var questionIndex = 0

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        setHasOptionsMenu(true)

       return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding.apply {

            nextButton.setOnClickListener {

                    questionIndex = (questionIndex + 1) % 20
                    binding.questionText.setText(questionBank[questionIndex].resourceId)

            }

            prevButton.setOnClickListener {
                if(questionIndex != 0){
                    questionIndex = (questionIndex - 1) % 20
                    binding.questionText.setText(questionBank[questionIndex].resourceId)
                }
            }

            trueBtn.setOnClickListener {
                if(questionBank.get(questionIndex).answer == true){
                    val toast = Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else
                {
                    val toast = Toast.makeText(activity, "Incorrect...", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            falseBtn.setOnClickListener {
                if(questionBank.get(questionIndex).answer == false){
                    val toast = Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else
                {
                    val toast = Toast.makeText(activity, "Incorrect...", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            cheatBtn.setOnClickListener {

                val questiontext = getString(questionBank[questionIndex].resourceId)

                navController.navigate(MainFragmentDirections.actionMainFragmentToCheatFragment(
                    questiontext,
                    questionBank[questionIndex].answer.toString()
                ))
            }

        }

        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("questionIndex", 0)
            binding.questionText.setText(questionBank[questionIndex].resourceId)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}
