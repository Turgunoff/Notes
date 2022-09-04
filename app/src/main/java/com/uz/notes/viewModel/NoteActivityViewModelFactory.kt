package com.uz.notes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uz.notes.model.Note
import com.uz.notes.repository.NoteRepository


/**
 * Created by Eldor Turgunov on 04.09.2022.
 * Notes
 * eldorturgunov777@gmail.com
 */

class NoteActivityViewModelFactory(private val repository: NoteRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteActivityViewModel(repository) as T
    }

}