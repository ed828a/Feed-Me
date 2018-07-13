package net.gahfy.feedme.ui.youtube

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_youtube.*
import kotlinx.android.synthetic.main.content_youtube.*
import net.gahfy.feedme.R
import net.gahfy.feedme.base.BaseActivity
import net.gahfy.feedme.model.VideoProfile

class YoutubeActivity : BaseActivity<YoutubePresenter>(), YoutubeView {

    private lateinit var adapter: YoutubeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        adapter = YoutubeAdapter()
        videoListView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        presenter.onViewCreated()
    }

    override fun instantiatePresenter(): YoutubePresenter {
        return YoutubePresenter(this)
    }

    override fun updateYoutubeVideoList(videos: List<VideoProfile>) {
        adapter.updateYoutubeList(videos)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }
}
