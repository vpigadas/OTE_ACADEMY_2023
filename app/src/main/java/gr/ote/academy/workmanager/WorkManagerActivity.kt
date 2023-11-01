package gr.ote.academy.workmanager

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import gr.ote.academy.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    private var handler: Handler? = Handler(Looper.getMainLooper())

    private val workPool = mutableListOf<WorkRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .setRequiresDeviceIdle(false)
            .build()

        val inputData = workDataOf("name" to "Markos")

        val oneTimeWorkerRequest =
            OneTimeWorkRequestBuilder<OneTimeWorker>().setConstraints(constraints)
                .setInputData(inputData).build()
        workPool.add(oneTimeWorkerRequest)


        val periodicWorkerRequest =
            PeriodicWorkRequestBuilder<PeriodicWorker>(1, TimeUnit.HOURS).build()
        workPool.add(periodicWorkerRequest)

        WorkManager.getInstance(baseContext).enqueue(periodicWorkerRequest)

        GlobalScope.launch {
            repeat(100) {
                WorkManager.getInstance(baseContext).enqueue(oneTimeWorkerRequest)
                delay(TimeUnit.HOURS.toMillis(1))
            }

            repeatRequest(oneTimeWorkerRequest)

        }
    }

    override fun onResume() {
        super.onResume()

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .setRequiresDeviceIdle(false)
            .build()

        val inputData = workDataOf("name" to "Markos")

        val oneTimeWorkerRequest =
            OneTimeWorkRequestBuilder<OneTimeWorker>().setConstraints(constraints)
                .setInputData(inputData).build()
        workPool.add(oneTimeWorkerRequest)

        handler = Handler(Looper.getMainLooper())
        handler?.postDelayed(kotlinx.coroutines.Runnable {
            WorkManager.getInstance(baseContext).enqueue(oneTimeWorkerRequest)
        }, TimeUnit.HOURS.toMillis(1))
    }

    private suspend fun repeatRequest(request: WorkRequest) {
        WorkManager.getInstance(baseContext).enqueue(request)
        delay(TimeUnit.HOURS.toMillis(1))
        if (this@WorkManagerActivity.isFinishing) {
            return
        }
        repeatRequest(request)
    }

    override fun onStop() {
        handler?.removeCallbacksAndMessages(null)
        handler = null
        super.onStop()
    }

    override fun onDestroy() {
        workPool.forEach {
            WorkManager.getInstance(baseContext).cancelWorkById(it.id)
        }

        super.onDestroy()
    }
}