package io.github.luteoos.githublister.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.githublister.R
import io.github.luteoos.githublister.data.android.realm.GithubRepoRealm
import io.realm.RealmList
import io.realm.RealmRecyclerViewAdapter

class RepoRealmRVAdapter(data: RealmList<GithubRepoRealm>?) : RealmRecyclerViewAdapter<GithubRepoRealm, RepoRealmRVAdapter.RepoVH>(data, true, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoVH =
        RepoVH(LayoutInflater.from(parent.context).inflate(R.layout.rv_repo, parent, false))


    override fun onBindViewHolder(holder: RepoVH, position: Int) {
        data?.get(position)?.let {repo ->
            holder.tvName.text = repo.name
        }
    }

    inner class RepoVH(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tvName)
    }
}