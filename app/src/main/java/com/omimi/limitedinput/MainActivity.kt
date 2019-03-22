package com.omimi.limitedinput

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var showBottomNav: MenuItem
    private lateinit var hideBottomNav: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //todo add stuff for nav drawer & connecting it to the nav graph? might not matter

        setupNavigation()

        showTutorial()
    }

    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.displayOneFragment, R.id.displayTwoFragment, R.id.displayThreeFragment, R.id.displayFourFragment, R.id.displayFiveFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        showBottomNav = menu.findItem(R.id.show_bottom_nav)
        hideBottomNav = menu.findItem(R.id.hide_bottom_nav)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.show_bottom_nav -> {
                showBottomNav()
                true
            }

            R.id.hide_bottom_nav -> {
                hideBottomNav()
                true
            }

            R.id.show_tutorial -> {
                showTutorial()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun showTutorial() {
        startActivity(Intent(baseContext, TutorialIntro::class.java))
    }

    private fun hideBottomNav() {
        bottom_nav_view.visibility = View.GONE
        hideBottomNav.isVisible = false
        showBottomNav.isVisible = true
    }

    private fun showBottomNav() {
        bottom_nav_view.visibility = View.VISIBLE
        hideBottomNav.isVisible = true
        showBottomNav.isVisible = false
    }
}
