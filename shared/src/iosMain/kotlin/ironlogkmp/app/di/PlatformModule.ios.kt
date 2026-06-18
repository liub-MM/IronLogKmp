package ironlogkmp.app.di

import ironlogkmp.app.data.database.db.getRoomDatabase
import ironlogkmp.app.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { getDatabaseBuilder() }

    single { getRoomDatabase(builder = get()) }
}