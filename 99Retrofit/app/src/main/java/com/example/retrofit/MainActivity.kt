package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit 을 사용하기전 두가지의 준비사항이 필요함
// 1. 데이터를 가져올곳(웹 or api)을 결정
// 2. 어떤 데이터를 사용할것인지 데이터의 형식을 결정

// retrofit이란? http통신을 할려면 httpURLConnection을 구현해야함
// 근데 그게 쥰나 복잡하고 힘드니까 쉽게 해주는애가 Retrofit
// 그럼 GSON은?
// GSON 은 api나 http 통신을 하고있는데 클라이언트 쪽에서 받은 자료가 JSON이면 그걸 해독해주는 친구

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

        val adapter = CustomAdapter()
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        mBinding.buttonRequest.setOnClickListener {
            val gitHubService = retrofit.create(GitHubService::class.java)
            gitHubService.users().enqueue(object : Callback<List<RepositoryItem>>{
                override fun onResponse(call: Call<List<RepositoryItem>>, response: Response<List<RepositoryItem>>
                ) {
                    adapter.userList.addAll(response.body() as List<RepositoryItem>)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}