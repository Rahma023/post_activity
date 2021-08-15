package com.example.postactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewPostActivity : AppCompatActivity() {
    lateinit var binding:ActivityViewPostBinding
    var postId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root) //root of the layout
        postId=intent.getIntExtra("POST_ID",0)
        setActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getPost()
        getComments()
    }
//    fun getPost(){
//        var retrofit=ApiClient.buildService(ApiInterface::class.java)
//        var request=retrofit.getPostById(postId)
//        request.enqueue(object : Callback<post>{
//            override fun onResponse(call: Call<post>, response: Response<post>) {
//                if (response.isSuccessful){
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<post>, t: Throwable) {
//                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
//            }
//
//
//        })
//    }

    fun getPost(){
        var retrofit =ApiClient.buildService(ApiInterface::class.java)
        var request = retrofit.getPostById(postId)
        request.enqueue(object :Callback <Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if (response.isSuccessful) {
                    binding.tvTitle1.text = response.body()?.title
                    binding.tvBody1.text = response.body()?.body
                }

            }
            override fun onFailure(call: Call<post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()


            }


        })
    }
    fun getComments() {
        var retrofit = ApiClient.buildService(ApiInterface::class.java)
        var request = retrofit.getComments(postId)


        request.enqueue(object : Callback<List<display_comments>>{
            override fun onResponse(
                call: Call<List<display_comments>>,
                response: Response<List<display_comments>>) {
                if (response.isSuccessful){
                    var comment= response.body()
                    if (comment!=null){
                        var commentsAdapter= RvCommentsAdapter(comment)
                        binding.rvComments.adapter= commentsAdapter
                        binding.rvComments.layoutManager = LinearLayoutManager(baseContext)

                    }
                }
            }
            override fun onFailure(call: Call<List<display_comments>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}
}