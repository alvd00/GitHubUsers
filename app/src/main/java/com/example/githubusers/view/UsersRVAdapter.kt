package com.example.githubusers.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.FragmentUserBinding
import com.example.githubusers.presenter.IUserListPresenter

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            FragmentUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { posit = position })

    inner class ViewHolder(private val binding: FragmentUserBinding) :
        RecyclerView.ViewHolder(binding.root), IUserView {

        override var posit = -1

        override fun setLogin(text: String) = with(binding) {
            textViewLogin.text = text
        }

    }
}
