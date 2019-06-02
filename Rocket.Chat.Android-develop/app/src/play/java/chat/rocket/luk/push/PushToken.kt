package chat.rocket.luk.push

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import chat.rocket.luk.push.worker.TokenRegistrationWorker

fun refreshPushToken() {
    val constraint =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    val work = OneTimeWorkRequestBuilder<TokenRegistrationWorker>()
            .setConstraints(constraint)
            .build()

    // Schedule a job since we are using network...
    WorkManager.getInstance().enqueue(work)
}