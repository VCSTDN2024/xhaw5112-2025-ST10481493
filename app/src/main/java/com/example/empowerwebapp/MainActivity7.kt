package com.example.empowerwebapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class MainActivity7 : AppCompatActivity() {

    // Simple card number validation - just check length and digits
    private fun isValidCardNumber(digits: String): Boolean {
        return digits.length >= 10 && digits.all { it.isDigit() }
    }

    // Check expiry: month 1..12, year must be 4-digit and 2025+
    private fun isExpired(month: Int, year: Int): Boolean {
        val now = Calendar.getInstance()
        val currentYear = now.get(Calendar.YEAR)
        val currentMonth = now.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based

        return year < currentYear || (year == currentYear && month < currentMonth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        // Toolbar (if present)
        val toolbar = findViewById<MaterialToolbar?>(R.id.toolbar)
        toolbar?.setNavigationOnClickListener { finish() }

        // Summary
        val txtSummary = findViewById<TextView>(R.id.txt_summaryinfo)
        txtSummary.text = intent.getStringExtra("PURCHASE_DETAILS") ?: "No purchase details available."

        // Input layouts and edittexts
        val layoutCard = findViewById<TextInputLayout>(R.id.textInputLayout_card_number)
        val layoutMonth = findViewById<TextInputLayout>(R.id.textInputLayout_month)
        val layoutYear = findViewById<TextInputLayout>(R.id.textInputLayout_year)
        val layoutCvv = findViewById<TextInputLayout>(R.id.textInputLayout_cvv)

        val edtCard = findViewById<TextInputEditText>(R.id.edt_account_number)
        val edtMonth = findViewById<TextInputEditText>(R.id.edt_expiry_month)
        val edtYear = findViewById<TextInputEditText>(R.id.edt_expiry_year)
        val edtCvv = findViewById<TextInputEditText>(R.id.edt_cvv)

        val btnPay = findViewById<MaterialButton>(R.id.btn_pay)
        btnPay.setOnClickListener {
            // Reset errors
            layoutCard.error = null; layoutCard.isErrorEnabled = false
            layoutMonth.error = null; layoutMonth.isErrorEnabled = false
            layoutYear.error = null; layoutYear.isErrorEnabled = false
            layoutCvv.error = null; layoutCvv.isErrorEnabled = false

            val rawCard = edtCard.text?.toString() ?: ""
            val card = rawCard.filter { it.isDigit() } // remove spaces or dashes
            val monthStr = edtMonth.text?.toString()?.trim() ?: ""
            val yearStr = edtYear.text?.toString()?.trim() ?: ""
            val cvv = edtCvv.text?.toString()?.trim() ?: ""

            var valid = true

            // Card number
            if (card.isEmpty()) {
                layoutCard.error = "Card number is required"
                valid = false
            } else if (!isValidCardNumber(card)) {
                layoutCard.error = "Card number must be at least 10 digits"
                valid = false
            }

            // Month
            val month = monthStr.toIntOrNull()
            if (monthStr.isEmpty()) {
                layoutMonth.error = "Expiry month required"
                valid = false
            } else if (month == null || month !in 1..12) {
                layoutMonth.error = "Enter month 1–12"
                valid = false
            }

            // Year (must be 4 digits and 2025 or later)
            val yearCandidate = yearStr.toIntOrNull()
            if (yearStr.isEmpty()) {
                layoutYear.error = "Expiry year required"
                valid = false
            } else if (yearCandidate == null || yearStr.length != 4) {
                layoutYear.error = "Enter 4-digit year (e.g. 2025)"
                valid = false
            } else if (yearCandidate < 2025) {
                layoutYear.error = "Year must be 2025 or later"
                valid = false
            }

            // Expiry combined check
            if (valid && month != null && yearCandidate != null) {
                if (isExpired(month, yearCandidate)) {
                    layoutMonth.error = "Card expired"
                    layoutYear.error = "Card expired"
                    valid = false
                }
            }

            // CVV (must be exactly 3 digits)
            if (cvv.isEmpty()) {
                layoutCvv.error = "CVV required"
                valid = false
            } else if (!cvv.matches(Regex("\\d{3}"))) {
                layoutCvv.error = "Enter 3-digit CVV"
                valid = false
            }

            if (!valid) {
                // keep focus on first invalid field
                when {
                    layoutCard.error != null -> edtCard.requestFocus()
                    layoutMonth.error != null -> edtMonth.requestFocus()
                    layoutYear.error != null -> edtYear.requestFocus()
                    layoutCvv.error != null -> edtCvv.requestFocus()
                }
                return@setOnClickListener
            }

            // All good — show confirmation
            AlertDialog.Builder(this)
                .setTitle("Payment Successful")
                .setMessage(
                    "Your payment was processed successfully.\n\n" +
                            "A confirmation will be sent to your email and we will contact you about scheduling."
                )
                .setPositiveButton("OK") { _, _ -> finish() }
                .setCancelable(false)
                .show()
        }
    }
}
