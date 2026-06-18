package ironlogkmp.app.data.db.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ironlogkmp.app.data.database.db.IronLogDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<IronLogDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("ironlog.db")

    return Room.databaseBuilder<IronLogDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
