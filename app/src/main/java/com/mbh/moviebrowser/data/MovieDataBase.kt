package com.mbh.moviebrowser.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mbh.moviebrowser.data.accessories.LongListConverter
import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel

@Database(
    entities = [GenreDataModel::class, MovieDataModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LongListConverter::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private const val DB_NAME = "AgoraDB"
        private var INSTANCE: MovieDataBase? = null
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }

        /**
         * Creates a Singleton _instance of this class
         *
         * @param application
         * @return
         */
        fun getInstance(application: Application): MovieDataBase {
            if (INSTANCE == null) {
                synchronized(MovieDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(application, MovieDataBase::class.java, DB_NAME)
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}