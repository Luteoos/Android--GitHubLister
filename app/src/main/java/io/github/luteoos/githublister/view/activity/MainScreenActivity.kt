package io.github.luteoos.githublister.view.activity

import android.os.Bundle
import android.widget.Toast
import io.github.luteoos.githublister.R
import io.github.luteoos.githublister.baseAbstract.ActivityVM
import io.github.luteoos.githublister.utils.Parameters
import io.github.luteoos.githublister.viewmodel.MainScreenViewModel
import io.github.luteoos.mvvmbaselib.Event
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenActivity : ActivityVM<MainScreenViewModel>() {

    override val viewModel: MainScreenViewModel by viewModel()

    override fun getLayoutID(): Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectToVMMessage()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    override fun onVMMessage(msg: Event<Int>) {
        super.onVMMessage(msg)
        when(msg.get()){
            Parameters.NETWORK_ERROR -> Toast.makeText(this, "No interet",Toast.LENGTH_SHORT).show()
        }
    }

}