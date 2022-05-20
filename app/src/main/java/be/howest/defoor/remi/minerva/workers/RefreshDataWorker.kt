package be.howest.defoor.remi.minerva.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import be.howest.defoor.remi.minerva.Repositories.BookRepository
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.database.MinervaRoomDatabase
import retrofit2.HttpException

class RefreshDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME: String = "be.howest.defoor.remi.minerva.workers.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database: MinervaRoomDatabase = MinervaRoomDatabase.getDatabase(applicationContext)
        val userRepository: UserRepository = UserRepository(database.userDao())
        val bookRepository: BookRepository = BookRepository(userRepository, database.bookDao())

        try {
            bookRepository.refreshBooks()
        } catch (ex: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }

}
