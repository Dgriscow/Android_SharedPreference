package com.example.android_sharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    var sharedDataFile = "SharedDataFile"

    val themeKey = "themeKey"

    var currentTheme:Boolean = false //the views theme value, this value is written to from preferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var saved_effect = findViewById<EditText>(R.id.textItem)

        //check sp
        val createPreferences = getSharedPreferences(sharedDataFile, MODE_PRIVATE) //a reference to the shared reference "SharedDatafile"
        currentTheme = createPreferences.getBoolean(themeKey, false)

        //set appropriate theme
        //setTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dark)

        if(currentTheme){

            setTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dark)
        }else{
            setTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Light)

        }

        setContentView(R.layout.activity_main)


        var toggleButton = findViewById<Switch>(R.id.switch1)

        toggleButton.isChecked = currentTheme

        toggleButton.setOnCheckedChangeListener{
            view, isChecked -> toggleTheme(isChecked)
        }



    }

    private fun toggleTheme(checked: Boolean) {
        //toggles the theme of the view based on the setting from the shared preference
        val preferenceEditor = getSharedPreferences(sharedDataFile, MODE_PRIVATE).edit() //a reference to the shared reference "SharedDatafile"

        preferenceEditor.apply{

            putBoolean(themeKey,checked)
            apply() //Using this method applies the changes on the main "thread" or operation,
            //this allows the view to update live.
        }

        //This is how you would make a view refresh from a asynronous task,
        //when the function is about to end it makes a blank intent to start,
        //this intent makes the view refresh.
        val intent = intent
        finish()
        startActivity(intent)
       Log.e("theme", checked.toString())



    }


    override fun onResume() {
        super.onResume()

        val sharedPreferenceReference = getSharedPreferences(sharedDataFile, MODE_PRIVATE) //a reference to the shared reference "SharedDatafile"

        val key1 = sharedPreferenceReference.getString("name", "")
        var saved_effect = findViewById<EditText>(R.id.textItem)

        saved_effect.setText(key1)


        //Logic for theme switching ---------------------------

        //get users theme value on resume

        //set the toggle button




    }

    override fun onPause() {
        super.onPause()

        val getPreference = getSharedPreferences(sharedDataFile, MODE_PRIVATE)

        //edit the value
        val myEdit = getPreference.edit()

        var saved_effect = findViewById<EditText>(R.id.textItem)


        myEdit.putString("name", saved_effect.text.toString())


        //save the current theme settings

        myEdit.commit()

    }


}