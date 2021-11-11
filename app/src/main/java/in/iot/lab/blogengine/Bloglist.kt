package `in`.iot.lab.blogengine

import `in`.iot.lab.blogengine.adapter.BlogListAdapter
import `in`.iot.lab.blogengine.inteface.GetInterface
import `in`.iot.lab.blogengine.model.BlogListItem
import `in`.iot.lab.blogengine.util.Companion.BASE_URL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Bloglist : Fragment() {

    lateinit var blogListRV:RecyclerView

    lateinit var blogCall:Call<List<BlogListItem>>

    val scope = CoroutineScope(Dispatchers.IO + CoroutineName("retrofit"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_bloglist, container, false)

        blogListRV=view.findViewById(R.id.recycler_list)
        val retroFit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val blogsPlaceHolder=retroFit.create(GetInterface::class.java)
        blogCall=blogsPlaceHolder.getBlogs()

        scope.launch {
            enginestart()
        }
        return view
    }

    suspend fun enginestart()
    {
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
    }
}