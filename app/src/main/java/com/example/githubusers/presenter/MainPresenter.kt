package com.example.githubusers.presenter

import com.example.githubusers.view.IScreens
import com.example.githubusers.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screen: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun back() {
        router.exit()
    }
}