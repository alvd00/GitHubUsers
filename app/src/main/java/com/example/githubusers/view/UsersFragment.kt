package com.example.githubusers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.App
import com.example.githubusers.databinding.FragmentUsersBinding
import com.example.githubusers.model.GitUsersRepo
import com.example.githubusers.presenter.UsersPresenter
import com.example.githubusers.presenter.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButton {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GitUsersRepo(), App.instance.router, UsersScreens())
    }
    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.run {
            this.recyclerViewUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersRVAdapter(presenter.usersListPresenter)
            this.recyclerViewUsers.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun back() = presenter.backPressed()
}