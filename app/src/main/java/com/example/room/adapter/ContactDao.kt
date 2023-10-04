package com.example.room.adapter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room.models.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAll(): List<Contact>

    @Insert
    fun insertContact(vararg contacts: Contact)

    @Delete
    fun deleteContact(vararg contacts: Contact)

    @Update
    fun updateContact(vararg contacts: Contact)
}