package com.example.empowerwebapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity6 : AppCompatActivity() {

    private lateinit var drawer_layout: DrawerLayout
    private lateinit var nav_view: NavigationView
    private lateinit var btn_nav: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)

        drawer_layout = findViewById(R.id.drawer_layout)
        nav_view = findViewById(R.id.nav_view)
        btn_nav = findViewById(R.id.btn_nav)

        btn_nav.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle menu item clicks
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity2::class.java))
                }

                R.id.nav_courses -> {
                    Toast.makeText(this, "Courses", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity3::class.java))
                }

                R.id.nav_calculatefees -> {
                    Toast.makeText(this, "Calculate Fees", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity4::class.java))
                }

                R.id.nav_contact -> {
                    Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity5::class.java))
                }

                R.id.nav_logout -> {
                    Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }

            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
}