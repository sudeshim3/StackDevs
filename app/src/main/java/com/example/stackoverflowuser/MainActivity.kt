package com.example.stackoverflowuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackoverflowuser.Constants.ExceptionType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    private val userAdapter by lazy {
        UserAdapter(R.layout.user_row_item)
    }
    private val userAdapterWOPaging by lazy {
        UserAdapterWOPaging(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Constants.goldColor = ContextCompat.getColor(applicationContext, R.color.gold)
        Constants.silverColor = ContextCompat.getColor(applicationContext, R.color.silver)
        Constants.bronzeColor = ContextCompat.getColor(applicationContext, R.color.bronze)
        setUpRecyclerWithoutPaging()
    }

    private fun showExceptionToast(network: ExceptionType) {
        when (network) {
            ExceptionType.NETWORK -> Toast.makeText(
                this,
                resources.getString(R.string.network_error),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun setUpRecyclerWithoutPaging() {
        rv_user.adapter = userAdapterWOPaging
        rv_user.hasFixedSize()
        rv_user.layoutManager = LinearLayoutManager(applicationContext)
    }

    /*private fun setUpRecyclerWithPaging() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.init(userDao)
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(applicationContext)

        userViewModel.userList?.observe(this, Observer {
            userAdapter.submitList(it)
        })
    }*/
}
