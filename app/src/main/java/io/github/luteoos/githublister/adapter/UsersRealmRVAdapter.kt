package io.github.luteoos.githublister.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.githublister.R
import io.github.luteoos.githublister.data.android.realm.GithubUserRealm
import io.github.luteoos.githublister.utils.loadAvatar
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults

class UsersRealmRVAdapter(data: RealmResults<GithubUserRealm>?) : RealmRecyclerViewAdapter<GithubUserRealm, UsersRealmRVAdapter.UserVH>(data, true, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH =
        UserVH(LayoutInflater.from(parent.context).inflate(R.layout.rv_user, parent, false))

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        data?.get(position)?.let {user ->
            holder.tvUsername.text = user.login
            holder.ivAvatar.loadAvatar(user.avatar_url ?: "")
            holder.rvRepos.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RepoRealmRVAdapter(user.repos)
            }
        }
    }

    inner class UserVH(view: View) : RecyclerView.ViewHolder(view){
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
        val rvRepos = view.findViewById<RecyclerView>(R.id.list_repo)
    }
}
