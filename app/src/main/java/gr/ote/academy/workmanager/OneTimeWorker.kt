package gr.ote.academy.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class OneTimeWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result = when (communicateWithHost()) {
        true -> Result.success()
        false -> Result.failure()
    }

    private fun communicateWithHost(): Boolean {
        return true
    }

}