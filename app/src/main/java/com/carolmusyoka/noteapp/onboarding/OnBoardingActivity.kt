package com.carolmusyoka.noteapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carolmusyoka.noteapp.R
import com.carolmusyoka.noteapp.main.MainActivity
import com.carolmusyoka.noteapp.onboarding.datastore.OnBoardingPrefManager
import kotlinx.android.synthetic.main.onboarding_view.*

class OnBoardingActivity : AppCompatActivity() {
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
        OnBoardingPrefManager(this).firstTimeLaunch
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}