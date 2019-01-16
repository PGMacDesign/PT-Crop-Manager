package pgmacdesign.ptcropmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;
import com.pgmacdesign.pgmactips.utilities.PermissionUtilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import pgmacdesign.ptcropmanager.misc.MyApplication;

public class MainActivity extends AppCompatActivity implements PGConnectivityReceiver.ConnectivityReceiverListener {

    //region Static Vars

    //endregion

    //region Variables
    boolean hasInternetConnectivity, justShowedNoConnectivity;
    private int colorBlack;
    private int colorRed;
    private int colorWhite;
    private int colorGreen;
    //endregion


    //region UI
    private Snackbar snackbar;
    private CoordinatorLayout activity_main_coordinator_layout;
    //endregion

    //region OnCreate and Initialization Methods

    /**
     * OnCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initVariables();
        this.initUI();
        this.requestPerms();
        this.initListeners();
    }

    /**
     * Initialize variables (Pre-UI initialization)
     */
    private void initVariables(){
        this.hasInternetConnectivity = false;
        this.colorBlack = ContextCompat.getColor(this, R.color.black);
        this.colorRed = ContextCompat.getColor(this, R.color.red);
        this.colorWhite = ContextCompat.getColor(this, R.color.white);
        this.colorGreen = ContextCompat.getColor(this, R.color.ForestGreen);
    }

    /**
     * Initialize UI
     */
    private void initUI(){
        this.activity_main_coordinator_layout = (CoordinatorLayout) this.findViewById(
                R.id.activity_main_coordinator_layout);
    }

    /**
     * Request permissions
     */
    private void requestPerms(){
        PermissionUtilities.permissionsRequestShortcut(this,
                new PermissionUtilities.permissionsEnum[]{
                        PermissionUtilities.permissionsEnum.ACCESS_NETWORK_STATE}
        );
    }

    /**
     * Initialize Listeners
     */
    private void initListeners(){

    }


    //endregion

    //region Class Functions

    /**
     * Show a snackbar indicating the internet connectivity has been reconnected and it is switching
     * to online mode
     * @param show if true, will show, if false, will hide
     */
    private void showConnectedSnackbar(boolean show){
        if(show){
            this.snackbar = Snackbar.make(this.activity_main_coordinator_layout,
                    MainActivity.this.getString(R.string.connected_to_internet),
                    Snackbar.LENGTH_SHORT);
            this.snackbar.setAction(MainActivity.this.getString(R.string.ok), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.this.snackbar.dismiss();
                }
            });
            this.snackbar.setActionTextColor(colorGreen);
            View view = this.snackbar.getView();
            TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
            if (tv != null) {
                tv.setTextColor(colorWhite);
                tv.setMaxLines(10);
            }
            view.setBackgroundColor(colorBlack);
            view.bringToFront();
            this.snackbar.show();
        } else {
            if(this.snackbar != null){
                this.snackbar.dismiss();
            }
        }
    }

    /**
     * Show a snackbar indicating the internet connectivity has been lost and it is switching
     * to offline mode
     * @param show if true, will show, if false, will hide
     */
    private void showDisconnectedSnackbar(boolean show){
        if(show) {
            this.justShowedNoConnectivity = true;
            this.snackbar = Snackbar.make(this.activity_main_coordinator_layout,
                    MainActivity.this.getString(R.string.not_connected_to_internet),
                    Snackbar.LENGTH_INDEFINITE);
            this.snackbar.setAction(MainActivity.this.getString(R.string.dismiss), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.this.snackbar.dismiss();
                }
            });
            this.snackbar.setActionTextColor(colorRed);
            View view = this.snackbar.getView();
            TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
            if (tv != null) {
                tv.setTextColor(colorRed);
                tv.setMaxLines(10);
            }
            view.setBackgroundColor(colorBlack);
            view.bringToFront();
            this.snackbar.show();
        } else {
            if(this.snackbar != null){
                this.snackbar.dismiss();
            }
        }

    }

    /**
     * Change the app to function in offline mode
     */
    private void setToOfflineMode(){

    }

    /**
     * Change the app to function in online mode
     */
    private void setToOnlineMode(){

    }
    
    //endregion


    //region Override Functions

    /**
     * Triggers on Network connectivity changing
     * @param isConnected
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        this.hasInternetConnectivity = isConnected;
        if(isConnected){
            //Has internet, take action
            if(this.justShowedNoConnectivity) {
                this.justShowedNoConnectivity = false;
                this.setToOnlineMode();
                this.showConnectedSnackbar(true);
            }
        } else {
            //No internet, take action
            this.setToOfflineMode();
            this.showDisconnectedSnackbar(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.setConnectivityListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.removeConnectivityListener();
    }

    //endregion
}
