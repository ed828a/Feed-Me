package net.gahfy.feedme.ui.youtube

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_youtube.view.*
import net.gahfy.feedme.R
import net.gahfy.feedme.model.VideoProfile


/**
 * Created by Edward on 7/13/2018.
 */
class YoutubeAdapter : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {
    val videoList = arrayListOf<VideoProfile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_youtube, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.count()
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        holder?.bind(videoList[position])
    }

    fun updateYoutubeList(newList: List<VideoProfile>){
        videoList.addAll(newList)
        notifyDataSetChanged()
    }
    inner class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageThumbnail = itemView.imageViewThumb
        val textTitle = itemView.textViewTitle
        val textDate = itemView.textViewDate

        fun bind(video: VideoProfile){
            textDate.text = video.date
            textTitle.text = video.title
            Glide.with(itemView.context).load(video.thumbnail).into(imageThumbnail)
        }

    }
}