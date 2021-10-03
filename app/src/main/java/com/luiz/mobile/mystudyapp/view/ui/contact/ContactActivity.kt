package com.luiz.mobile.mystudyapp.view.ui.contact

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.viewmodel.contacts.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("UseCompatLoadingForDrawables")
class ContactActivity : BaseActivity() {

    private lateinit var mTieNameContact: TextInputEditText
    private lateinit var mTieAgeContact: TextInputEditText
    private lateinit var mTieCityContact: TextInputEditText
    private lateinit var mBtnSave: Button

    private lateinit var mContact: Contact

    private val viewModel: ContactViewModel by viewModel()

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, ContactActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        findView()
        listenerTextWatcher()


        mBtnSave.setOnClickListener {
            viewModel.successLiveData.observe(this, { contactSave ->
                contactSave as Contact
                toast(
                    msgText = "Contato salvo com sucesso: " +
                            "\n${contactSave.name}\n${contactSave.age}\n${contactSave.city}"
                )
                startActivity(ContactListsActivity.intent(this@ContactActivity))
            })
            viewModel.databaseErrorLiveData.observe(this, { msgErr ->
                toast(msgText = "Houve um erro: $msgErr")
            })
            viewModel.save(mContact)
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
                mContact = viewModel.buildContact(name, age, city)
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
        if (isEnabled) {
            mBtnSave.background = getDrawable(R.drawable.btn_rounded_bg_green)
        } else {
            mBtnSave.background = getDrawable(R.drawable.btn_bg_disabled)
        }
    }

}