package com.luiz.mobile.mystudyapp.view.ui.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.domain.entity.Contact
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.adapter.ContactAdapter
import com.luiz.mobile.mystudyapp.view.viewmodel.contacts.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListsActivity : BaseActivity() {

    private lateinit var mIvExcept: ImageView
    private lateinit var mVfItems: ViewFlipper
    private lateinit var mTvMsgExcept: TextView
    private lateinit var mRvContacts: RecyclerView
    private lateinit var mContactList: List<Contact>
    private lateinit var mCircleProgress: AlertDialog

    private val viewModel: ContactViewModel by viewModel()

    companion object {
        private const val MSG_EXCEPT = 0
        private const val RECYCLER = 1
        fun intent(context: Context): Intent {
            return Intent(context, ContactListsActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        mCircleProgress = buildCircleProgress()

        findView()

        mCircleProgress.show()

        viewModel.successLiveData.observe(this, { response ->
            response?.let { it ->
                if (it.toString() == "removed") {
                    viewModel.getAll()
                } else {
                    mContactList = it as List<Contact>
                    if (mContactList.isEmpty()) {
                        mVfItems.displayedChild = MSG_EXCEPT
                        mIvExcept.visibility = View.VISIBLE
                        mIvExcept.setImageResource(R.drawable.ic_group_off)
                        mTvMsgExcept.visibility = View.VISIBLE
                        mCircleProgress.hide()
                        mTvMsgExcept.text =
                            getString(R.string.msg_does_not_have_any_registered_contact)
                    } else {
                        mVfItems.displayedChild = RECYCLER
                        mCircleProgress.hide()
                        val adapter = ContactAdapter(mContactList, viewModel)
                        mRvContacts.adapter = adapter
                        mRvContacts.setHasFixedSize(true)
                        mRvContacts.layoutManager = LinearLayoutManager(this@ContactListsActivity)
                    }
                }
            }
        })
        viewModel.databaseErrorLiveData.observe(this, { msgError ->
            mVfItems.displayedChild = MSG_EXCEPT
            mIvExcept.visibility = View.VISIBLE
            mTvMsgExcept.visibility = View.VISIBLE
            mCircleProgress.hide()
            mTvMsgExcept.text = msgError
        })
        viewModel.getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.it_add_contact -> {
                startActivity(ContactActivity.intent(this@ContactListsActivity))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun findView() {
        mVfItems = findViewById(R.id.vf_items_contact)
        mRvContacts = findViewById(R.id.rv_contacts)
        mTvMsgExcept = findViewById(R.id.tv_msg_except)
        mIvExcept = findViewById(R.id.iv_except)
    }
}