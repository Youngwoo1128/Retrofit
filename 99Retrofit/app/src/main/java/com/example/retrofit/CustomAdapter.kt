package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter : RecyclerView.Adapter<Holder>() {

    var userList = mutableListOf<RepositoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int = userList.size

}

class Holder(itemView: View): RecyclerView.ViewHolder(itemView){

    val textName = itemView.findViewById<TextView>(R.id.textName)
    val textId = itemView.findViewById<TextView>(R.id.textId)
    val img = itemView.findViewById<ImageView>(R.id.imageAvatar)
    fun setUser(user: RepositoryItem){
        textName.text = user.name
        textId.text = user.node_id
        Glide.with(itemView).load(user.owner.avatar_url).into(img)
    }
}