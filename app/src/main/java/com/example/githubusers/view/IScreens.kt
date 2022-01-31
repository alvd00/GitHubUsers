package com.example.githubusers.view

import com.example.githubusers.presenter.GitUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun login(user: GitUser): Screen
}