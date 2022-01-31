package com.example.githubusers.view

import com.example.githubusers.presenter.GitUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsersScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun login(user: GitUser): Screen {
        return FragmentScreen { LoginFragment.newInstance(user) }
    }
}