package com.example.assignment_64

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_64.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load fade-in animation for the FAB
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.fab.startAnimation(fadeIn)

        // FAB click listener
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "FAB Clicked!", Snackbar.LENGTH_LONG)
                .setAction("Next") {
                    val intent = Intent(this,MainActivity2::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                }.show()
        }

    }
}