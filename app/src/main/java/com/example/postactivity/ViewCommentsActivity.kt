package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCommentsActivity : AppCompatActivity() {
    lateinit var tvTitle1:TextView
    lateinit var tvBody1: TextView
    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comments)
        postId=intent.getIntExtra("POST_ID",0)
        getComments()
    }


    fun getComments() {
        var retrofit = ApiClient.buildService(ApiInterface::class.java)
        var request = retrofit.getComments(postId)


        request.enqueue(object : Callback<List<display_comments>>{
            override fun onResponse(
                call: Call<List<display_comments>>,
                response: Response<List<display_comments>>) {

            }

            override fun onFailure(call: Call<List<display_comments>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()

            }

        })
    }
}