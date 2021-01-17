package com.carolmusyoka.noteapp.ui.note.add


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.databinding.AddNotesFragmentBinding
import com.carolmusyoka.noteapp.ui.note.notes.NotesViewModel
import com.carolmusyoka.noteapp.room.model.Notes
import com.carolmusyoka.noteapp.utils.toast

import kotlinx.android.synthetic.main.content_note_layout.*
import kotlinx.android.synthetic.main.item_post_notes.*
import java.util.*
import kotlin.properties.Delegates


class AddNotesFragment : Fragment(R.layout.add_notes_fragment) {
    private lateinit var date: Date
    private val viewModel: NotesViewModel by activityViewModels()
    private lateinit var _binding: AddNotesFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = AddNotesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getDate()

        characters.text="| 0 characters"
        noteET.addTextChangedListener(textWatcher)
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // save notes to db
            saveNotes.setOnClickListener {

                val (title, note) = getNoteContent()
                // check whether both title & desc is not empty
                when {
                    title.isEmpty() -> {
                        requireActivity().toast(getString(R.string.empty_title_msg))
                    }
                    note.isEmpty() -> {
                        requireActivity().toast(getString(R.string.empty_desc_msg))
                    }
                    else -> {
                        val character  = note.trim().length.toLong()
                        val dateToday  = currentDate.text.toString()
                        viewModel.insertNotes(
                                title,note,character,dateToday
                        ).also {
                            requireActivity().toast(getString(R.string.note_saved_msg)).also {
                                findNavController().navigate(R.id.action_addNotesFragment_to_notesFragment)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getNoteContent() = binding.noteLayout.let {
        Pair(
            it.titleET.text.toString(),
            it.noteET.text.toString()

        )
    }
    private val textWatcher= object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            characters.text= " | Characters "+s?.length.toString()

        }

    }
    private fun getDate() {
        date=Calendar.getInstance().time
        currentDate.text=date.toString()
    }
}