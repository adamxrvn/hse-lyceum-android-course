package com.adammz.hse_lyceum_room_notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adammz.hse_lyceum_room_notes.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: Repository) : ViewModel() {

    fun getNotes() = isSortedByDate.flatMapLatest { sortedByDate ->
        if (sortedByDate) {
            repository.getAllNotesByDate()
        } else {
            repository.getAllNotesByTitle()
        }
    }.asLiveData(viewModelScope.coroutineContext)

    private val isSortedByDate = MutableStateFlow(true)


    fun upsert(note: Note) {
        viewModelScope.launch { repository.upsert(note) }
    }

    fun delete(note: Note) {
        viewModelScope.launch { repository.deleteNote(note) }
    }

    fun sortNotes() {
        isSortedByDate.value = !isSortedByDate.value
    }

}