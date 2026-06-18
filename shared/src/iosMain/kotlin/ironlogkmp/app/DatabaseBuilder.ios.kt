package ironlogkmp.app


import androidx.room.Room
import androidx.room.RoomDatabase
import ironlogkmp.app.data.database.db.IronLogDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<IronLogDatabase> {
    val dbFilePath = NSHomeDirectory() + "/ironlog.db"

    return Room.databaseBuilder<IronLogDatabase>(
        name = dbFilePath
    )
}
