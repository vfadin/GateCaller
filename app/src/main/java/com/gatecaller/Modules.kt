package com.gatecaller

import android.content.Context
import com.gatecaller.data.database.ContactDatabase
import com.gatecaller.data.repo.HomeRepo
import com.gatecaller.data.repo.NewContactRepo
import com.gatecaller.domain.repo.IHomeRepo
import com.gatecaller.domain.repo.INewContactRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContactDatabase(
        @ApplicationContext appContext: Context
    ): ContactDatabase {
        return ContactDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideContactDao(database: ContactDatabase) = database.contactDao()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepo(database: ContactDatabase): IHomeRepo {
        return HomeRepo(database)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideNewContactRepo(database: ContactDatabase): INewContactRepo {
        return NewContactRepo(database)
    }
}