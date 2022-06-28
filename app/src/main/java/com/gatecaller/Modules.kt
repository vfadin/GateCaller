package com.gatecaller

import com.gatecaller.data.repo.HomeRepo
import com.gatecaller.domain.repo.IHomeRepo
import com.gatecaller.ui.home.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepo(): IHomeRepo {
        return HomeRepo()
    }
}