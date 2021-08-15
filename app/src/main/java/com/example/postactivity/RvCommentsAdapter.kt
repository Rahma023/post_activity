package com.example.postactivity

class RvCommentsAdapter(var commentsList: List<display_comments>):RecyclerView.Adapter<CommentsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.activity_view_comments,parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentcomment=commentsList.get(position)
        holder.tvCommentName.text=currentcomment.name
        holder.tvCommentBody.text=currentcomment.body
        holder.tvCommentEmail.text=currentcomment.email
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}


class CommentsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvCommentName=itemView.findViewById<TextView>(R.id.tvCommentsName)
    var tvCommentEmail=itemView.findViewById<TextView>(R.id.tvCommentEmail)
    var tvCommentBody=itemView.findViewById<TextView>(R.id.tvCommentBody)

}