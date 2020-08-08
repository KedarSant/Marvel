package com.example.marvel

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.marvel.di.appModules
import com.example.marvel.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

class BaseApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(appModules)
        }
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch{
            setUpRecurringWork()
        }
    }

    private fun setUpRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

}