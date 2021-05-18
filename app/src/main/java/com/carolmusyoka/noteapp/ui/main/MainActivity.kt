package com.carolmusyoka.noteapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.databinding.ActivityMainBinding
import com.carolmusyoka.noteapp.data.datastore.UIModePreference
import com.carolmusyoka.noteapp.ui.note.notes.NotesViewModel
import com.carolmusyoka.noteapp.ui.onboarding.OnBoardingActivity
import com.carolmusyoka.noteapp.ui.onboarding.datastore.OnBoardingPrefManager
import com.carolmusyoka.noteapp.data.room.db.NotesDatabase
import com.carolmusyoka.noteapp.data.room.repo.NotesRepo
import com.carolmusyoka.noteapp.utils.factory.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var onboardingPreferences: OnBoardingPrefManager
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val newsRepository by lazy { NotesRepo(NotesDatabase(this)) }
    private val viewModel: NotesViewModel by viewModels {
        viewModelFactory { NotesViewModel(this.application, newsRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        if (OnBoardingPrefManager(this).isFirstTimeLaunch()){
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }


        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        /**
         * Just so the viewModel doesn't get removed by the compiler, as it isn't used
         * anywhere here for now
         */


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
