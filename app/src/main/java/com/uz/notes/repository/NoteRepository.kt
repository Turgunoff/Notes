package com.uz.notes.repository

import com.uz.notes.db.NoteDatabase
import com.uz.notes.model.Note


/**
 * Created by Eldor Turgunov on 04.09.2022.
 * Notes
 * eldorturgunov777@gmail.com
 */

class NoteRepository(private val db: NoteDatabase) {

    fun getNote() = db.getNoteDao().getAllNote()

    fun searchNote(query: String) = db.getNoteDao().searchNote(query)

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)

    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
}