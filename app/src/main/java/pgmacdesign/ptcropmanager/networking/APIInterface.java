package pgmacdesign.ptcropmanager.networking;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API Interface defines the paths and routes to match the web api
 */
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
    String PATH_MEMBERS = "/members";

    String PATH_ID = "/{id}";
    String PATH_OWNER_ID = "/{ownerId}";

    String DOT_JSON = ".json";

    //endregion

    //region Crops

    /**
     * Get all crops (Paginated in sets)
     */
    @GET(PATH_CROPS + DOT_JSON)
    Call<ResponseBody> getCrops(@Query(value = "page") Integer page);

    /**
     * Get a Single Crop
     *
     * @param id
     */
    @GET(PATH_CROPS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getCrop(@Path("id") String id);

    //endregion

    //region Gardens

    /**
     * Get all Gardens (Paginated in sets)
     */
    @GET(PATH_GARDENS + DOT_JSON)
    Call<ResponseBody> getGardens(@Query(value = "page") Integer page);

    /**
     * Get a Single Garden
     *
     * @param id
     */
    @GET(PATH_GARDENS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getGarden(@Path("id") String id);

    /**
     * NOTE! API Lists this as working, but does not seem to be. Will omit from API Calls for now
     * Get a Garden Owner
     *
     * @param ownerId owner id
     */
    @GET(PATH_GARDENS + PATH_OWNER_ID + DOT_JSON)
    Call<ResponseBody> getGardenOwner(@Path("ownerId") String ownerId);

    //endregion

    //region Plantings

    /**
     * Get all Plantings (Paginated in sets)
     */
    @GET(PATH_PLANTINGS + DOT_JSON)
    Call<ResponseBody> getPlantings(@Query(value = "page") Integer page);

    /**
     * Get a Single Planting
     *
     * @param id
     */
    @GET(PATH_PLANTINGS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getPlanting(@Path("id") String id);

    /**
     * NOTE! API Lists this as working, but does not seem to be. Will omit from API Calls for now
     * Get a Planting Owner
     *
     * @param ownerId owner id
     */
    @GET(PATH_PLANTINGS + PATH_OWNER_ID + DOT_JSON)
    Call<ResponseBody> getPlantingOwner(@Path("ownerId") String ownerId);

    //endregion

    //region Seeds

    /**
     * Get all Seeds (Paginated in sets)
     */
    @GET(PATH_SEEDS + DOT_JSON)
    Call<ResponseBody> getSeeds(@Query(value = "page") Integer page);

    /**
     * Get a Single Garden
     *
     * @param id
     */
    @GET(PATH_SEEDS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getSeed(@Path("id") String id);

    /**
     * NOTE! API Lists this as working, but does not seem to be. Will omit from API Calls for now
     * Get a Seed Owner
     *
     * @param ownerId owner id
     */
    @GET(PATH_SEEDS + PATH_OWNER_ID + DOT_JSON)
    Call<ResponseBody> getSeedOwner(@Path("ownerId") String ownerId);

    //endregion

    //region Posts

    /**
     * Get all Posts (Paginated in sets)
     */
    @GET(PATH_POSTS + DOT_JSON)
    Call<ResponseBody> getPosts(@Query(value = "page") Integer page);

    /**
     * Get a Single Post
     *
     * @param id
     */
    @GET(PATH_POSTS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getPost(@Path("id") String id);

    /**
     * NOTE! API Lists this as working, but does not seem to be. Will omit from API Calls for now
     * Get a Post Owner
     *
     * @param ownerId owner id
     */
    @GET(PATH_POSTS + PATH_OWNER_ID + DOT_JSON)
    Call<ResponseBody> getPostOwner(@Path("ownerId") String ownerId);

    //endregion

    //region Comments

    /**
     * Get all Comments (Paginated in sets)
     */
    @GET(PATH_COMMENTS + DOT_JSON)
    Call<ResponseBody> getComments(@Query(value = "page") Integer page);

    /**
     * Get a Single Comment
     *
     * @param id
     */
    @GET(PATH_COMMENTS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getComment(@Path("id") String id);

    //endregion

    //region Photos

    /**
     * Get all Photos (Paginated in sets)
     */
    @GET(PATH_PHOTOS + DOT_JSON)
    Call<ResponseBody> getPhotos(@Query(value = "page") Integer page);

    /**
     * Get a Single Photo
     *
     * @param id
     */
    @GET(PATH_PHOTOS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getPhoto(@Path("id") String id);

    //endregion

    //region Places

    /**
     * Get all Places (Paginated in sets)
     */
    @GET(PATH_PLACES + DOT_JSON)
    Call<ResponseBody> getPlaces(@Query(value = "page") Integer page);

    /**
     * Get a Single Place
     *
     * @param id
     */
    @GET(PATH_PLACES + PATH_ID + DOT_JSON)
    Call<ResponseBody> getPlace(@Path("id") String id);

    //endregion

    //region Members

    /**
     * Get all Members (Paginated in sets)
     */
    @GET(PATH_MEMBERS + DOT_JSON)
    Call<ResponseBody> getMembers(@Query(value = "page") Integer page);

    /**
     * Get a Single Member
     *
     * @param id
     */
    @GET(PATH_MEMBERS + PATH_ID + DOT_JSON)
    Call<ResponseBody> getMember(@Path("id") String id);

    //endregion
}
