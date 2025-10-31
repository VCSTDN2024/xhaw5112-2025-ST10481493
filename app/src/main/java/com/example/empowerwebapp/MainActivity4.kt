package com.example.empowerwebapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity4 : AppCompatActivity() {

    private lateinit var spnCourse1: Spinner
    private lateinit var spnCourse2: Spinner
    private lateinit var spnCourse3: Spinner
    private lateinit var spnCourse4: Spinner
    private lateinit var btnCalculate: Button
    private lateinit var btnPurchase: Button
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var nav_view: NavigationView
    private lateinit var btn_nav: Button

    private val courses = listOf(
        "Select a course",
        "First Aid - R1500",
        "Sewing - R1500",
        "Landscaping - R1500",
        "Life Skills - R1500",
        "Child Minding - R750",
        "Cooking - R750",
        "Garden Maintenance - R750"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        // Initialize views
        spnCourse1 = findViewById(R.id.spn_course1)
        spnCourse2 = findViewById(R.id.spn_course2)
        spnCourse3 = findViewById(R.id.spn_course3)
        spnCourse4 = findViewById(R.id.spn_course4)
        btnCalculate = findViewById(R.id.btn_calculatefees)
        btnPurchase = findViewById(R.id.btn_purchase)
        drawer_layout = findViewById(R.id.drawer_layout)
        nav_view = findViewById(R.id.nav_view)
        btn_nav = findViewById(R.id.btn_nav)

        // Setup spinners with duplicate prevention
        setupSpinnerWithValidation(spnCourse1)
        setupSpinnerWithValidation(spnCourse2)
        setupSpinnerWithValidation(spnCourse3)
        setupSpinnerWithValidation(spnCourse4)

        // Calculate Fees button
        btnCalculate.setOnClickListener { calculateSelectedCourses() }

        // Purchase button
        btnPurchase.setOnClickListener { handlePurchase() }

        // Drawer navigation toggle
        btn_nav.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        // Navigation menu
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity2::class.java))
                R.id.nav_courses -> startActivity(Intent(this, MainActivity3::class.java))
                R.id.nav_calculatefees -> recreate()
                R.id.nav_contact -> startActivity(Intent(this, MainActivity5::class.java))
                R.id.nav_logout -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setupSpinnerWithValidation(spinner: Spinner) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selected = parent.getItemAtPosition(position).toString()
                if (selected != "Select a course" && isCourseAlreadySelected(selected, spinner)) {
                    Toast.makeText(this@MainActivity4, "This course is already selected!", Toast.LENGTH_SHORT).show()
                    spinner.setSelection(0) // reset to "Select a course"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun isCourseAlreadySelected(course: String, currentSpinner: Spinner): Boolean {
        val otherSelections = listOf(spnCourse1, spnCourse2, spnCourse3, spnCourse4)
            .filter { it != currentSpinner }
            .map { it.selectedItem.toString() }

        return otherSelections.contains(course)
    }

    private fun getSelectedCourses(): List<String> {
        return listOf(
            spnCourse1.selectedItem.toString(),
            spnCourse2.selectedItem.toString(),
            spnCourse3.selectedItem.toString(),
            spnCourse4.selectedItem.toString()
        ).filter { it != "Select a course" }
    }

    private fun extractPrice(course: String): Int {
        val priceRegex = Regex("R\\s?([0-9 ]+)")
        val match = priceRegex.find(course)
        return match?.groups?.get(1)?.value?.replace(" ", "")?.toIntOrNull() ?: 0
    }

    private fun calculateSelectedCourses() {
        val selected = getSelectedCourses()

        if (selected.isEmpty()) {
            Toast.makeText(this, "Please select at least one course", Toast.LENGTH_LONG).show()
            return
        }

        var total = 0
        val summary = StringBuilder("You selected the following courses:\n\n")
        for (course in selected) {
            summary.append("• $course\n")
            total += extractPrice(course)
        }

        val discount = when (selected.size) {
            1 -> 0.0
            2 -> 0.05
            3 -> 0.10
            else -> 0.15
        }

        val discountAmount = (total * discount).toInt()
        val discountedTotal = total - discountAmount
        val discountPercent = (discount * 100).toInt()

        summary.append("\nSubtotal: R$total")
        if (discount > 0) {
            summary.append("\nDiscount ($discountPercent%): -R$discountAmount")
            summary.append("\nFinal Total (after discount): R$discountedTotal")
        } else {
            summary.append("\nNo discount applied")
            summary.append("\nFinal Total: R$discountedTotal")
        }

        AlertDialog.Builder(this)
            .setTitle("Course Summary")
            .setMessage(summary.toString())
            .setPositiveButton("OK", null)
            .show()
    }

    private fun handlePurchase() {
        val selectedCourses = getSelectedCourses()

        if (selectedCourses.isEmpty()) {
            Toast.makeText(this, "Please select at least one course", Toast.LENGTH_LONG).show()
            return
        }

        var total = 0
        val purchaseSummary = StringBuilder("You selected the following courses:\n\n")
        for (course in selectedCourses) {
            purchaseSummary.append("• $course\n")
            total += extractPrice(course)
        }

        val discount = when (selectedCourses.size) {
            1 -> 0.0
            2 -> 0.05
            3 -> 0.10
            else -> 0.15
        }

        val discountAmount = (total * discount).toInt()
        val discountedTotal = total - discountAmount
        val discountPercent = (discount * 100).toInt()

        purchaseSummary.append("\nSubtotal: R$total")
        if (discount > 0) {
            purchaseSummary.append("\nDiscount ($discountPercent%): -R$discountAmount")
            purchaseSummary.append("\nFinal Total (after discount): R$discountedTotal")
        } else {
            purchaseSummary.append("\nNo discount applied")
            purchaseSummary.append("\nFinal Total: R$discountedTotal")
        }

        AlertDialog.Builder(this)
            .setTitle("Purchase Summary")
            .setMessage(purchaseSummary.toString())
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, MainActivity7::class.java)
                intent.putExtra("PURCHASE_DETAILS", purchaseSummary.toString())
                startActivity(intent)
            }
            .show()
    }

    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
}
