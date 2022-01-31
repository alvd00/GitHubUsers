package com.example.githubusers.presenter

import com.example.githubusers.model.GitUsersRepo
import com.example.githubusers.view.IScreens
import com.example.githubusers.view.IUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GitUsersRepo,
    private val router: Router,
    private val screen: IScreens
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        override  var itemClickListener: ((IUserView) -> Unit)? = null

        val users = mutableListOf<GitUser>()
        override fun getCount() = users.size
        override fun bindView(view: IUserView) {
            val user = users[view.posit]
            view.setLogin(user.user_login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            router.navigateTo(screen.login(usersListPresenter.users[it.posit]))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}