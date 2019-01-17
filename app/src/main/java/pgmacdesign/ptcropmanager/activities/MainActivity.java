package pgmacdesign.ptcropmanager.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.pgmacdesign.pgmactips.utilities.L;
import com.pgmacdesign.pgmactips.utilities.PermissionUtilities;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.customui.HiveProgressBar;
import pgmacdesign.ptcropmanager.fragments.CropsFragment;
import pgmacdesign.ptcropmanager.interfaces.MainActivityListener;

/**
 * Main Activity; this houses the fragments and runs upon loading
 */
public class MainActivity extends ParentActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MainActivityListener {


    //region Static Vars
    private static final int CONTAINER_FRAGMENT_INT = R.id.fragment_layout;
    //endregion

    //region Variables
    boolean hasInternetConnectivity, justShowedNoConnectivity;
    private FragmentManager fm;

    //endregion

    //region UI
    private Snackbar snackbar;
    private CoordinatorLayout activity_main_coordinator_layout;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
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
        this.setContentView(R.layout.activity_main);
        this.initParentClass();
        this.initVariables();
        this.initUI();
        this.requestPerms();
        this.initListeners();
        this.setupFirstFragment();
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
        this.activity_main_coordinator_layout = (CoordinatorLayout) this.findViewById(
                R.id.activity_main_coordinator_layout);
        this.progressBar = HiveProgressBar.buildHiveDialog(this, false, null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.toggle.setDrawerIndicatorEnabled(true);
        this.toggle.setDrawerSlideAnimationEnabled(true);
        this.drawer.addDrawerListener(this.toggle);
        this.toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.app_bar_tv = (TextView) findViewById(
                R.id.app_bar_tv);
        toolbar.setTitle("");

        this.toggle.syncState();
    }

    /**
     * Request permissions
     */
    private void requestPerms() {
        PermissionUtilities.permissionsRequestShortcut(this,
                new PermissionUtilities.permissionsEnum[]{
                        PermissionUtilities.permissionsEnum.ACCESS_NETWORK_STATE}
        );
    }

    /**
     * Initialize Listeners
     */
    private void initListeners() {

    }

    /**
     * Setup the first fragment
     */
    private void setupFirstFragment() {
        this.getMyFragmentManager().beginTransaction()
                .add(CONTAINER_FRAGMENT_INT,
                        ((MainActivity.this.getMyFragmentManager()
                                .findFragmentByTag(MainActivity.this.getString(R.string.crops)) != null)
                                ? ((CropsFragment) (MainActivity.this.getMyFragmentManager()
                                .findFragmentByTag(MainActivity.this.getString(R.string.crops))))
                                : new CropsFragment()),
                        MainActivity.this.getString(R.string.crops))
                .commit();
        MainActivity.this.setToolbarTitle(MainActivity.this.getString(R.string.crops));
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
     * This triggers when a user clicks on a menu item within the nav drawer.
     * NOTE! Removed multiple click options on 2019-01-17 due to simplification of app
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        try {
            if(item.getItemId() != R.id.nav_id_crops) {
                L.toast(MainActivity.this, MainActivity.this.getString(
                        R.string.crops_only_limitation));
            }
            //Close the drawer regardless of a valid position click or not
            ((DrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout))
                    .closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            //This can trigger if a phone call interrupts while the drawer is attempting to close
            e.printStackTrace();
        }
        return true;
    }

    /**
     * See {@link MainActivityListener} for documentation
     */
    @Override
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
     * See {@link MainActivityListener} for documentation
     */
    @Override
    public void setToolbarTitle(String str) {
        this.app_bar_tv.setText((StringUtilities.isNullOrEmpty(str) ? "" : str));
    }


    //endregion
}
