package com.yeyannaung.datamanagement

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yeyannaung.datamanagement.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnCreateAccount.setOnClickListener { navigateToFormActivity() }
    }

    private fun navigateToFormActivity() {
        startActivity(Intent(this, FormActivity::class.java))
    }
}