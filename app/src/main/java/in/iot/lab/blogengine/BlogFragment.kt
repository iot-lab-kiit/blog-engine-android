package `in`.iot.lab.blogengine

import `in`.iot.lab.blogengine.model.Blogitem
import android.icu.text.CaseMap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class BlogFragment : Fragment() {
    lateinit var title:TextView
    lateinit var content:TextView
    lateinit var imageView:ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_blog, container, false)
        title=view.findViewById(R.id.blog_title)
        content=view.findViewById(R.id.blog_content)
        imageView=view.findViewById(R.id.blog_image)

        title.text= Blogitem.title
        content.text=Blogitem.content
        Glide.with(requireContext()).load(Blogitem.image).into(imageView)

        return view
    }
}