package com.example.githubusers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.App.Navigator.navigatorHolder
import com.example.githubusers.App.Navigator.router
import com.example.githubusers.R
import com.example.githubusers.databinding.ActivityMainBinding
import com.example.githubusers.presenter.MainPresenter
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.container)

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(router, UsersScreens()) }//FIXME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButton && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}