package com.example.githubusers.model

import com.example.githubusers.presenter.GitUser

class GitUsersRepo {
    private val repositories = (0..10).map { GitUser("login $it") }

    fun getUsers(): List<GitUser> {
        return repositories
    }
}