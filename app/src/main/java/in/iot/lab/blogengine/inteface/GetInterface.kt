package `in`.iot.lab.blogengine.inteface

import `in`.iot.lab.blogengine.model.BlogListItem
import retrofit2.Call
import retrofit2.http.GET

interface GetInterface {
    @GET("getblogs")
    fun getBlogs():Call<List<BlogListItem>>
}