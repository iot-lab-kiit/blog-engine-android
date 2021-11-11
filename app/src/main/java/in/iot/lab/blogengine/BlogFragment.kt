package `in`.iot.lab.blogengine

import `in`.iot.lab.blogengine.databinding.FragmentBlogBinding
import `in`.iot.lab.blogengine.model.Blogitem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class BlogFragment : Fragment() {


    private var fragmentBlogBinding: FragmentBlogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentBlogBinding.inflate(inflater, container, false)
        fragmentBlogBinding = binding

        binding.blogTitle.text= Blogitem.title
        binding.blogContent.text=Blogitem.content
        Glide.with(requireContext()).load(Blogitem.image).into(binding.blogImage)

        return binding.root
    }
}