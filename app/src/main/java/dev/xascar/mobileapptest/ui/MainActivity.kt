package dev.xascar.mobileapptest.ui

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.xascar.mobileapptest.R
import dev.xascar.mobileapptest.databinding.ActivityMainBinding
import dev.xascar.mobileapptest.model.Data
import dev.xascar.mobileapptest.model.UserRegistration
import dev.xascar.mobileapptest.viewmodel.LoginViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchRegisterRequest()
        updateUIComponents()
    }

    private fun updateUIComponents() {

        loginViewModel.registration.observe(this){listOfInputsViewResponse ->
            val textView = TextView(this)
            textView.text = "Registration form"
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            binding.containerLayout.addView(textView)
            listOfInputsViewResponse.sortedBy {
                it.order
            }.forEach { view ->
                when(view.type){
                    "text" -> {
                        val editText = EditText(this)
                        editText.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        binding.containerLayout.addView(editText)
                    }
                    "select" -> {
                        val spinner = Spinner(this)
                        spinner.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        // Set spinner adapter and options as needed
                        binding.containerLayout.addView(spinner)
                    }
                    "checkbox" -> {
                        val checkBox = CheckBox(this)
                        checkBox.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        checkBox.text = "Check me"
                        binding.containerLayout.addView(checkBox)
                    }
                    else -> {
                        throw Exception("This view type is not supported yet: ${view.type}")
                    }
                }
            }
        }
        loginViewModel.message.observe(this){
            if (it.isNotEmpty()){
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun launchRegisterRequest() {
        loginViewModel.getRegistrationFields(
            UserRegistration(
                data = Data(newRegistration = true),
                login = "testaffiliateexternal",
                password = "testaffiliateexternal"
            )
        )
    }
}