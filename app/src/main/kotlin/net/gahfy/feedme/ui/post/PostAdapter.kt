package net.gahfy.feedme.ui.post

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_post.view.*
import net.gahfy.feedme.R
import net.gahfy.feedme.model.Post

/**
 * Adapter for the list of the posts
 * @property context Context in which the application is running
 */
class PostAdapter(private val context: Context) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    /**
     * The list of posts of the adapter
     */
    private var posts = arrayListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("PostAdapter", "posts.count = ${posts.count()}")
        return posts.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {
        holder?.bind(posts[position])
    }


    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePosts(newPosts: List<Post>) {
        this.posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle = itemView.post_title
        private val textBody = itemView.post_body

        /**
         * Binds a post into the view
         */
        fun bind(post: Post) {
            with(post) {
                textTitle.text = title
                textBody.text = body
            }
        }
    }
}