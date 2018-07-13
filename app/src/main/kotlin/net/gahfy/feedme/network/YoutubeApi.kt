package net.gahfy.feedme.network

import io.reactivex.Observable
import net.gahfy.feedme.model.YoutubeResponse
import net.gahfy.feedme.utils.API_KEY
import net.gahfy.feedme.utils.NETWORK_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Edward on 7/13/2018.
 */
interface YoutubeApi {
    @GET("search")
    fun searchVideo(@Query("q") query: String = "",
                    @Query("pageToken") pageToken: String ="",
                    @Query("part") part: String = "snippet",
                    @Query("maxResults") maxResults: String = "$NETWORK_PAGE_SIZE",
                    @Query("type") type: String = "video",
                    @Query("key") key: String = API_KEY): Observable<YoutubeResponse>
}