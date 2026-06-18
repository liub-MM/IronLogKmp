package ironlogkmp.app.di

import ironlogkmp.app.data.database.db.IronLogDatabase
import ironlogkmp.app.data.network.GeminiWorkoutParser
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single { GeminiWorkoutParser() }

    single { get<IronLogDatabase>().workoutDao }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule, sharedModule)
}