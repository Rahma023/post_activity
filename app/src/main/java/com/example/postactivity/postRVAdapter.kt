package com.example.postactivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class postRVAdapter(var postlist: Context):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return PostViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPostList=postlist.get(position)
        holder.tvuserid.text=currentPostList.userId.toString()
        holder.tvid.text=currentPostList.id.toString()
        holder.tvtitle.text=currentPostList.title
        holder.tvbody.text=currentPostList.body
        holder.cvview.setOnClickListener{
            var intent = Intent(context,viewPostActivity::class.java)
            intent.putExtra("POST_ID",post.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return postlist.size
    }
}

class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvuserid=itemView.findViewById<TextView>(R.id.tvuserid)
    var tvid=itemView.findViewById<TextView>(R.id.tvid)
    var tvtitle=itemView.findViewById<TextView>(R.id.tvtitle)
    var tvbody=itemView.findViewById<TextView>(R.id.tvbody)

    var cvview=itemView.findViewById<CardView>(R.id.cvview)
}
