package ironlogkmp.app.di

import ironlogkmp.app.data.database.db.IronLogDatabase
import ironlogkmp.app.data.network.GeminiWorkoutParser
import ironlogkmp.app.data.repository.WorkoutRepositoryImpl
import ironlogkmp.app.domain.repository.WorkoutRepository
import ironlogkmp.app.domain.usecases.ProcessAndSaveWorkoutUseCase
import ironlogkmp.app.domain.usecases.SyncWorkoutToCloudUseCase
import ironlogkmp.app.presentation.viewmodel.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single { GeminiWorkoutParser() }

    single { get<IronLogDatabase>().workoutDao }

    single<WorkoutRepository> {
        WorkoutRepositoryImpl(
            geminiWorkoutParser = get(),
            dao = get()
        )
    }

    factory { ProcessAndSaveWorkoutUseCase(get(), get()) }
    factory { SyncWorkoutToCloudUseCase(get()) }

    factory {
        HomeViewModel(
            processAndSaveWorkoutUseCase = get(),
        )
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule, sharedModule)
}