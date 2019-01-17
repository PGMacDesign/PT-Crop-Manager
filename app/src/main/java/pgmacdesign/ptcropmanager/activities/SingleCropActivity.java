package pgmacdesign.ptcropmanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.customui.HiveProgressBar;

/**
 * Utilized when viewing a single crop
 */
public class SingleCropActivity extends ParentActivity{
    
    //region Variables
    boolean hasInternetConnectivity, justShowedNoConnectivity;
    private FragmentManager fm;

    //endregion

    //region UI
    private Snackbar snackbar;
    private CoordinatorLayout activity_crop_coordinator_layout;
    private HiveProgressBar progressBar;
    private TextView app_bar_tv;

    //endregion

    //region OnCreate and Initialization Methods

    /**
     * OnCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_single_crop);
        this.initParentClass();
        this.initVariables();
        this.initUI();
        this.requestPerms();
        this.initListeners();
    }

    /**
     * Initialize variables (Pre-UI initialization)
     */
    private void initVariables() {
        this.getMyFragmentManager();
        this.hasInternetConnectivity = false;
    }

    /**
     * Initialize UI
     */
    private void initUI() {
        this.activity_crop_coordinator_layout = (CoordinatorLayout) this.findViewById(
                R.id.activity_crop_coordinator_layout);
        this.progressBar = HiveProgressBar.buildHiveDialog(this, false, null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }

        toolbar.setTitle("");
        this.app_bar_tv = (TextView) this.findViewById(R.id.app_bar_tv);
    }

    /**
     * Request permissions
     */
    private void requestPerms() {
        
    }

    /**
     * Initialize Listeners
     */
    private void initListeners() {

    }

    //endregion

    //region Class Methods / Functions

    /**
     * Initialize (if null) a fragment manager and return it
     *
     * @return
     */
    private FragmentManager getMyFragmentManager() {
        if (this.fm == null) {
            this.fm = this.getSupportFragmentManager();
        }
        return this.fm;
    }

    /**
     * Show a snackbar indicating the internet connectivity has been reconnected and it is switching
     * to online mode
     *
     * @param show if true, will show, if false, will hide
     */
    private void showConnectedSnackbar(boolean show) {
        if (show) {
            this.snackbar = Snackbar.make(this.activity_crop_coordinator_layout,
                    SingleCropActivity.this.getString(R.string.connected_to_internet),
                    Snackbar.LENGTH_SHORT);
            this.snackbar.setAction(SingleCropActivity.this.getString(R.string.ok), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SingleCropActivity.this.snackbar.dismiss();
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
            if (this.snackbar != null) {
                this.snackbar.dismiss();
            }
        }
    }

    /**
     * Show a snackbar indicating the internet connectivity has been lost and it is switching
     * to offline mode
     *
     * @param show if true, will show, if false, will hide
     */
    private void showDisconnectedSnackbar(boolean show) {
        if (show) {
            this.justShowedNoConnectivity = true;
            this.snackbar = Snackbar.make(this.activity_crop_coordinator_layout,
                    SingleCropActivity.this.getString(R.string.not_connected_to_internet),
                    Snackbar.LENGTH_INDEFINITE);
            this.snackbar.setAction(SingleCropActivity.this.getString(R.string.dismiss), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SingleCropActivity.this.snackbar.dismiss();
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
            if (this.snackbar != null) {
                this.snackbar.dismiss();
            }
        }

    }

    /**
     * Change the app to function in offline mode
     */
    private void setToOfflineMode() {

    }

    /**
     * Change the app to function in online mode
     */
    private void setToOnlineMode() {

    }

    //endregion

    //region Override Functions

    /**
     * Triggers on Network connectivity changing
     *
     * @param isConnected
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        this.hasInternetConnectivity = isConnected;
        if (isConnected) {
            //Has internet, take action
            if (this.justShowedNoConnectivity) {
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

    /**
     * Show or hide the loading animation while the page is loading
     */
    public void showOrHideLoadingAnimation(boolean bool) {
        if (bool) {
            try {
                this.progressBar.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.progressBar.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the top center toolbar textview
     */
    public void setToolbarTitle(String str) {
        this.app_bar_tv.setText((StringUtilities.isNullOrEmpty(str) ? "" : str));
    }


    //endregion
}
