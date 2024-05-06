package com.adammz.hse_lyceum_room_notes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adammz.hse_lyceum_room_notes.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val dao: NoteDao
}