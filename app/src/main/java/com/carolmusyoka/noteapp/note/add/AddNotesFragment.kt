package com.carolmusyoka.noteapp.note.add


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.databinding.AddNotesFragmentBinding
import com.carolmusyoka.noteapp.note.notes.NotesViewModel
import com.carolmusyoka.noteapp.utils.toast


class AddNotesFragment : Fragment(R.layout.add_notes_fragment) {

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
                        viewModel.insertNotes(title, note).also {
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
}