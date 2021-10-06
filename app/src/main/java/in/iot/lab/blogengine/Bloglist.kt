package `in`.iot.lab.blogengine

import `in`.iot.lab.blogengine.R
import `in`.iot.lab.blogengine.adapter.BlogListAdapter
import `in`.iot.lab.blogengine.inteface.GetInterface
import `in`.iot.lab.blogengine.model.BlogListItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Bloglist : Fragment() {
    private val baseUrl="https://myways-backend.herokuapp.com/api/"
    lateinit var blogListRV:RecyclerView



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

        blogCall.enqueue(object:Callback<List<BlogListItem>>{
            override fun onResponse(
                call: Call<List<BlogListItem>>,
                response: Response<List<BlogListItem>>
            ) {
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
        return view
    }
}