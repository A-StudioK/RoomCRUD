package com.example.room.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.room.R
import com.example.room.database.AppDatabase
import com.example.room.models.Contact
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class ContactActivity : AppCompatActivity() {
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val toolbar = findViewById<Toolbar>(R.id.tbContact)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadContact()
    }

    private fun loadContact() {
        val gson = Gson()
        val stringObj = intent.getStringExtra("contact")

        contact = gson.fromJson(stringObj, Contact::class.java) ?: Contact(null, "", "")

        if (contact.id != null) {
            val etName = findViewById<TextInputEditText>(R.id.etName)
            val etTelf = findViewById<TextInputEditText>(R.id.etTelf)
            etName.setText(contact.name)
            etTelf.setText(contact.telephone)
        }
    }

    private fun saveContact() {
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelf = findViewById<TextInputEditText>(R.id.etTelf)
        contact.name = etName.text.toString()
        contact.telephone = etTelf.text.toString()

        if (contact.id == null) {
            AppDatabase.getInstance(this).getDao().insertContact(contact)
        }
        else {
            AppDatabase.getInstance(this).getDao().updateContact(contact)
        }
        finish()
    }

    private fun deleteContact() {
        AppDatabase.getInstance(this).getDao().deleteContact(contact)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.itemSave -> {
                saveContact()
                true
            }
            R.id.itemDelete -> {
                deleteContact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}