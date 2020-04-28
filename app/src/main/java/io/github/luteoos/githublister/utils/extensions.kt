package io.github.luteoos.githublister.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.realm.Realm
import io.realm.RealmObject

fun <T : RealmObject> T.saveOrUpdateToRealm(): T? {
    Realm.getDefaultInstance().use { db ->
        db.executeTransaction {
            it.copyToRealmOrUpdate(this)
        }
        db.refresh()
    }
    return this
}

fun ImageView.loadAvatar(url: String){
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontAnimate()
        .into(this)
}