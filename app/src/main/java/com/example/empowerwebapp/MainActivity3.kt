package com.example.empowerwebapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity3 : AppCompatActivity() {

    private data class Group(
        val img: View,
        val txt: View,
        val inner: View,
        val btn: Button
    )

    private lateinit var setGroups: List<Group>
    private lateinit var packGroups: List<Group>

    // Indexes for the currently visible set/pack
    private var currentSetIndex = 0
    private var currentPackIndex = 0
    private lateinit var btn_moreinfo : Button
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var nav_view: NavigationView
    private lateinit var btn_nav: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        // ====== EDIT THESE ARRAYS ======
        // Put the long distinct messages you want for each set/pack here.
        // The array index must match the group index (set1 -> index 0, set2 -> index 1, ...)
        val setMessages = arrayOf(
            "First Aid\n" +
                    "Fees: R1500\n" +
                    "Purpose: To provide first aid awareness and basic life support\n" +
                    "Content:\n" +
                    "• Wounds and bleeding\n" +
                    "• Burns and fractures\n" +
                    "• Emergency scene management\n" +
                    "• Cardio-Pulmonary Resuscitation (CPR)\n" +
                    "• Respiratory distress e.g., Choking, blocked airway",
            "Sewing\n" +
                    "Fees: R1500\n" +
                    "Purpose: To provide alterations and new garment tailoring services\n" +
                    "Content:\n" +
                    "• Types of stitches\n" +
                    "• Threading a sewing machine\n" +
                    "• Sewing buttons, zips, hems and seams\n" +
                    "• Alterations\n" +
                    "• Designing and sewing new garments",
            "Landscaping\n" +
                    "Fees: R1500\n" +
                    "Purpose: To provide landscaping services for new and established gardens\n" +
                    "Content:\n" +
                    "• Indigenous and exotic plants and trees\n" +
                    "• Fixed structures (fountains, statues, benches, tables, built-in braai)\n" +
                    "• Balancing of plants and trees in a garden\n" +
                    "• Aesthetics of plant shapes and colours\n" +
                    "• Garden layout",
            "Life Skills\n" +
                    "Fees: R1500\n" +
                    "Purpose: To provide skills to navigate basic life necessities\n" +
                    "Content:\n" +
                    "• Opening a bank account\n" +
                    "• Basic labour law (know your rights)\n" +
                    "• Basic reading and writing literacy\n" +
                    "• Basic numeric literacy"
        )

        val packMessages = arrayOf(
            "Child Minding\n" +
                    "Fees: R750\n" +
                    "Purpose: To provide basic child and baby care\n" +
                    "Content:\n" +
                    "• Birth to six-month old baby needs\n" +
                    "• Seven-month to one year old needs\n" +
                    "• Toddler needs\n" +
                    "• Educational toys",
            "Cooking\n" +
                    "Fees: R750\n" +
                    "Purpose: To prepare and cook nutritious family meals\n" +
                    "Content:\n" +
                    "• Nutritional requirements for a healthy body\n" +
                    "• Types of protein, carbohydrates and vegetables\n" +
                    "• Planning meals\n" +
                    "• Tasty and nutritious recipes\n" +
                    "• Preparation and cooking of meals",
            "Garden Maintenance\n" +
                    "Fees: R750\n" +
                    "Purpose: To provide basic knowledge of watering, pruning and planting in a domestic garden\n" +
                    "Content:\n" +
                    "• Water restrictions and the watering requirements of indigenous and exotic plants\n" +
                    "• Pruning and propagation of plants\n" +
                    "• Planting techniques for different plant types"
        )
        // ====== /EDIT THESE ARRAYS ======

        // Build set groups
        setGroups = listOf(
            Group(findViewById(R.id.img_set), findViewById(R.id.txt_set), findViewById(R.id.imag_innerset), findViewById(R.id.btn_set)),
            Group(findViewById(R.id.img_set2), findViewById(R.id.txt_set2), findViewById(R.id.imag_innerset2), findViewById(R.id.btn_set2)),
            Group(findViewById(R.id.img_set3), findViewById(R.id.txt_set3), findViewById(R.id.imag_innerset3), findViewById(R.id.btn_set3)),
            Group(findViewById(R.id.img_set4), findViewById(R.id.txt_set4), findViewById(R.id.imag_innerset4), findViewById(R.id.btn_set4))
        )

        // Build pack groups
        packGroups = listOf(
            Group(findViewById(R.id.img_pack1), findViewById(R.id.txt_pack1), findViewById(R.id.img_innerpack1), findViewById(R.id.btn_pack1)),
            Group(findViewById(R.id.img_pack2), findViewById(R.id.txt_pack2), findViewById(R.id.img_innerpack2), findViewById(R.id.btn_pack2)),
            Group(findViewById(R.id.img_pack3), findViewById(R.id.txt_pack3), findViewById(R.id.img_innerpack3), findViewById(R.id.btn_pack3))
        )

        // If your arrays don't match the number of groups, we fall back to a default message.
        // (You can also add a runtime check and throw/log if you prefer.)
        fun setMessageFor(index: Int) = setMessages.getOrNull(index) ?: "No message set for this set."
        fun packMessageFor(index: Int) = packMessages.getOrNull(index) ?: "No message set for this pack."

        // Setup initial visibility: only first set and first pack shown & enabled
        showOnlySet(currentSetIndex)
        showOnlyPack(currentPackIndex)

        // Next buttons (advance only these)
        findViewById<Button>(R.id.btn_nextset).setOnClickListener { advanceSet() }
        findViewById<Button>(R.id.btn_nextpack).setOnClickListener { advancePack() }

        btn_moreinfo = findViewById(R.id.btn_moreinfo)
        drawer_layout = findViewById(R.id.drawer_layout)
        nav_view = findViewById(R.id.nav_view)
        btn_nav = findViewById(R.id.btn_nav)

        btn_moreinfo.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
            finish()
        }

        // Attach listeners for each set button: show popup ONLY (do NOT advance)
        setGroups.forEachIndexed { index, group ->
            group.btn.setOnClickListener {
                showLongPopup(setMessageFor(index))
            }
        }

        // Attach listeners for each pack button: show popup ONLY (do NOT advance)
        packGroups.forEachIndexed { index, group ->
            group.btn.setOnClickListener {
                showLongPopup(packMessageFor(index))
            }
        }

        // Toggle drawer on button click
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
                    recreate()
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

    private fun showOnlySet(indexToShow: Int) {
        setGroups.forEachIndexed { idx, group ->
            if (idx == indexToShow) {
                group.img.visibility = View.VISIBLE
                group.txt.visibility = View.VISIBLE
                group.inner.visibility = View.VISIBLE
                group.btn.visibility = View.VISIBLE
                group.btn.isEnabled = true
            } else {
                group.img.visibility = View.GONE
                group.txt.visibility = View.GONE
                group.inner.visibility = View.GONE
                group.btn.visibility = View.GONE
                group.btn.isEnabled = false
            }
        }
    }

    private fun showOnlyPack(indexToShow: Int) {
        packGroups.forEachIndexed { idx, group ->
            if (idx == indexToShow) {
                group.img.visibility = View.VISIBLE
                group.txt.visibility = View.VISIBLE
                group.inner.visibility = View.VISIBLE
                group.btn.visibility = View.VISIBLE
                group.btn.isEnabled = true
            } else {
                group.img.visibility = View.GONE
                group.txt.visibility = View.GONE
                group.inner.visibility = View.GONE
                group.btn.visibility = View.GONE
                group.btn.isEnabled = false
            }
        }
    }

    private fun advanceSet() {
        currentSetIndex = (currentSetIndex + 1) % setGroups.size
        showOnlySet(currentSetIndex)
    }

    private fun advancePack() {
        currentPackIndex = (currentPackIndex + 1) % packGroups.size
        showOnlyPack(currentPackIndex)
    }

    private fun showLongPopup(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(true)
            .show()
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
