package com.example.jogodemimica

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.jogodemimica.databinding.ActivityMainBinding


/**
 * This class is the host for all fragments
 */
class MainActivity : AppCompatActivity() {

    // TODO
    // Navigate using volume keys
    // Use dataBinding and safeargs
    // Check issues
    // Options on drawerLayout
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Navigate Up and Drawer Layout
        val navController = this.findNavController(R.id.myNavHostFragment)
        drawerLayout = binding.drawerLayout

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navController)

        // Navigation Listener
        navController.addOnDestinationChangedListener{controller, destination, _ ->
            when(destination.id) {
                controller.graph.startDestination -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                else -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean
//    {
//        when(item.itemId)
//        {
//            R.id.addNew -> Toast.makeText(applicationContext,"Brevemente ...",Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
