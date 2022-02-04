package com.example.githubusers.view

import android.os.Bundle
import com.example.githubusers.App
import com.example.githubusers.R
import com.example.githubusers.databinding.ActivityMainBinding
import com.example.githubusers.presenter.MainPresenter
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, UsersScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButton && it.back()) {
                return
            }
        }
        presenter.backClicked()
    }
}