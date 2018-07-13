package net.gahfy.feedme.ui.youtube


import android.support.annotation.StringRes
import net.gahfy.feedme.base.BaseView
import net.gahfy.feedme.model.VideoProfile


/**
 * Created by Edward on 7/13/2018.
 */
interface YoutubeView: BaseView {

    fun updateYoutubeVideoList(videos: List<VideoProfile>)

    fun showError(error: String)
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    fun showLoading()

    fun hideLoading()
}