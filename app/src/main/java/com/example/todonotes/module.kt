package com.example.todonotes

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object module {

        @Provides
    fun ProvideDao(roomDB: RoomDB):Dao{
     return roomDB.dao()

    }

//    @Singleton
//    @Provides
//    fun ProvideRepo(roomDB: RoomDB): Repo {
//        return Repo(roomDB)
//    }

        @Singleton
        @Provides
        fun providedatabase( context: Application): RoomDB {
            return Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                "room"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
