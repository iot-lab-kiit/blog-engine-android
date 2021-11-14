package `in`.iot.lab.blogengine.adapter

import `in`.iot.lab.blogengine.Bloglist
import `in`.iot.lab.blogengine.R
import `in`.iot.lab.blogengine.database.viewmodel.userviewmodel
import `in`.iot.lab.blogengine.model.Blogitem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.NonDisposableHandle.parent
import `in`.iot.lab.blogengine.model.BlogListItem as BlogListItem1

class BlogListAdapter(var blogList:List<BlogListItem1>, var context: Context, var navController: NavController):
    RecyclerView.Adapter<BlogListAdapter.BloglistViewHolder>() {

    class BloglistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title=itemView.findViewById<TextView>(R.id.blog_title)
        var data=itemView.findViewById<TextView>(R.id.blog_data)
        var imageView=itemView.findViewById<ImageView>(R.id.blog_pic)
        lateinit var navController:NavController
        lateinit var listItem: BlogListItem1

        fun bind(posts: BlogListItem1, context: Context, navController: NavController){
            title.text=posts.title
            data.text=posts.content.subSequence(0,50).toString()+"..."
            Glide.with(context).load(posts.image).into(imageView)
            this.navController=navController
            listItem=posts
        }
        init{
            itemView.setOnClickListener{
                navController.navigate(R.id.action_bloglist_to_blogFragment)
                Blogitem.title=listItem.title
                Blogitem.content=listItem.content
                Blogitem.image=listItem.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloglistViewHolder {
        return BloglistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.blog_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: BloglistViewHolder, position: Int) {
        holder.bind(blogList[position],context,navController)
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}