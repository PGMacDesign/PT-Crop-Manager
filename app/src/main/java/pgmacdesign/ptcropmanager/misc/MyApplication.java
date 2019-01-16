package pgmacdesign.ptcropmanager.misc;

import android.content.Context;

import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConfig;
import com.pgmacdesign.pgmactips.utilities.DatabaseUtilities;
import com.pgmacdesign.pgmactips.utilities.DisplayManagerUtilities;
import com.pgmacdesign.pgmactips.utilities.SharedPrefs;

import androidx.multidex.MultiDexApplication;
import io.realm.RealmConfiguration;

public class MyApplication extends MultiDexApplication {

    //Instance of the application
    private static MyApplication sInstance;
    //Context
    private static Context context;
    //Shared preferences wrapper class
    private static SharedPrefs sp;
    //Localized Database management class.
    private static DatabaseUtilities dbUtilities;
    //Used for managing screen width, height, and other visual metrics
    private static DisplayManagerUtilities dmu;
    //Used for managing logging statements on live builds and other config details
    private static PGMacTipsConfig pgMacTipsConfig;
    
    public MyApplication(){
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.sInstance = this;
        MyApplication.context = getContext();
        MyApplication.dbUtilities = getDatabaseInstance();
        MyApplication.sp = getSharedPrefsInstance();
        MyApplication.pgMacTipsConfig = buildPGMacTipsConfig();
    }


    /**
     * Get context, if it is null, get an instance first and then return it
     * @return Context
     */
    public static synchronized Context getContext(){
        if(context == null){
            MyApplication.context = getInstance().getApplicationContext();
        }
        return context;
    }

    /**
     * Build and return a DatabaseUtilities instance
     * @return {@link DatabaseUtilities}
     */
    public static synchronized DatabaseUtilities getDatabaseInstance(){
        if(dbUtilities == null){
            RealmConfiguration config = DatabaseUtilities.buildRealmConfig(
                    getContext(),
                    Constants.REALM_DB_NAME,
                    Constants.REALM_DB_VERSION,
                    Constants.REALM_DB_FORCE_UPDATE_IF_NEEDED
            );
            dbUtilities = new DatabaseUtilities(getContext(), config);
        }
        return dbUtilities;
    }

    /**
     * Build and return a shared prefs instance state.
     * @return {@link SharedPrefs}
     */
    public static synchronized SharedPrefs getSharedPrefsInstance(){
        if(sp == null){
            sp = SharedPrefs.getSharedPrefsInstance(getContext(),
                    Constants.SHARED_PREFS_NAME);
        }
        return sp;
    }

    public static synchronized PGMacTipsConfig buildPGMacTipsConfig(){
        if(pgMacTipsConfig == null) {
            pgMacTipsConfig = new PGMacTipsConfig.Builder().setLiveBuild(Constants.IS_LIVE_BUILD)
                    .setTagForLogging("PTCrop").build(getContext());
        } else {
            if(PGMacTipsConfig.getInstance() == null){
                pgMacTipsConfig = new PGMacTipsConfig.Builder().setLiveBuild(Constants.IS_LIVE_BUILD)
                        .setTagForLogging("PTCrop").build(getContext());
            }
        }
        return pgMacTipsConfig;
    }

    /**
     * DMU is utilized in screen measurements in pixels and DP. Useful for setting view
     * dimensions on the fly.
     * @return {@link DisplayManagerUtilities}
     */
    public static synchronized DisplayManagerUtilities getDMU(){
        if(dmu == null) {
            dmu = new DisplayManagerUtilities(getContext());
        }
        return dmu;
    }

    /**
     * Get an instance of the application. This will cascade down and define/ initialize
     * other variables like context as well.
     * @return {@link MyApplication}
     */
    public static synchronized MyApplication getInstance(){
        if(sInstance == null) {
            sInstance = new MyApplication();
        }
        return sInstance;
    }

    /**
     * Connectivity Broadcast Receiver for detecting when internet drops or is reconnected
     * @param listener {@link PGConnectivityReceiver}
     */
    public static synchronized void setConnectivityListener(PGConnectivityReceiver.ConnectivityReceiverListener listener) {
        PGConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
