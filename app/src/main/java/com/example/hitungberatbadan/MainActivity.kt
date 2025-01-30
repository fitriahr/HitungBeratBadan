package com.example.hitungberatbadan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etTinggiBadan = findViewById<EditText>(R.id.etTinggiBadan)
        val etBeratBadan = findViewById<EditText>(R.id.etBeratBadan)
        val radioGroupJenisKelamin = findViewById<RadioGroup>(R.id.radioGroupJenisKelamin)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnHitung.setOnClickListener {
            val nama = etNama.text.toString()
            val tinggiBadan = etTinggiBadan.text.toString().toIntOrNull()
            val beratBadan = etBeratBadan.text.toString().toDoubleOrNull()

            if (nama.isEmpty()) {
                tvHasil.text = "Nama tidak boleh kosong"
                return@setOnClickListener
            }

            if (tinggiBadan == null || beratBadan == null) {
                tvHasil.text = "Tinggi atau Berat Badan tidak valid"
                return@setOnClickListener
            }

            val selectedGenderId = radioGroupJenisKelamin.checkedRadioButtonId
            val jenisKelamin = findViewById<RadioButton>(selectedGenderId).text.toString()

            val hasil = hitungBeratBadanIdeal(tinggiBadan, jenisKelamin)

            tvHasil.text = """
                Nama: $nama
                Jenis Kelamin: $jenisKelamin
                Berat Badan: $beratBadan kg
                Tinggi Badan: $tinggiBadan cm
                Berat Badan Ideal: $hasil kg
            """.trimIndent()
        }
    }

    private fun hitungBeratBadanIdeal(tinggiBadan: Int, jenisKelamin: String): Double {
        val tinggiBadanM = tinggiBadan / 100.0
        return if (jenisKelamin == "Pria") {
            22 * (tinggiBadanM * tinggiBadanM)
        } else {
            21 * (tinggiBadanM * tinggiBadanM)
        }
    }
}
