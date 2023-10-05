package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.room.R
import com.example.room.models.Contact

class ContactAdapter(private val contacts: List<Contact>, private val itemClickListener: OnItemClickListener) : Adapter<ContactPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_contact, parent, false)
        return ContactPrototype(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactPrototype, position: Int) {
        holder.bind(contacts[position], itemClickListener)
    }
}

class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName = itemView.findViewById<TextView>(R.id.tvName)
    private val tvTelephone = itemView.findViewById<TextView>(R.id.tvTelf)
    private val cvContact = itemView.findViewById<CardView>(R.id.cvContact)!!

    fun bind(contact: Contact, itemClickListener: OnItemClickListener) {
        tvName.text = contact.name
        tvTelephone.text = contact.telephone

        cvContact.setOnClickListener {
            itemClickListener.onItemClicked(contact)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(contact: Contact)
}