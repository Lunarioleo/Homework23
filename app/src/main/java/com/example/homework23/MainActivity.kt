package com.example.homework23


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.homework23.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getCurrency()
        viewModel.Uistate.observe(this){
            when (it) {
                is MyViewModel.UiState.Result ->{
                    val roundedValue = it.result?.data?.rateUsd?.toFloat()
                    binding.bitcoin.text = "${it.result?.data?.id}:  ${roundedValue}"
                }
                is MyViewModel.UiState.NoData -> {
                    binding.bitcoin.text = "NO DATA"
                    Toast.makeText(this, "Cannot receive data, try later", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
data class Bitcoin(
    val data: Data?
)
data class Data(
    val id: String,
    val rateUsd: String)