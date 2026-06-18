package ironlogkmp.app.di

import ironlogkmp.app.data.database.db.getRoomDatabase
import ironlogkmp.app.data.db.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { getDatabaseBuilder(context = get()) }

    single { getRoomDatabase(builder = get()) }
}