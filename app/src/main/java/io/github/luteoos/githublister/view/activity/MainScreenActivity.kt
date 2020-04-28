package io.github.luteoos.githublister.view.activity

import android.os.Bundle
import io.github.luteoos.githublister.R
import io.github.luteoos.githublister.baseAbstract.ActivityVM
import io.github.luteoos.githublister.viewmodel.MainScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenActivity : ActivityVM<MainScreenViewModel>() {

    override val viewModel: MainScreenViewModel by viewModel()

    override fun getLayoutID(): Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}