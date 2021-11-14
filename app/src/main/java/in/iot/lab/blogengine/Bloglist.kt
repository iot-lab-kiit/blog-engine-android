package `in`.iot.lab.blogengine

import `in`.iot.lab.blogengine.adapter.BlogListAdapter
import `in`.iot.lab.blogengine.database.model.item
import `in`.iot.lab.blogengine.database.viewmodel.userviewmodel
import `in`.iot.lab.blogengine.inteface.GetInterface
import `in`.iot.lab.blogengine.model.BlogListItem
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Bloglist : Fragment() {
    private val baseUrl="https://blog-backend-iot.herokuapp.com/api/"
    lateinit var blogListRV:RecyclerView

    lateinit var userviewmodel: userviewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_bloglist, container, false)
        blogListRV=view.findViewById(R.id.recycler_list)
        val retroFit= Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val blogsPlaceHolder=retroFit.create(GetInterface::class.java)
        val blogCall=blogsPlaceHolder.getBlogs()

        userviewmodel = ViewModelProvider(this).get(`in`.iot.lab.blogengine.database.viewmodel.userviewmodel::class.java)

        var read = userviewmodel.readalldata
        var user = read.value

        userviewmodel.readalldata.observe(viewLifecycleOwner, Observer {user ->

            if(user.isEmpty())
            {
                Log.d("data","empty")
                blogCall.enqueue(object:Callback<List<BlogListItem>>{
                    override fun onResponse(
                        call: Call<List<BlogListItem>>,
                        response: Response<List<BlogListItem>>
                    ) {
                        for(i in response.body()!!.indices)
                        {
                            val title = response.body()!!.get(i).title
                            val data = response.body()!!.get(i).content
                            val image = response.body()!!.get(i).image

                            val user1 = item(0,title,data,image)
                            userviewmodel.adduser(user1)
                        }
                        val posts=response.body()!!
                        val navController=findNavController()
                        blogListRV.apply {
                            layoutManager=LinearLayoutManager(context)
                            adapter=BlogListAdapter(posts,context,navController)
                        }
                    }

                    override fun onFailure(call: Call<List<BlogListItem>>, t: Throwable) {
                        Toast.makeText(context,t.message.toString(),Toast.LENGTH_LONG).show()
                    }

                })
            }
            else
            {
                var templist = mutableListOf<BlogListItem>()

                for(i in user.indices)
                {
                    Log.d("data", user.get(i).title)
                    templist.add(BlogListItem(user.get(i).title,user.get(i).data,user.get(i).image))
                }

                for(i in templist.indices)
                {
                    Log.d("data of templist", templist.get(i).title)
                }

                Log.d("test", "working fine")

                val navController=findNavController()
                blogListRV.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = BlogListAdapter(templist, context, navController)
                }
            }
        })

        return view
    }

    override fun onDestroy() {
        userviewmodel.deleteallusers()
        super.onDestroy()
    }

    override fun onStop() {
        userviewmodel.deleteallusers()
        super.onStop()
    }
}