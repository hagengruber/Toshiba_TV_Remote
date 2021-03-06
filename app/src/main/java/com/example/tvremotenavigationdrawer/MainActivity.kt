package com.example.tvremotenavigationdrawer


import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tvremotenavigationdrawer.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    fun shouldAskPermissions(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    fun askPermissions() {
        val permissions = arrayOf(
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        )
        val requestCode = 200
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (shouldAskPermissions()) {
            askPermissions()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // If User clicks Volume Down/Up Button send Frame
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            val s = HomeFragment()
            thread{ s.doSocket("1017", false) }
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            val s = HomeFragment()
            thread{ s.doSocket("1016", false) }
        }
        return true
    }

    // If User clicks Volume Down/Up Button send Frame
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            val s = HomeFragment()
            thread{ s.doSocket("1017", false) }
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            val s = HomeFragment()
            thread{ s.doSocket("1016", false) }
        }
        return true
    }

}