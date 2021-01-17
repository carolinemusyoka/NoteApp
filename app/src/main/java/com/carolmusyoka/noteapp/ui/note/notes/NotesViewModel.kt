package com.carolmusyoka.noteapp.ui.note.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.datastore.UIModePreference
import com.carolmusyoka.noteapp.room.model.Notes
import com.carolmusyoka.noteapp.room.repo.NotesRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NotesViewModel(application: Application, private val notesRepo: NotesRepo) :
    AndroidViewModel(application) {

    // DataStore
    private val uiDataStore = UIModePreference(application)

    // get UI mode
    val getUIMode = uiDataStore.uiMode

    // save UI mode
    fun saveToDataStore(isNightMode: Boolean) {
        viewModelScope.launch(IO) {
            uiDataStore.saveToDataStore(isNightMode)
        }
    }


    // save notes
    fun insertNotes(taskName: String, taskDesc: String, taskCharacter:Long,taskDate:String) = viewModelScope.launch {
        val notes = Notes(
                title = taskName,
                description = taskDesc,
                character = taskCharacter,
                date = taskDate
        )
        notesRepo.insert(notes)
    }

    // save notes
    fun updateNotes(id: Int, taskName: String, taskDesc: String, taskCharacter:Long,taskDate:String ) = viewModelScope.launch {
        val notes = Notes(
                id = id,
                title = taskName,
                description = taskDesc,
                character = taskCharacter,
                date = taskDate

        )
        notesRepo.update(notes)
    }

    // get saved notes
    fun getSavedNotes() = notesRepo.getSavedNotes().asLiveData()


    // delete notes
    fun deleteNotes(taskID: Int, taskName: String, taskDesc: String, taskCharacter: Long, taskDate: String) = viewModelScope.launch {
        val notes = Notes(
                id = taskID,
                title = taskName,
                description = taskDesc,
                character = taskCharacter,
                date = taskDate
        )
        notesRepo.deleteNotes(notes)
    }

}