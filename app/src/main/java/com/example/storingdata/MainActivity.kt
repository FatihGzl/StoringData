package com.example.storingdata

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences
    var ageFromPref : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //SharedPreferences - XML - Kev-Value
        sharedPref = this.getSharedPreferences("com.example.storingdata", Context.MODE_PRIVATE)

        ageFromPref = sharedPref.getInt("age", -1)

        if (ageFromPref == -1) {
            binding.textView.text = "Your age : "
        } else {
            binding.textView.text = "Your age : ${ageFromPref}"
        }

    }

    fun save(view : View) {
        val myAge = binding.editText.text.toString().toIntOrNull()

        if (myAge != null) {
            binding.textView.text = "Your age : ${myAge}"
            //binding.textView.text = "Your age : " + myAge
            sharedPref.edit().putInt("age", myAge).apply()
        }


    }

    fun delete(view : View) {

        ageFromPref = sharedPref.getInt("age", -1)

        if (ageFromPref != -1) {
            sharedPref.edit().remove("age").apply()
            binding.textView.text = "Your age : "
        }

    }

}