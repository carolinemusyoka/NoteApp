package com.carolmusyoka.noteapp.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.databinding.ActivityMainBinding
import com.carolmusyoka.noteapp.note.notes.NotesViewModel
import com.carolmusyoka.noteapp.onboarding.OnBoardingActivity
import com.carolmusyoka.noteapp.onboarding.datastore.OnBoardingPrefManager
import com.carolmusyoka.noteapp.room.db.NotesDatabase
import com.carolmusyoka.noteapp.room.repo.NotesRepo
import com.carolmusyoka.noteapp.utils.factory.viewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val newsRepository by lazy { NotesRepo(NotesDatabase(this)) }
    private val viewModel: NotesViewModel by viewModels {
        viewModelFactory { NotesViewModel(this.application, newsRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        /**
         * Just so the viewModel doesn't get removed by the compiler, as it isn't used
         * anywhere here for now
         */
        viewModel

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return

        with(navHostFragment.navController) {
            appBarConfiguration = AppBarConfiguration(graph)
            setupActionBarWithNavController(this, appBarConfiguration)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}