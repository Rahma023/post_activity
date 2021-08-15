package com.example.postactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvrecycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchPost()
    }
    fun fetchPost(){
        var retrofit=ApiClient.buildService(ApiInterface::class.java)
        var request=retrofit.getPosts()
        request.enqueue(object : Callback<List<post>> {
            override fun onResponse(call: Call<List<post>>, response: Response<List<post>>) {
                if (response.isSuccessful){
                    var postsList=response.body()!!
                    rvrecycler=findViewById(R.id.rvrecycler)
                    var adapter=postRVAdapter(baseContext,postsList)
                    rvrecycler.adapter=adapter
//                    if (postsList!=null){
//                        var adapter=postRVAdapter(postsList)
//                        rvrecycler.adapter=adapter
//                    }
//                    rvrecycler=findViewById(R.id.rvrecycler)
//                   rvrecycler.layoutManager=LinearLayoutManager(baseContext)
//                    val adapter=postRVAdapter(baseContext,postsList)
//                    rvrecycler.adapter=adapter

                    Toast.makeText(baseContext,postsList!!.size.toString(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
}