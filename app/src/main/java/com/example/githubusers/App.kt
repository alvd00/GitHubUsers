package com.example.githubusers

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object Navigator{
        private val cicerone : Cicerone<Router> by lazy {
            Cicerone.create()
        }
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
    }
}