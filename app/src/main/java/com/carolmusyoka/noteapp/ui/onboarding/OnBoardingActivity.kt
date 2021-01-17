package com.carolmusyoka.noteapp.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.ui.main.MainActivity
import com.carolmusyoka.noteapp.ui.onboarding.datastore.OnBoardingPrefManager
import kotlinx.android.synthetic.main.onboarding_view.*
import kotlinx.coroutines.launch

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onboardingPreferences: OnBoardingPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)


        startBtn.setOnClickListener {
            navigateToMain()
        }
        skipBtn.setOnClickListener {
            navigateToMain()

        }

    }

    private fun navigateToMain(){
        OnBoardingPrefManager(this).setFirstTimeLaunch(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}