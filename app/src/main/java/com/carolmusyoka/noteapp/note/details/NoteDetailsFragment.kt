package com.carolmusyoka.noteapp.note.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.databinding.FragmentNotesDetailsBinding
import com.carolmusyoka.noteapp.note.notes.NotesViewModel

class NoteDetailsFragment : Fragment(R.layout.fragment_notes_details) {

    private val viewModel: NotesViewModel by activityViewModels()
    private val args: NoteDetailsFragmentArgs by navArgs()

    private lateinit var _binding: FragmentNotesDetailsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNotesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)


        // receiving bundles here
        val notes = args.notes
        val id = notes.id

        with(binding) {
            noteLayout.titleET.setText(notes.title)
            noteLayout.noteET.setText(notes.description)

            // update notes on click
            updateBtnSaveNotes.setOnClickListener {
                val (title, note) = getNoteContent()

                viewModel.updateNotes(id, title, note).also {
                    findNavController().navigate(R.id.action_notesDetailsFragment_to_notesFragment)
                    Toast.makeText(activity,
                        getString(R.string.note_updated_msg),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        return when (item.itemId) {

            R.id.action_delete -> {
                viewModel.deleteNotes(args.notes.id, args.notes.title, args.notes.description)
                findNavController().navigateUp()
                true
            }

            R.id.action_share -> {
                val shareMsg = getString(
                    R.string.share_message,
                    args.notes.title,
                    args.notes.description
                )

                val intent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setType("text/plain")
                    .setText(shareMsg)
                    .intent

                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getNoteContent() = binding.noteLayout.let {
        Pair(
            it.titleET.text.toString(),
            it.noteET.text.toString()
        )
    }
}