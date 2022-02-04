package com.example.githubusers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubusers.App
import com.example.githubusers.databinding.FragmentLoginBinding
import com.example.githubusers.model.GitUsersRepo
import com.example.githubusers.presenter.GitUser
import com.example.githubusers.presenter.UsersPresenter
import com.example.githubusers.presenter.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment : MvpAppCompatFragment(), BackButton, UsersView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GitUsersRepo(), App.instance.router, UsersScreens())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val LOGIN = "login"
        fun newInstance(user: GitUser): MvpAppCompatFragment {
            val bundle = Bundle()
            bundle.putString(LOGIN, user.login)
            return LoginFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun back() = presenter.backPressed()

    override fun init() {
        arguments?.let {
            binding.loginTextView.text = it.getString(LOGIN)
        }
    }

    override fun updateList() {
    }
}