package com.uz.notes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uz.notes.R
import com.uz.notes.databinding.ActivityMainBinding
import com.uz.notes.db.NoteDatabase
import com.uz.notes.repository.NoteRepository
import com.uz.notes.viewModel.NoteActivityViewModel
import com.uz.notes.viewModel.NoteActivityViewModelFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var noteActivityViewModel: NoteActivityViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)

        try {
            setContentView(binding.root)
            val noteRepository = NoteRepository(NoteDatabase(this))
            val noteActivityViewModelFactory = NoteActivityViewModelFactory(noteRepository)
            noteActivityViewModel = ViewModelProvider(
                this,
                noteActivityViewModelFactory
            )[NoteActivityViewModel::class.java]
        } catch (e: Exception) {
            Log.d("TAG", "Error")
        }
    }
}