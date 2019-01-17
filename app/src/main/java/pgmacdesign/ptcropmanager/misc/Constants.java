package pgmacdesign.ptcropmanager.misc;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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

    //region Fragment Management
    public static enum AllFragments {
        HomeFragment("Home", "Default Home Fragment"),
        CropsFragment("Crops", "View All Crops"),
        CropFragment("Crop", "View Single Crop"),
        GardensFragment("Gardens", "View All Gardens"),
        GardenFragment("Garden", "View Single Garden"),
        MembersFragment("Members", "View All Members"),
        MemberFragment("Member", "View Single Member"),
        SeedsFragment("Seeds", "View All Seeds"),
        SeedFragment("Seed", "View Single Seed"),
        TBD1("TBD", "TBD"),
        TBD2("TBD", "TBD"),
        TBD3("TBD", "TBD"),
        TBD4("TBD", "TBD"),
        TBD5("TBD", "TBD"),
        TBD6("TBD", "TBD"),
        TBD7("TBD", "TBD"),
        TBD8("TBD", "TBD"),
        TBD9("TBD", "TBD"),

        EMPTY_FRAGMENT("Empty", "For Placeholders");

        private String title;
        private String description;
        AllFragments(String title, String description){
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

    public static List<AllFragments> getAllFragments(){
        return new ArrayList<>(EnumSet.allOf(Constants.AllFragments.class));
    }

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
    public static final int TAG_TBD2 = -722;
    public static final int TAG_TBD3 = -723;
    public static final int TAG_TBD4 = -724;
    public static final int TAG_TBD5 = -725;
    public static final int TAG_TBD6 = -726;
    public static final int TAG_TBD7 = -727;
    public static final int TAG_TBD8 = -728;
    public static final int TAG_TBD9 = -729;
    //endregion

    //region SP + DB Stuff

    public static final String REALM_DB_NAME = "ptcropmanager.realm.db";
    public static final int REALM_DB_VERSION = 1;
    public static final boolean REALM_DB_FORCE_UPDATE_IF_NEEDED = true;

    public static final String SHARED_PREFS_NAME = "ptcropmanager.sp";
    public static final String TAG_FOR_LOGGING = "PTCrop";

    //endregion

}
