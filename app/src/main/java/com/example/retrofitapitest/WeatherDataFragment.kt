package com.example.retrofitapitest

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapitest.api.WeatherItem

private const val TAG = "WEATHERDATAFRAGMENT"

class WeatherDataFragment: Fragment() {


    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var weatherDataViewModel: WeatherDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherDataViewModel = ViewModelProvider(this).get(WeatherDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather_data, container, false)
        weatherRecyclerView = view.findViewById(R.id.weather_recycler_view)
        weatherRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.weatherItemLiveData.observe(
            viewLifecycleOwner,
            Observer { weatherItems ->
                Log.d(TAG, "Gallery Items from View Model $weatherItems")
                weatherRecyclerView.adapter = WeatherAdapter(weatherItems)
            }
        )
    }

    private class WeatherHolder(itemTextView: TextView): RecyclerView.ViewHolder(itemTextView){
        val bind: (String) -> Unit = itemTextView::setText
    }
    private inner class WeatherAdapter(private val weatherItem: WeatherItem): RecyclerView.Adapter<WeatherHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
            val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.weather_item, parent, false) as TextView
            return WeatherHolder(view)
        }

        override fun getItemCount(): Int = 1

        override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
            holder.bind(weatherItem.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherDataFragment()
    }


}