package dev.xascar.mobileapptest.ui

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import dev.xascar.mobileapptest.databinding.ActivityMainBinding
import dev.xascar.mobileapptest.viewmodel.LoginViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUIComponents()
    }

    private fun updateUIComponents() {

        val textView = MaterialTextView(this)
        textView.text = "Registration form"
        textView.setTextAppearance(android.R.style.TextAppearance_DialogWindowTitle)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        binding.containerLayout.addView(textView)

        loginViewModel.registration.observe(this) { listOfInputsViewResponse ->


            listOfInputsViewResponse.sortedBy {
                it.order
            }.forEach { view ->
                when (view.type) {
                    "text" -> {
                        val textInputLayout = TextInputLayout(this)
                        textInputLayout.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        val textInputEditText = TextInputEditText(this)
                        textInputEditText.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        textInputEditText.maxLines = 1

                        textInputLayout.addView(textInputEditText)

                        textInputLayout.visibility = if (view.visible) View.VISIBLE else View.INVISIBLE
                        textInputLayout.hint = view.description

                        view.maxLength?.let {
                            textInputLayout.counterMaxLength = it
                            textInputEditText.filters = arrayOf(InputFilter.LengthFilter(it))
                        }

                        textInputEditText.setOnFocusChangeListener { _, hasFocus ->
                            if (!hasFocus){
                                if (view.regex.isNotEmpty() && textInputEditText.text.toString().isNotEmpty()){
                                    if (!textInputEditText.text.toString().matches(view.regex.toRegex())) {
                                        textInputLayout.error = "Invalid input"
                                        loginViewModel.updateMessage("Please verify input data on ${view.description}")
                                    } else {
                                        loginViewModel.updateMessage("")
                                        textInputLayout.error = null
                                    }
                                }
                            }
                        }

                        binding.containerLayout.addView(textInputLayout)
                    }

                    "select" -> {

                        val textInputLayout = TextInputLayout(ContextThemeWrapper(this, com.google.android.material.R.style.Widget_Material3_TextInputLayout_FilledBox_ExposedDropdownMenu))
                        textInputLayout.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        textInputLayout.hint = "Select"

                        val autoCompleteTextView = AutoCompleteTextView(this)
                        autoCompleteTextView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        autoCompleteTextView.inputType = InputType.TYPE_NULL
                        autoCompleteTextView.setAdapter(
                            ArrayAdapter(
                                this,
                                android.R.layout.simple_list_item_1,
                                view.values
                            )
                        )

                        textInputLayout.addView(autoCompleteTextView)

                        textInputLayout.visibility = if (view.visible) View.VISIBLE else View.INVISIBLE

                        view.maxLength?.let {
                            textInputLayout.counterMaxLength = it
                            autoCompleteTextView.filters = arrayOf(InputFilter.LengthFilter(it))
                        }


                        binding.containerLayout.addView(textInputLayout)
                    }

                    "checkbox" -> {
                        val checkBox = MaterialCheckBox(this)
                        checkBox.text = view.order.toString()
                        checkBox.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        checkBox.text = "Check me"

                        checkBox.visibility = if (view.visible) View.VISIBLE else View.INVISIBLE
                        binding.containerLayout.addView(checkBox)
                    }

                    else -> {
                        throw Exception("This view type is not supported yet: ${view.type}")
                    }
                }
            }


            val btnRegister = MaterialButton(this)
            btnRegister.text = "Register"
            btnRegister.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            btnRegister.setOnClickListener {
                clearFocus(binding.containerLayout)
                if (loginViewModel.message.value.toString().isNotEmpty()){
                    Toast.makeText(this,loginViewModel.message.value.toString(),Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Registering user...",Toast.LENGTH_SHORT).show()
                }

            }

            binding.containerLayout.addView(btnRegister)
        }
        loginViewModel.message.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun clearFocus(view: View) {
        view.clearFocus()
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                clearFocus(child)
            }
        }
    }
}