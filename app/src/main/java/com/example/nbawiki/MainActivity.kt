package com.example.nbawiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbawiki.ui.main.features.main.MainFragment
import kotlinx.android.synthetic.main.team_fragment.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Timber.plant(Timber.DebugTree())
    }

}
