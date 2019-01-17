package pgmacdesign.ptcropmanager.misc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConfig;

import androidx.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    //Instance of the application
    private static MyApplication sInstance;
    //Context
    private static Context context;
    //Used for managing logging statements on live builds and other config details
    private static PGMacTipsConfig pgMacTipsConfig;
    //Used for Connectivity Listeners
    private static BroadcastReceiver mNetworkReceiver;
    
    public MyApplication(){
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.sInstance = this;
        MyApplication.context = getContext();
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

    public static synchronized PGMacTipsConfig buildPGMacTipsConfig(){
        if(pgMacTipsConfig == null) {
            pgMacTipsConfig = new PGMacTipsConfig.Builder().setLiveBuild(Constants.IS_LIVE_BUILD)
                    .setTagForLogging(Constants.TAG_FOR_LOGGING).build(getContext());
        } else {
            if(PGMacTipsConfig.getInstance() == null){
                pgMacTipsConfig = new PGMacTipsConfig.Builder().setLiveBuild(Constants.IS_LIVE_BUILD)
                        .setTagForLogging(Constants.TAG_FOR_LOGGING).build(getContext());
            }
        }
        return pgMacTipsConfig;
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
        if(MyApplication.mNetworkReceiver == null) {
            MyApplication.mNetworkReceiver = new PGConnectivityReceiver();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getContext().registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            }
        }
        PGConnectivityReceiver.connectivityReceiverListener = listener;
    }

    /**
     * Remove the connectivity listener (broadcast receiver) so as to prevent memory leaks
     */
    public static synchronized void removeConnectivityListener() {
        if(MyApplication.mNetworkReceiver != null ) {
            getContext().unregisterReceiver(mNetworkReceiver);
        }
        PGConnectivityReceiver.connectivityReceiverListener = null;
        MyApplication.mNetworkReceiver = null;
    }
}
