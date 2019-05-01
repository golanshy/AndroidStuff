package co.applylogic.android.interview.examples.example3.api;

/**
 * Created by golanshy on 03/11/2016. (iRiS SoftwareSystems)
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiInterface {

    @Headers({"Content-Type: application/json"})
    @GET("/3/movie/popular")
    Call<ResponseBody> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);
}



