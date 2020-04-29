package io.github.luteoos.githublister.view.activity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.luteoos.githublister.R
import io.github.luteoos.githublister.adapter.UsersRealmRVAdapter
import io.github.luteoos.githublister.baseAbstract.ActivityVM
import io.github.luteoos.githublister.data.android.GithubUsersWrapper
import io.github.luteoos.githublister.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenActivity : ActivityVM<MainScreenViewModel>() {

    override val viewModel: MainScreenViewModel by viewModel()
    private val adapter: UsersRealmRVAdapter by inject()

    override fun getLayoutID(): Int = R.layout.activity_main_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setRVAdapter()
    }

    override fun onResume() {
        super.onResume()
            viewModel.getData(isNetworkAvailable())
    }

    private fun setBindings(){
        viewModel.getUsersLiveData().observe(this, Observer { handleGithubData(it) })
    }

    private fun setRVAdapter(){
        rv_users.apply {
            addItemDecoration(DividerItemDecoration(this@MainScreenActivity, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainScreenActivity)
            adapter = this@MainScreenActivity.adapter
        }
    }

    private fun handleGithubData(wrap: GithubUsersWrapper){
        when(wrap.isSuccess){
            true -> adapter.updateData(wrap.list!!)
            false -> showErrorSnackBar()
        }
    }

    private fun showErrorSnackBar(){
        Snackbar.make(layout, R.string.connection_error_message, Snackbar.LENGTH_INDEFINITE)
            .setActionTextColor(getColor(R.color.red))
            .setAction(R.string.retry){
                viewModel.getData(isNetworkAvailable())
            }
            .show()
    }

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(): Boolean {
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {cm ->
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected ?: false
        }
    }
}