package be.howest.defoor.remi.minerva

import android.app.Application
import androidx.work.*
import be.howest.defoor.remi.minerva.Repositories.BookRepository
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.database.MinervaRoomDatabase
import be.howest.defoor.remi.minerva.workers.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MinervaApplication: Application() {

    val database: MinervaRoomDatabase by lazy { MinervaRoomDatabase.getDatabase(this) }
    val userRepository: UserRepository by lazy {  UserRepository(database.userDao()) }
    val bookRepository: BookRepository by lazy { BookRepository(userRepository, database.bookDao()) }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val workManager: WorkManager = WorkManager.getInstance(applicationContext)

        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()

        val refreshDataRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            refreshDataRequest
        )
    }

}
