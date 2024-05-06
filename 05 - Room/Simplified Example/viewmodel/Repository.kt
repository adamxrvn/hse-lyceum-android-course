package com.adammz.hse_lyceum_room_notes.viewmodel

import com.adammz.hse_lyceum_room_notes.model.Note
import com.adammz.hse_lyceum_room_notes.room.NoteDatabase

class Repository(private val db: NoteDatabase) {

    suspend fun upsert(note: Note) {
        db.dao.upsert(note)
    }

    suspend fun deleteNote(note: Note) {
        db.dao.deleteNote(note)
    }

    fun getAllNotesByDate() = db.dao.getAllNotesByDate()

    fun getAllNotesByTitle() = db.dao.getAllNotesByTitle()
}