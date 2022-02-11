package com.example.mausam.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.mausam.WeatherApplication
import com.example.mausam.databinding.FragmentSearchBinding
import com.example.mausam.viewModel.PlaceViewModel
import com.example.mausam.viewModel.PlaceViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

object SearchValue{
    var cityName :String = ""
}

class SearchFragment : Fragment() {

    private var _binding:FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.outlinedButton.setOnClickListener {
            if(binding.outlinedTextField.editText?.text!!.toString() != "") {
                SearchValue.cityName = binding.outlinedTextField.editText?.text!!.toString()
                view.findNavController()
                    .navigate(SearchFragmentDirections.actionSearchFragmentToBasicWeatherInfoFragment())
            }
            else{
                Toast.makeText(context,"Please type the city name",Toast.LENGTH_SHORT).show()
            }
        }
        binding.imageButton.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something...")
            try {
                activityResultLauncher.launch(intent)
            }catch (e:ActivityNotFoundException){
                Toast.makeText(view.context,"Device does not support speech recognition",Toast.LENGTH_LONG).show()
            }
        }
            activityResultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                    if (result!!.resultCode == RESULT_OK && result!!.data != null) {
                        val speechText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<Editable>
                        binding.outlinedTextField.editText?.text = speechText[0]
                    }
                }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}