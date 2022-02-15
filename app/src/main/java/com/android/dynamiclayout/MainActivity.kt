package com.android.dynamiclayout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.dynamiclayout.databinding.ActivityMainBinding
import com.android.dynamiclayout.model.Uidata
import com.android.dynamiclayout.util.MyApplication
import com.android.dynamiclayout.viewmodel.MainViewModel
import com.android.dynamiclayout.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val uidataList = ArrayList<Uidata>()
    private lateinit var btnLabel: Button
    private lateinit var editLabel: EditText
    private var editPos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setUpObservers()
    }

    private fun setUpViewModel() {
        val repository = (application as MyApplication).mainRepository

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
    }

    private fun setUpObservers() {
        viewModel.users.observe(this) {
            binding.customLayout.removeAllViews()
            uidataList.clear()
            uidataList.addAll(it.uidata)
            for (uiSize in 0 until uidataList.size) {
                when (uidataList[uiSize].uitype) {
                    "label" -> {
                        val view: View = layoutInflater.inflate(R.layout.item_layout, null)
                        val tvLabel = view.findViewById<TextView>(R.id.tvLabel)
                        tvLabel.text = uidataList[uiSize].value
                        binding.customLayout.addView(view)
                    }
                    "edittext" -> {
                        val view: View = layoutInflater.inflate(R.layout.edit_layout, null)
                        editLabel = view.findViewById(R.id.editLabel)
                        editLabel.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int
                            ) {
//                                Will Implement later
                            }

                            override fun onTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int
                            ) {
//                                Will Implement later
                            }

                            override fun afterTextChanged(editable: Editable?) {
                                uidataList[uiSize].editTextData = editable.toString()
                            }
                        })
                        editLabel.hint = uidataList[uiSize].hint
                        editPos = binding.customLayout.childCount
                        binding.customLayout.addView(view)
                    }
                    else -> {
                        val view: View = layoutInflater.inflate(R.layout.btn_layout, null)
                        btnLabel = view.findViewById(R.id.btnLabel)
                        btnLabel.text = uidataList[uiSize].value
                        binding.customLayout.addView(view)
                    }
                }
            }
//            setUpClickListener()
        }
    }
}