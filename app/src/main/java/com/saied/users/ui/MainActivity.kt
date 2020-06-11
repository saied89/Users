package com.saied.users.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.saied.users.R
import com.saied.users.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // actionbar title update based on current destination.
        // https://developer.android.com/guide/navigation/navigation-ui#action_bar
        setupActionBarWithNavController(
            findNavController(R.id.nav_host_fragment)
        )
    }

    // handle up navigation. https://developer.android.com/guide/navigation/navigation-ui#action_bar
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}