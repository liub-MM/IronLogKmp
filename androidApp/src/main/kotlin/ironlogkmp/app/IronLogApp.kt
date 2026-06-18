package ironlogkmp.app

import android.app.Application
import android.content.Context
import ironlogkmp.app.di.initKoin
import org.koin.dsl.module

class IronLogApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            modules(
                module {
                    single<Context> { this@IronLogApp }
                }
            )
        }
    }
}