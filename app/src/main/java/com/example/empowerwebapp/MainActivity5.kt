package com.example.empowerwebapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class MainActivity5 : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var btnNav: com.google.android.material.button.MaterialButton

    // Campus Google Maps URLs
    private val centralCampusUrl = "https://maps.google.com/?q=-26.2041,28.0473"
    private val northCampusUrl = "https://maps.google.com/?q=-26.1076,28.0567"
    private val southCampusUrl = "https://maps.google.com/?q=-26.2678,27.8546"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        initializeViews()
        setupToolbar()
        setupClickListeners()
        setupNavigation()
    }

    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        btnNav = findViewById(R.id.btn_nav)
    }

    private fun setupToolbar() {
        btnNav.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }



    private fun setupClickListeners() {
        // Map buttons - Open Google Maps
        findViewById<MaterialButton>(R.id.btn_map_central).setOnClickListener {
            openGoogleMaps(centralCampusUrl)
        }

        findViewById<MaterialButton>(R.id.btn_map_north).setOnClickListener {
            openGoogleMaps(northCampusUrl)
        }

        findViewById<MaterialButton>(R.id.btn_map_south).setOnClickListener {
            openGoogleMaps(southCampusUrl)
        }

        // Call buttons
        findViewById<MaterialButton>(R.id.btn_call_central).setOnClickListener {
            makePhoneCall(getString(R.string.central_phone))
        }

        findViewById<MaterialButton>(R.id.btn_call_north).setOnClickListener {
            makePhoneCall(getString(R.string.north_phone))
        }

        findViewById<MaterialButton>(R.id.btn_call_south).setOnClickListener {
            makePhoneCall(getString(R.string.south_phone))
        }

        // Directions buttons - Also open Google Maps
        findViewById<MaterialButton>(R.id.btn_map_central).setOnClickListener {
            openGoogleMaps(centralCampusUrl)
        }

        findViewById<MaterialButton>(R.id.btn_map_north).setOnClickListener {
            openGoogleMaps(northCampusUrl)
        }

        findViewById<MaterialButton>(R.id.btn_map_south).setOnClickListener {
            openGoogleMaps(southCampusUrl)
        }

        // Social media buttons
        findViewById<MaterialButton>(R.id.btn_instagram).setOnClickListener {
            openUrl("https://www.instagram.com/empoweringthenation_sa")
        }

        findViewById<MaterialButton>(R.id.btn_linkedin).setOnClickListener {
            openUrl("https://www.linkedin.com/company/empowering-the-nation")
        }

        findViewById<MaterialButton>(R.id.btn_facebook).setOnClickListener {
            openUrl("https://www.facebook.com/EmpoweringTheNationSA")
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        } else {
            // Request permission or use ACTION_DIAL instead
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }
    }

    private fun openGoogleMaps(mapUrl: String) {
        try {
            // Try to open in Google Maps app first
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Fallback to web browser
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                startActivity(webIntent)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to open maps", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }



    private fun setupNavigation() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity2::class.java))
                    finish()
                }
                R.id.nav_courses -> {
                    startActivity(Intent(this, MainActivity3::class.java))
                    finish()
                }
                R.id.nav_calculatefees -> {
                    startActivity(Intent(this, MainActivity4::class.java))
                    finish()
                }
                R.id.nav_contact -> {
                    Toast.makeText(this, "Already on Contact page", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_logout -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }



    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
}