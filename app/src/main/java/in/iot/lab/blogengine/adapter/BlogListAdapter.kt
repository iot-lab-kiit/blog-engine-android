package `in`.iot.lab.blogengine.adapter

import `in`.iot.lab.blogengine.R
import `in`.iot.lab.blogengine.model.BlogListItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BlogListAdapter(var blogList:List<BlogListItem>,var context: Context): RecyclerView.Adapter<BlogListAdapter.BloglistViewHolder>() {




    class BloglistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title=itemView.findViewById<TextView>(R.id.blog_title)
        var data=itemView.findViewById<TextView>(R.id.blog_data)
        var imageView=itemView.findViewById<ImageView>(R.id.blog_pic)

        fun bind(posts:BlogListItem,context: Context){
            title.text=posts.title
            data.text=posts.content.subSequence(0,50).toString()+"..."
            Glide.with(context).load(posts.image).into(imageView)
//            imageView.setImageURI(Uri.parse(posts.imageUrl))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloglistViewHolder {
        return BloglistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.blog_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: BloglistViewHolder, position: Int) {
        holder.bind(blogList[position],context)
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

}