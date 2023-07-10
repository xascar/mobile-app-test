package dev.xascar.mobileapptest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.xascar.mobileapptest.domain.RegistrationFormDomain

@Database(entities = [RegistrationFormDomain::class], version = 1, exportSchema = false)
abstract class TestAppDb : RoomDatabase() {
    abstract fun registrationDao(): RegistrationDAO
    companion object {
        @Volatile
        private var INSTANCE: TestAppDb? = null

        fun getDatabase(context: Context): TestAppDb {

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    TestAppDb::class.java,
                    "soccer_world_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}