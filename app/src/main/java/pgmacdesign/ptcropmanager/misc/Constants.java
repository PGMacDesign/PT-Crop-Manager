package pgmacdesign.ptcropmanager.misc;

/**
 * Constants that will be unchanging throughout the app
 */
public class Constants {

    //region Boolean Flags

    public static final boolean IS_LIVE_BUILD = false;

    //endregion

    //region Strings

    public static final String API_BASE_URL = "https://www.growstuff.org/";
    public static final String GENERIC_ERROR_STR = "An error has occurred: ";
    public static final String GENERIC_UNKNOWN_ERROR_STR = "An unknown error has occurred";

    //endregion

    //region Int Tags for callbacks
    public static final int TAG_NO_NETWORK_CONNECTIVITY = -700;
    public static final int TAG_UNKNOWN_ERROR = -701;
    public static final int TAG_ERROR_MODEL = -702;
    public static final int TAG_COMMENT = -703;
    public static final int TAG_LIST_OF_COMMENTS = -704;
    public static final int TAG_CROP = -705;
    public static final int TAG_LIST_OF_CROPS = -706;
    public static final int TAG_GARDEN = -707;
    public static final int TAG_LIST_OF_GARDENS = -708;
    public static final int TAG_PHOTO = -709;
    public static final int TAG_LIST_OF_PHOTOS = -710;
    public static final int TAG_PLACE = -711;
    public static final int TAG_LIST_OF_PLACES = -712;
    public static final int TAG_PLANTING = -713;
    public static final int TAG_LIST_OF_PLANTINGS= -714;
    public static final int TAG_POST = -715;
    public static final int TAG_LIST_OF_POSTS = -716;
    public static final int TAG_SEED = -717;
    public static final int TAG_LIST_OF_SEEDS = -718;
    public static final int TAG_API_CALL_ERROR = -719;
    public static final int TAG_MEMBER = -720;
    public static final int TAG_LIST_OF_MEMBERS = -721;
    //endregion

    //region SP + DB Stuff

    public static final String REALM_DB_NAME = "ptcropmanager.realm.db";
    public static final int REALM_DB_VERSION = 1;
    public static final boolean REALM_DB_FORCE_UPDATE_IF_NEEDED = true;

    public static final String SHARED_PREFS_NAME = "ptcropmanager.sp";
    public static final String TAG_FOR_LOGGING = "PTCrop";

    //endregion

}
