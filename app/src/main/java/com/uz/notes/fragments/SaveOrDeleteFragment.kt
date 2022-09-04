package com.uz.notes.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import com.uz.notes.R
import com.uz.notes.activity.MainActivity
import com.uz.notes.databinding.BottomSheerLayoutBinding
import com.uz.notes.databinding.FragmentSaveOrDeleteBinding
import com.uz.notes.model.Note
import com.uz.notes.utils.hideKeyboard
import com.uz.notes.viewModel.NoteActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class SaveOrDeleteFragment : Fragment(R.layout.fragment_save_or_delete) {

    private lateinit var result: String
    lateinit var binding: FragmentSaveOrDeleteBinding
    lateinit var navController: NavController
    var note: Note? = null
    var color = -1
    val noteActivityViewModel: NoteActivityViewModel by activityViewModels()
    val currentData = SimpleDateFormat.getDateInstance().format(Date())
    val job = CoroutineScope(Dispatchers.Main)
    val args: SaveOrDeleteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment
            scrimColor = Color.TRANSPARENT
            duration = 300L
        }
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSaveOrDeleteBinding.bind(view)

        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity

        binding.backBtn.setOnClickListener {
            requireView().hideKeyboard()
            navController.popBackStack()
        }
        binding.lastEdited.text = getString(R.string.edited_on, SimpleDateFormat.getDateInstance().format(
            Date()
        ))

        binding.saveNote.setOnClickListener {
            saveNote()
        }

        try {
            binding.etNoteContent.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.bottomBar.visibility = View.VISIBLE
                    binding.etNoteContent.setStylesBar(binding.styleBar)
                } else {
                    binding.bottomBar.visibility = View.GONE
                }
            }
        } catch (e: Throwable) {
            Log.d("TAG", e.stackTraceToString())
        }

        binding.fabColorPicker.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                requireContext(),
                R.style.BottomSheetDialogTheme
            )
            val bottomSheetView: View = layoutInflater.inflate(
                R.layout.bottom_sheer_layout, null
            )
            with(bottomSheetDialog){
                setContentView(bottomSheetView)
                show()
            }
            val bottomSheetBinding = BottomSheerLayoutBinding.bind(bottomSheetView)
            bottomSheetBinding.apply {
                colorPicker.apply {
                    setSelectedColor(color)
                    setOnColorSelectedListener { value ->
                        color = value
                        binding.apply {
                            noteContentFragmentParent.setBackgroundColor(color)
                            toolbarFragmentNoteContent.setBackgroundColor(color)
                            bottomBar.setBackgroundColor(color)
                            activity.window.statusBarColor = color
                        }
                        bottomSheetBinding.bottomSheetParent.setCardBackgroundColor(color)
                    }
                }
                bottomSheetParent.setBackgroundColor(color)
            }
            bottomSheetView.post {
                bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

    }

    private fun saveNote() {
        if (binding.etNoteContent.text.toString().isEmpty() ||
            binding.etTitle.text.toString().isEmpty()
        ) {
            Toast.makeText(activity, "Some is empty", Toast.LENGTH_SHORT).show()
        } else {
            note = args.note
            when (note) {
                null -> {
                    noteActivityViewModel.saveNote(
                        Note(
                            0, binding.etTitle.text.toString(),
                            binding.etNoteContent.text.toString(), currentData, color
                        )
                    )
                    result = "Note Saved"
                    setFragmentResult(
                        "key",
                        bundleOf("bundleKey" to result)
                    )
                    navController.navigate(SaveOrDeleteFragmentDirections.actionSaveOrDeleteFragmentToNoteFragment())
                }
                else -> {

                }
            }
        }
    }
}