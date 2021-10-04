package com.luiz.mobile.mystudyapp.view.ui.contact

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.viewmodel.contacts.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val CONTACT = "CONTACT"

@SuppressLint("UseCompatLoadingForDrawables")
class ContactActivity : BaseActivity() {

    private lateinit var mTieNameContact: TextInputEditText
    private lateinit var mTieAgeContact: TextInputEditText
    private lateinit var mTieCityContact: TextInputEditText
    private lateinit var mBtnSave: Button

    private var mContact: Contact = Contact()

    private val viewModel: ContactViewModel by viewModel()

    companion object {
        fun intent(context: Context, contact: Contact? = null): Intent {
            return Intent(context, ContactActivity::class.java).apply {
                putExtra(CONTACT, contact)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        findView()
        listenerTextWatcher()

        val extras = intent.extras
        if (extras!!.containsKey(CONTACT)) {
            if (extras.get(CONTACT) != null) {
                val bundleContact: Contact = extras.get(CONTACT) as Contact

                mContact.id = bundleContact.id
                mContact.name = bundleContact.name
                mContact.age = bundleContact.age
                mContact.city = bundleContact.city

                (mTieNameContact as TextView).text = mContact.name
                (mTieAgeContact as TextView).text = mContact.age.toString()
                (mTieCityContact as TextView).text = mContact.city
            }
        }

        mBtnSave.setOnClickListener {
            viewModel.successLiveData.observe(this, {
                toast(msgText = "Contato salvo com sucesso")
                startActivity(ContactListsActivity.intent(this@ContactActivity))
            })
            viewModel.databaseErrorLiveData.observe(this, { msgErr ->
                toast(msgText = "Houve um erro: $msgErr")
            })
            if (mContact.id == 0L)
                viewModel.save(mContact)
            else
                viewModel.update(mContact)
        }
    }

    private fun listenerTextWatcher() {
        mTieNameContact.addTextChangedListener(textWatcher)
        mTieAgeContact.addTextChangedListener(textWatcher)
        mTieCityContact.addTextChangedListener(textWatcher)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = mTieNameContact.text.toString()
            val age = mTieAgeContact.text.toString()
            val city = mTieCityContact.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()) {
                mContact = viewModel.buildContact(mContact.id, name, age, city)
                configStateButton(true)
            } else {
                configStateButton(false)
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun findView() {
        mTieNameContact = findViewById(R.id.tie_name_contact)
        mTieAgeContact = findViewById(R.id.tie_age_contact)
        mTieCityContact = findViewById(R.id.tie_city_contact)
        mBtnSave = findViewById(R.id.btn_save_contact)
        configStateButton(false)
    }

    private fun configStateButton(isEnabled: Boolean) {
        mBtnSave.isEnabled = isEnabled
        if (!isEnabled) {
            mBtnSave.background = getDrawable(R.drawable.btn_bg_disabled)
        }
    }

}