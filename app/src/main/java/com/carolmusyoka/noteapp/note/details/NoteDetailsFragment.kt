package com.carolmusyoka.noteapp.note.details

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.carolmusyoka.noteapp.room.model.Notes
import kotlinx.android.synthetic.main.content_note_layout.*
import kotlinx.android.synthetic.main.item_post_notes.*
import java.util.*

class NoteDetailsFragment : Fragment(R.layout.fragment_notes_details) {
    private lateinit var date: Date
    private lateinit var  getNote: Notes
    private lateinit var context1: Context
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
        getDate()
        characters.text="| 0 characters"
        noteET.addTextChangedListener(textWatcher)
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
                characters.text="${notes.character}"
                val character = note.trim().length.toLong()

                val dateToday  = currentDate.text.toString()
                viewModel.updateNotes(
                        id,title
                        ,note,character,dateToday

                ).also {
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
                viewModel.deleteNotes(
                        args.notes.id, args.notes.title, args.notes.description,
                        args.notes.character,args.notes.date
                )
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
        it.currentDate.text.toString()
        it.characters.text.toString().length.toLong()
        Pair(
            it.titleET.text.toString(),
            it.noteET.text.toString(),
        )
    }
    private val textWatcher= object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            characters.text= " | Character "+s?.length.toString()

        }

    }
    private fun getDate() {
        date= Calendar.getInstance().time
        currentDate.text=date.toString()
    }
}