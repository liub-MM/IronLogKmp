package ironlogkmp.app

import ironlogkmp.app.data.database.db.IronLogDatabase

interface RoomDatabaseConstructor {
    fun create(): IronLogDatabase
}