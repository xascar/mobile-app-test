package dev.xascar.mobileapptest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.xascar.mobileapptest.data.RegistrationDAO
import dev.xascar.mobileapptest.data.TestAppDb


@InstallIn(SingletonComponent::class)
@Module
class LocalDbModule() {


    @Provides
    fun provideLocalDB(@ApplicationContext context: Context): TestAppDb {
        val database: TestAppDb by lazy {
            TestAppDb.getDatabase(context)
        }
        return database
    }

    @Provides
    fun provideRegistrationDao(
        database: TestAppDb
    ): RegistrationDAO {
        return database.registrationDao()
    }

}