package com.omimi.limitedinput

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_menu_options.view.*

class MainActivity : AppCompatActivity(), GestureCallback {
    private lateinit var showBottomNav: MenuItem
    private lateinit var hideBottomNav: MenuItem
    private var bottomNavShown = false
    private lateinit var alertDialog: AlertDialog
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(this, R.id.nav_host_fragment)

        //todo add stuff for nav drawer & connecting it to the nav graph? might not matter

        setupNavigation()

        showTutorial()

        gesture_detector.setGestureCallback(this)
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
        bottomNavShown = true
    }

    private fun showBottomNav() {
        bottom_nav_view.visibility = View.VISIBLE
        hideBottomNav.isVisible = true
        showBottomNav.isVisible = false
        bottomNavShown = false
    }

    override fun showOptionsDialog() {
        var dialogView = layoutInflater.inflate(R.layout.dialog_menu_options, null)
        var dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Dropdown Options Menu")

        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(true)

        if(bottomNavShown) {
            dialogView.btn_hide_bottom_nav.visibility = View.VISIBLE
            dialogView.btn_hide_bottom_nav.setOnClickListener {
                hideBottomNav()
                alertDialog.dismiss()
            }
        } else {
            dialogView.btn_show_bottom_nav.visibility = View.VISIBLE
            dialogView.btn_show_bottom_nav.setOnClickListener {
                showBottomNav()
                alertDialog.dismiss()
            }
        }

        dialogView.btn_show_tutorial.setOnClickListener {
            showTutorial()
            alertDialog.dismiss()
        }

        alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun showDisplayFiveFragment() {
        navController.navigate(R.id.displayFiveFragment)
    }

    override fun showDisplayFourFragment() {
        navController.navigate(R.id.displayFourFragment)
    }

    override fun showDisplayOneFragment() {
        navController.navigate(R.id.displayOneFragment)
    }

    override fun showDisplayThreeFragment() {
        navController.navigate(R.id.displayThreeFragment)
    }

    override fun showDisplayTwoFragment() {
        navController.navigate(R.id.displayTwoFragment)
    }

    override fun openNavDrawer() {
        //todo make this open the nav drawer
    }
}
