package pgmacdesign.ptcropmanager.networking;

import com.pgmacdesign.pgmactips.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConstants;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.RetrofitClient;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.RetrofitParser;
import com.pgmacdesign.pgmactips.utilities.NumberUtilities;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Comment;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Crop;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.ErrorModel;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Garden;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Member;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Place;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Planting;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Post;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Seed;
import pgmacdesign.ptcropmanager.misc.Constants;
import retrofit2.Call;

/**
 * Static API Calls for use throughout the app. This can be refactored into an SDK with minimal
 * effort should one ever be desired
 */
public class APICalls {

    /**
     * Interface method for use with {@link retrofit2.Retrofit}
     */
    private static APIInterface service;

    static {
        if (APICalls.service == null) {
            RetrofitClient.Builder builder = new RetrofitClient.Builder(APIInterface.class, Constants.API_BASE_URL);
            builder.setLogLevel(HttpLoggingInterceptor.Level.BODY); //todo switch to dynamic once launching
            builder.setTimeouts((int) (PGMacTipsConstants.ONE_MINUTE), (int) (PGMacTipsConstants.ONE_MINUTE));
            APICalls.service = builder.build().buildServiceClient();
        }
    }

    //region Crops

    /**
     * Get the list of {@link Crop}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_CROPS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getCrops(@NonNull final OnTaskCompleteListener listener,
                                @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getCrops(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_CROPS:
                        listener.onTaskComplete((List<Crop>) result, Constants.TAG_LIST_OF_CROPS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Crop.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_CROPS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Crop} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_CROP
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSingleCrop(@NonNull final OnTaskCompleteListener listener,
                                     @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getCrop(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_CROP:
                        listener.onTaskComplete((Crop) result, Constants.TAG_CROP);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Crop.getType(), ErrorModel.getType(), Constants.TAG_CROP, Constants.TAG_ERROR_MODEL);
    }


    //endregion

    //region Gardens


    /**
     * Get the list of {@link Garden}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_GARDENS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getGardens(@NonNull final OnTaskCompleteListener listener,
                                  @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getGardens(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_GARDENS:
                        listener.onTaskComplete((List<Garden>) result, Constants.TAG_LIST_OF_GARDENS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Garden.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_GARDENS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single Garden referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_GARDEN
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSingleGarden(@NonNull final OnTaskCompleteListener listener,
                                       @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getGarden(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_CROP:
                        listener.onTaskComplete((Garden) result, Constants.TAG_GARDEN);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Garden.getType(), ErrorModel.getType(), Constants.TAG_GARDEN, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Plantings


    /**
     * Get the list of {@link Planting}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_PLANTINGS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getPlantings(@NonNull final OnTaskCompleteListener listener,
                                    @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getPlantings(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_PLANTINGS:
                        listener.onTaskComplete((List<Planting>) result, Constants.TAG_LIST_OF_PLANTINGS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Planting.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_PLANTINGS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Planting} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_PLANTING
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSinglePlanting(@NonNull final OnTaskCompleteListener listener,
                                         @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getPlanting(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_PLANTING:
                        listener.onTaskComplete((Planting) result, Constants.TAG_PLANTING);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Planting.getType(), ErrorModel.getType(), Constants.TAG_PLANTING, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Seeds

    /**
     * Get the list of {@link Seed}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_SEEDS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSeeds(@NonNull final OnTaskCompleteListener listener,
                                @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getSeeds(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_SEEDS:
                        listener.onTaskComplete((List<Seed>) result, Constants.TAG_LIST_OF_SEEDS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Seed.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_SEEDS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Seed} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_SEED
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSingleSeed(@NonNull final OnTaskCompleteListener listener,
                                     @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getSeed(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_SEED:
                        listener.onTaskComplete((Planting) result, Constants.TAG_SEED);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Seed.getType(), ErrorModel.getType(), Constants.TAG_SEED, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Posts


    /**
     * Get the list of {@link Post}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_POSTS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getPosts(@NonNull final OnTaskCompleteListener listener,
                                @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getPosts(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_POSTS:
                        listener.onTaskComplete((List<Post>) result, Constants.TAG_LIST_OF_POSTS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Post.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_POSTS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Post} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_POST
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSinglePost(@NonNull final OnTaskCompleteListener listener,
                                     @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getPost(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_POST:
                        listener.onTaskComplete((Post) result, Constants.TAG_POST);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Post.getType(), ErrorModel.getType(), Constants.TAG_POST, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Comments


    /**
     * Get the list of {@link Comment}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_COMMENTS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getComments(@NonNull final OnTaskCompleteListener listener,
                                   @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getComments(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_COMMENTS:
                        listener.onTaskComplete((List<Comment>) result, Constants.TAG_LIST_OF_COMMENTS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Comment.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_COMMENTS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Comment} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_COMMENT
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSingleComment(@NonNull final OnTaskCompleteListener listener,
                                        @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getComment(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_COMMENT:
                        listener.onTaskComplete((Comment) result, Constants.TAG_COMMENT);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Comment.getType(), ErrorModel.getType(), Constants.TAG_COMMENT, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Photos


    /**
     * Get the list of {@link Photo}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_PHOTOS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getPhotos(@NonNull final OnTaskCompleteListener listener,
                                 @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getPhotos(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_PHOTOS:
                        listener.onTaskComplete((List<Photo>) result, Constants.TAG_LIST_OF_PHOTOS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Photo.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_PHOTOS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Photo} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_PHOTO
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSinglePhoto(@NonNull final OnTaskCompleteListener listener,
                                      @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getPhoto(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_PHOTO:
                        listener.onTaskComplete((Photo) result, Constants.TAG_PHOTO);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Photo.getType(), ErrorModel.getType(), Constants.TAG_PHOTO, Constants.TAG_ERROR_MODEL);
    }

    //endregion

    //region Places

    /**
     * Get the list of {@link Place}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_PLACES
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getPlaces(@NonNull final OnTaskCompleteListener listener,
                                 @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getPlaces(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_PLACES:
                        listener.onTaskComplete((List<Place>) result, Constants.TAG_LIST_OF_PLACES);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Place.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_PLACES, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Place} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_PLACE
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSinglePlace(@NonNull final OnTaskCompleteListener listener,
                                      @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getPlace(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_PLACE:
                        listener.onTaskComplete((Place) result, Constants.TAG_PLACE);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Place.getType(), ErrorModel.getType(), Constants.TAG_PLACE, Constants.TAG_ERROR_MODEL);
    }


    //endregion

    //region Members

    /**
     * Get the list of {@link Member}
     *
     * @param listener   {@link OnTaskCompleteListener}
     * @param pageNumber Page number, if null, will default to 1
     *                   Return callback will be one of these 3 tags:
     *                   1) Constants.TAG_LIST_OF_MEMBERS
     *                   2) Constants.TAG_ERROR_MODEL
     *                   3) Constants.TAG_API_CALL_ERROR
     */
    public static void getMembers(@NonNull final OnTaskCompleteListener listener,
                                  @Nullable Integer pageNumber) {
        Call<ResponseBody> call = service.getMembers(
                (NumberUtilities.getInt(pageNumber) <= 0) ? 1 : pageNumber);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_LIST_OF_MEMBERS:
                        listener.onTaskComplete((List<Member>) result, Constants.TAG_LIST_OF_MEMBERS);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Member.getListType(), ErrorModel.getType(), Constants.TAG_LIST_OF_MEMBERS, Constants.TAG_ERROR_MODEL);
    }


    /**
     * Get a single {@link Member} referencing an ID or slug
     *
     * @param listener {@link OnTaskCompleteListener}
     * @param idOrSlug The ID to search or the 'slug' (Unique identifier in a category) to utilize
     *                 Return callback will be one of these 3 tags:
     *                 1) Constants.TAG_MEMBER
     *                 2) Constants.TAG_ERROR_MODEL
     *                 3) Constants.TAG_API_CALL_ERROR
     */
    public static void getSingleMember(@NonNull final OnTaskCompleteListener listener,
                                       @NonNull String idOrSlug) {
        Call<ResponseBody> call = service.getMember(idOrSlug);
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                String errorText;
                switch (customTag) {
                    case Constants.TAG_MEMBER:
                        listener.onTaskComplete((Member) result, Constants.TAG_MEMBER);
                        return;
                    case Constants.TAG_ERROR_MODEL:
                        listener.onTaskComplete((ErrorModel) result, Constants.TAG_ERROR_MODEL);
                        return;
                    case RetrofitParser.TAG_RETROFIT_CALL_ERROR:
                        errorText = Constants.GENERIC_UNKNOWN_ERROR_STR;
                        break;
                    default:
                    case RetrofitParser.TAG_RETROFIT_PARSE_ERROR:
                        errorText = Constants.GENERIC_ERROR_STR + ((String) result);
                        break;
                }
                listener.onTaskComplete(errorText, Constants.TAG_API_CALL_ERROR);
            }
        }, call, Member.getType(), ErrorModel.getType(), Constants.TAG_MEMBER, Constants.TAG_ERROR_MODEL);
    }


    //endregion
}
