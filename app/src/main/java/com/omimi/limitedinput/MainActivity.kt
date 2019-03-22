package com.omimi.limitedinput

import android.app.AlertDialog
import android.content.Intent
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_menu_options.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var showBottomNav: MenuItem
    private lateinit var hideBottomNav: MenuItem
    private var bottomNavShown = false
    private lateinit var alertDialog: AlertDialog
    private lateinit var navController: NavController
    private lateinit var gestureLibrary: GestureLibrary

    private val lGesture = "l_gesture"
    private val reverseLGesture = "reverse_l_gesture"
    private val openBottomGesture = "open_bottom_gesture"
    private val openLeftGesture = "open_left_gesture"
    private val openRightGesture = "open_right_gesture"
    private val openTopGesture = "open_top_gesture"
    private val closedGesture = "closed_gesture"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(this, R.id.nav_host_fragment)

        setupNavigation()
        showTutorial()

        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gesture)

        //guarantee that it loaded properly
        if(!gestureLibrary.load()) {
            finish()
        }

        gesture_overlay.addOnGesturePerformedListener { overlay, gesture ->
            var predictions = gestureLibrary.recognize(gesture)

            if(predictions.size > 0 && predictions[0].score > 1.0) {
                var action = predictions[0].name

                when(action) {
                    lGesture -> {
                        openNavDrawer()
                    }

                    reverseLGesture -> {
                        showOptionsDialog()
                    }

                    openRightGesture -> {
                        showDisplayOneFragment()
                    }

                    openBottomGesture -> {
                        showDisplayTwoFragment()
                    }

                    closedGesture -> {
                        showDisplayThreeFragment()
                    }

                    openTopGesture -> {
                        showDisplayFourFragment()
                    }

                    openLeftGesture -> {
                        showDisplayFiveFragment()
                    }
                }
            }
        }

        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawers()
        true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        //hacky fix to make the nav drawer on toolbar stay where its expected when switching fragments
        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
            }
        }
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

            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
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

    fun showOptionsDialog() {
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

    private fun showDisplayFiveFragment() {
        navController.navigate(R.id.displayFiveFragment)
    }

    private fun showDisplayFourFragment() {
        navController.navigate(R.id.displayFourFragment)
    }

    private fun showDisplayOneFragment() {
        navController.navigate(R.id.displayOneFragment)
    }

    private fun showDisplayThreeFragment() {
        navController.navigate(R.id.displayThreeFragment)
    }

    private fun showDisplayTwoFragment() {
        navController.navigate(R.id.displayTwoFragment)
    }

    private fun openNavDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }
}
