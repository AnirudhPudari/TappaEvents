package com.eazybytes.tappaevents.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.eazybytes.tappaevents.adapter.EventRecyclerAdapter
import com.eazybytes.tappaevents.databinding.ActivityMainBinding
import com.eazybytes.tappaevents.viewmodel.MainViewModel
import com.google.android.material.color.DynamicColors
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterr: EventRecyclerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DynamicColors.applyToActivityIfAvailable(this)

        val strategy: FormatStrategy = PrettyFormatStrategy
            .newBuilder()
            .showThreadInfo(true)
            .methodCount(1)
            .methodOffset(5)
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(strategy))

        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })

//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val recyclerView = binding.recyclerView

        LinearSnapHelper().attachToRecyclerView(recyclerView)


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getEvents()
        }

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressBar.visibility = View.INVISIBLE
                if (it.error.isNotBlank()) {
                    binding.swipeRefresh.isRefreshing = false
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.textError.visibility = View.VISIBLE
                    binding.textError.text = it.error
                } else {
                    binding.textError.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                    binding.recyclerView.visibility = View.VISIBLE
                    adapterr = EventRecyclerAdapter(it.eventItems)
                    binding.recyclerView.adapter = adapterr
                }
            }

        }
    }
}