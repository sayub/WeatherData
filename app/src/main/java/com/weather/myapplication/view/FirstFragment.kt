package com.weather.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.weather.myapplication.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: WeatherViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.collect {
                when(it) {
                    is WeatherState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is WeatherState.Loaded -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.cityTextView.text = "City: ${it.data?.name}"
                        binding.temperatureTextView.text = "Temperature: ${it.data?.main?.temp}"
                        Glide.with(requireContext())
                            .load("http://openweathermap.org/img/w/${it.data?.weather?.getOrNull(0)?.icon}.png")
                            .into(binding.weatherIconImageVIew);
                    }
                    is WeatherState.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        Snackbar.make(view, it.message ?: "", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.loadWeather()

        binding.buttonGetWeather.setOnClickListener {
            viewModel.loadWeather()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}