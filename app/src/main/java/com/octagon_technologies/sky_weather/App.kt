package com.octagon_technologies.sky_weather

import android.app.Application
import androidx.work.*
import com.octagon_technologies.sky_weather.widgets.WidgetRepo
import com.octagon_technologies.sky_weather.work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class App : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    private val widgetRepo by lazy { WidgetRepo(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        applicationScope.launch {
            setRecurringWork()
        }
    }

    private fun setRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWork>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        try {
            WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWork.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        } catch (e: IllegalStateException) {
            Timber.e(e, "Work manager wasn't initialized")
        }

        widgetRepo.updateAllWidgets {
            Timber.d("In App, updateAllWidgets.isSuccess is ${it.isSuccess}")
        }
    }
}