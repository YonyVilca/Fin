package com.example.f

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val spinnerDepartment: Spinner = findViewById(R.id.spinner_department)
        val spinnerProvince: Spinner = findViewById(R.id.spinner_province)
        val spinnerDistrict: Spinner = findViewById(R.id.spinner_district)
        val listViewPharmacies: ListView = findViewById(R.id.list_view_pharmacies)
        val radioGroupPayment: RadioGroup = findViewById(R.id.radio_group_payment)
        val radioGroupVoucher: RadioGroup = findViewById(R.id.radio_group_voucher)
        val buttonBack: Button = findViewById(R.id.button_back)
        val buttonCompletePurchase: Button = findViewById(R.id.button_complete_purchase)

        // Dummy data for spinners and list view
        val departments = listOf("Departamento 1", "Departamento 2")
        val provinces = listOf("Provincia 1", "Provincia 2")
        val districts = listOf("Distrito 1", "Distrito 2")
        val pharmacies = listOf("Farmacia 1", "Farmacia 2")

        // Set adapters for spinners
        spinnerDepartment.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departments)
        spinnerProvince.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provinces)
        spinnerDistrict.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, districts)

        // Set adapter for pharmacies list view
        listViewPharmacies.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, pharmacies)
        listViewPharmacies.choiceMode = ListView.CHOICE_MODE_SINGLE

        // Set button listeners
        buttonBack.setOnClickListener {
            finish()
        }

        buttonCompletePurchase.setOnClickListener {
            val selectedPaymentOption = when (radioGroupPayment.checkedRadioButtonId) {
                R.id.radio_yape -> "YAPE"
                R.id.radio_card -> "Tarjeta"
                R.id.radio_cash -> "PagoEfectivo"
                else -> ""
            }

            val selectedVoucherOption = when (radioGroupVoucher.checkedRadioButtonId) {
                R.id.radio_receipt -> "Boleta"
                R.id.radio_invoice -> "Factura"
                else -> ""
            }

            if (selectedPaymentOption.isNotEmpty() && selectedVoucherOption.isNotEmpty()) {
                Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()
                // Aquí puedes agregar la lógica para finalizar la compra
                finish()
            } else {
                Toast.makeText(this, "Seleccione todas las opciones", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
