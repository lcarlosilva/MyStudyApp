package com.luiz.mobile.mystudyapp.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.view.ui.contact.ContactActivity
import com.luiz.mobile.mystudyapp.view.viewmodel.contacts.ContactViewModel
import kotlinx.android.synthetic.main.item_list_contact.view.*

class ContactAdapter(private val contacts: List<Contact>, private val viewModel: ContactViewModel) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflateItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_contact, parent, false)
        return ContactViewHolder(inflateItemView, viewModel)
    }

    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) =
        holderContact.bindView(contacts[position])

    override fun getItemCount(): Int = contacts.count()

    class ContactViewHolder(itemView: View, private val viewModel: ContactViewModel) :
        RecyclerView.ViewHolder(itemView) {

        private val nameContact = itemView.tv_contact_name
        private val ageContact = itemView.tv_contact_age

        fun bindView(contact: Contact) {

            nameContact.text = itemView.resources.getString(R.string.lbl_name, contact.name)
            ageContact.text = itemView.resources.getString(R.string.lbl_age, contact.age.toString())

            itemView.setOnClickListener {
                itemView.context.startActivity(ContactActivity.intent(itemView.context, contact))
            }

            itemView.findViewById<ImageButton>(R.id.iv_delete_contact).setOnClickListener {
                val dialog = AlertDialog.Builder(it.context)
                dialog.setTitle(R.string.title_remove_contact)
                dialog.setMessage(R.string.msg_do_you_want_remove_contact)
                dialog.setPositiveButton(R.string.btn_txt_yes) { _, _ ->
                    viewModel.remove(contact)
                }
                dialog.setNegativeButton(R.string.btn_txt_no) { _, _ -> }
                dialog.create().show()
            }
        }
    }
}