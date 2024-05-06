package com.adammz.hse_lyceum_room_notes.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.adammz.hse_lyceum_room_notes.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {


    @Upsert
    suspend fun upsert(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY dateCreated")
     fun getAllNotesByDate(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY title")
     fun getAllNotesByTitle(): Flow<List<Note>>
}