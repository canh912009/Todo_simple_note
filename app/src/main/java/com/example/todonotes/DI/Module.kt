package com.example.todonotes.DI

import android.app.Application
import androidx.room.Room
import com.example.todonotes.DataBase.Dao
import com.example.todonotes.DataBase.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    fun ProvideDao(roomDB: RoomDB): Dao {
        return roomDB.dao()

    }


    @Singleton
    @Provides
    fun providedatabase(context: Application): RoomDB {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "room"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
