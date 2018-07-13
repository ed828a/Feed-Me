package net.gahfy.feedme.ui.post

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*
import net.gahfy.feedme.R
import net.gahfy.feedme.base.BaseActivity
import net.gahfy.feedme.model.Post

/**
 * Activity displaying the list of posts
 */
class PostActivity : BaseActivity<PostPresenter>(), PostView {
    /**
     * DataBinding instance
     */
    private lateinit var adapter: PostAdapter

    /**
     * The adapter for the list of posts
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        adapter = PostAdapter(this)
        posts.adapter = adapter
        posts.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePosts(posts: List<Post>) {
        adapter.updatePosts(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }
}