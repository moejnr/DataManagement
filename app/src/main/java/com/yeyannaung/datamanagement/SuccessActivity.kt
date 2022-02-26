package com.yeyannaung.datamanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yeyannaung.datamanagement.databinding.ActivitySuccessBinding
import com.yeyannaung.datamanagement.model.User

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val user = intent.getSerializableExtra("user") as User
        user.let {
            binding.tvData.text = "First Name - ${user.firstName}\nLast Name - ${user.lastName}\n" +
                    "Email - ${user.email}\nDate of Birth - ${user.dob}\nGender - ${user.gender}\n" +
                    "Nationality - ${user.nationality}\nCountry - ${user.country}\nPhone - ${user.phone}"
        }
    }
}