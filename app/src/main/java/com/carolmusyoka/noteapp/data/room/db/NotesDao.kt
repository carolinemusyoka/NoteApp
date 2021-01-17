package com.carolmusyoka.noteapp.data.room.db

import androidx.room.*
import com.carolmusyoka.noteapp.data.room.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    // insert notes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    // update notes
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotes(notes: Notes)

    // get all notes from db
    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Notes>>

    // delete notes from db
    @Delete
    suspend fun deleteNotes(notes: Notes)
}