package net.gahfy.feedme.ui.youtube


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.gahfy.feedme.R
import net.gahfy.feedme.base.BasePresenter
import net.gahfy.feedme.model.VideoProfile
import net.gahfy.feedme.network.YoutubeApi
import net.gahfy.feedme.utils.extractDate
import javax.inject.Inject


/**
 * Created by Edward on 7/13/2018.
 */
class YoutubePresenter(youtubeView: YoutubeView) : BasePresenter<YoutubeView>(youtubeView) {

    @Inject
    lateinit var youtubeApi: YoutubeApi

    private var disposable: Disposable? = null

    override fun onViewCreated() {
        loadYoutubeVideoList()
    }

    private fun loadYoutubeVideoList() {
        view.showLoading()
        disposable = youtubeApi?.searchVideo("trump")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { youtubeResponse ->
                            val videoList =
                                    youtubeResponse.items.map {
                                        VideoProfile(it.snippet.title,
                                                it.snippet.publishedAt.extractDate(),
                                                it.snippet.thumbnails.high.url,
                                                it.id.videoId)
                                    }
                            view.updateYoutubeVideoList(videoList)
                        },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onViewDestroyed() {
        disposable?.dispose()
    }
}