package com.octagon_technologies.sky_weather.ui.current_forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octagon_technologies.sky_weather.R
import com.octagon_technologies.sky_weather.databinding.CurrentForecastFragmentBinding
import com.octagon_technologies.sky_weather.domain.getFormattedFeelsLike
import com.octagon_technologies.sky_weather.domain.getFormattedTemp
import com.octagon_technologies.sky_weather.utils.StatusCode
import com.octagon_technologies.sky_weather.utils.Theme
import com.octagon_technologies.sky_weather.utils.addToolbarAndBottomNav
import com.octagon_technologies.sky_weather.utils.changeSystemNavigationBarColor
import com.octagon_technologies.sky_weather.utils.getStringResource
import com.octagon_technologies.sky_weather.utils.getWeatherIconFrom
import com.octagon_technologies.sky_weather.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentForecastFragment : Fragment() {

    private lateinit var binding: CurrentForecastFragmentBinding
    private val viewModel by viewModels<CurrentForecastViewModel>()
//    private val adHelper by adHelpers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentForecastFragmentBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpStatusCode()
        setUpSwipeToRefresh()
        setUpHourlyForecastPreview()
        setUpSeeMoreButton()
        setUpLiveData()
//        setUpAd()
    }

    private fun setUpLiveData() {
        viewModel.currentForecast.observe(viewLifecycleOwner) { currentForecast ->
            if (currentForecast != null) {
                binding.mainTemp.text = currentForecast.getFormattedTemp()
                binding.mainRealfeelDisplayText.text = currentForecast.getFormattedFeelsLike()
            }
        }
    }

    private fun setUpSeeMoreButton() {
        binding.seeMoreBtn.setOnClickListener {
            val currentForecast = viewModel.currentForecast.value
            if (currentForecast == null) {
                Toast.makeText(requireContext(), "No weather data available", Toast.LENGTH_SHORT)
                    .show()
            } else {
                findNavController()
                    .navigate(
                        CurrentForecastFragmentDirections
                            .actionCurrentForecastFragmentToSeeMoreFragment(currentForecast)
                    )
            }
        }
    }

    private fun setUpSwipeToRefresh() {
        binding.swipeToRefreshLayout.setOnRefreshListener { viewModel.refreshWeatherForecast() }

        viewModel.isRefreshing.observe(viewLifecycleOwner) { isRefreshing ->
            binding.swipeToRefreshLayout.isRefreshing = isRefreshing == true
        }
    }

    private fun setUpHourlyForecastPreview() {
        viewModel.oneHourForecast.observe(viewLifecycleOwner) { oneHourForecast ->
            binding.oneHourTempText.text = oneHourForecast.getFormattedTemp()
            binding.oneHourFeelslikeDisplayText.text = oneHourForecast.getFormattedFeelsLike()
            binding.oneHourWeatherImage.getWeatherIconFrom(oneHourForecast?.weatherCode)
        }
        viewModel.sixHourForecast.observe(viewLifecycleOwner) { sixHourForecast ->
            binding.sixHourTempText.text = sixHourForecast.getFormattedTemp()
            binding.sixHourFeelslikeDisplayText.text = sixHourForecast.getFormattedFeelsLike()
            binding.sixHourWeatherImage.getWeatherIconFrom(sixHourForecast?.weatherCode)
        }
        viewModel.twentyFourHourForecast.observe(viewLifecycleOwner) { twentyFourHourForecast ->
            binding.twentyFourTempText.text = twentyFourHourForecast.getFormattedTemp()
            binding.twentyFourFeelslikeDisplayText.text =
                twentyFourHourForecast.getFormattedFeelsLike()
            binding.twentyFourWeatherImage.getWeatherIconFrom(twentyFourHourForecast?.weatherCode)
        }
    }

    private fun setUpStatusCode() {
        viewModel.statusCode.observe(viewLifecycleOwner) {
            val message = when (it ?: return@observe) {
                StatusCode.Success -> return@observe
                StatusCode.NoNetwork -> getStringResource(R.string.no_network_availble_plain_text)
                StatusCode.ApiLimitExceeded -> getStringResource(R.string.api_limit_exceeded_plain_text)
            }

            showLongToast(message)
        }
    }

//    private fun setUpAd() {
//        adHelper.loadAd(binding.adView) {
//            Timber.d("Load ad listener return $it")
//            // If it failed in onAdFailedToLoad(), hide the ad view
//            if (!it) {
//                binding.adView.root.visibility = View.GONE
//            }
//        }
//    }

    override fun onStart() {
        super.onStart()
        viewModel.theme.observe(viewLifecycleOwner) {
            addToolbarAndBottomNav(it)
            changeSystemNavigationBarColor(
                if (it == Theme.LIGHT) R.color.light_theme_blue
                else R.color.dark_theme_blue
            )
        }
    }
}