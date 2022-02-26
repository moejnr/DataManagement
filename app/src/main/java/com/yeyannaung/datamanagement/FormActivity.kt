package com.yeyannaung.datamanagement

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yeyannaung.datamanagement.databinding.ActivityFormBinding
import com.yeyannaung.datamanagement.model.User
import java.util.*

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set Female as default gender
        val defaultGender = binding.btnFemale.id
        binding.gender.selectButton(defaultGender)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.etDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val picker = DatePickerDialog(
                this, { it, year, monthOfYear, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year, monthOfYear, dayOfMonth)
                    val selectedDate = selectedCalendar.time
                    val currentDate = Calendar.getInstance().time
                    val diff = selectedDate.compareTo(currentDate)
                    if (diff > 0) {
                        showToast("Please select a valid date!")
                    } else {
                        binding.etDob.setText("$dayOfMonth/${monthOfYear + 1}/$year")
                    }
                }, year, month, day)
            picker.show()
        }
        binding.btnCreateAccountNow.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val dob = binding.etDob.text.toString()
        val nationality = binding.etNationality.text.toString()
        val country = binding.etCountry.text.toString()
        val phone = binding.etPhone.text.toString()
        val gender = binding.gender.selectedButtons[0].text

        if (isEmpty(firstName)) {
            showToast("First Name cannot be empty!")
            binding.etFirstName.requestFocus()
            return
        }

        if (isEmpty(lastName)) {
            showToast("Last Name cannot be empty!")
            binding.etLastName.requestFocus()
            return
        }

        if (isEmpty(email)) {
            showToast("Email cannot be empty!")
            binding.etEmail.requestFocus()
            return
        }

        if (isEmpty(dob)) {
            showToast("Date of Birth cannot be empty!")
            return
        }

        if (isEmpty(nationality)) {
            showToast("Nationality cannot be empty!")
            return
        }

        if (isEmpty(country)) {
            showToast("Country cannot be empty!")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter valid email address!")
            binding.etEmail.requestFocus()
            binding.etEmail.setSelection(email.length)
            return
        }

        val user = User(firstName, lastName, email, dob, gender, nationality, country, phone)
        navigateToSuccessActivity(user)
    }

    private fun navigateToSuccessActivity(user: User) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun showToast(err: String) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
    }
}