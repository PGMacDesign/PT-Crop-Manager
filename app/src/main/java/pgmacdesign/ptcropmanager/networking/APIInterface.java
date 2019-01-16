package pgmacdesign.ptcropmanager.networking;

import com.pgmacdesign.pgmactips.datamodels.SamplePojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    //region Path and URL Strings

    String PATH_CROPS = "/crops";
    String PATH_GARDENS = "/gardens";
    String PATH_PLANTINGS = "/plantings";
    String PATH_SEEDS = "/seeds";
    String PATH_POSTS = "/posts";
    String PATH_COMMENTS = "/comments";
    String PATH_PHOTOS = "/photos";
    String PATH_PLACES = "/places";

    String PATH_OWNER = "/owner";
    String PATH_POSTS_OWNER = "/author";

    String DOT_JSON = ".json";

    //endregion

    //region Crops

    /**
     * Get all crops
     * @return
     */
    @GET(PATH_CROPS + DOT_JSON)
    Call<SamplePojo> getCrops();

    /**
     * Get a Single Crop
     * @param id
     * @return
     */
    @GET(PATH_CROPS + "/{id}" + DOT_JSON)
    Call<SamplePojo> getCrop(@Path("id") String id);

    //endregion

    //region Gardens

    //endregion

    //region Plantings

    //endregion

    //region Seeds

    //endregion

    //region Posts

    //endregion

    //region Comments

    //endregion

    //region Photos

    //endregion

    //region Places

    //endregion

}
