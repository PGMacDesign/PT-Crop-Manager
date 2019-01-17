package pgmacdesign.ptcropmanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;
import com.pgmacdesign.pgmactips.stackmanagement.StackManager;
import com.pgmacdesign.pgmactips.utilities.PermissionUtilities;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;

import java.util.LinkedList;
import java.util.Stack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pgmacdesign.ptcropmanager.customui.HiveProgressBar;
import pgmacdesign.ptcropmanager.datamodels.miscmodels.SavedStateObj;
import pgmacdesign.ptcropmanager.fragments.CropsFragment;
import pgmacdesign.ptcropmanager.fragments.GardensFragment;
import pgmacdesign.ptcropmanager.fragments.HomeFragment;
import pgmacdesign.ptcropmanager.fragments.MembersFragment;
import pgmacdesign.ptcropmanager.fragments.SeedsFragment;
import pgmacdesign.ptcropmanager.interfaces.MainActivityListener;
import pgmacdesign.ptcropmanager.misc.Constants;
import pgmacdesign.ptcropmanager.misc.MyApplication;

/**
 * Main Activity; this houses the fragments and runs upon loading
 */
public class MainActivity extends AppCompatActivity implements
        PGConnectivityReceiver.ConnectivityReceiverListener,
        NavigationView.OnNavigationItemSelectedListener,
        MainActivityListener {


    //region Static Vars
    private static final int CONTAINER_FRAGMENT_INT = R.id.fragment_layout;
    //endregion

    //region Variables
    boolean hasInternetConnectivity, justShowedNoConnectivity;
    private int colorBlack;
    private int colorRed;
    private int colorWhite;
    private int colorGreen;
    private StackManager stackManager;
    private Constants.AllFragments currentFragment;
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
        this.initVariables();
        this.initUI();
        this.initStackManager();
        this.requestPerms();
        this.initListeners();
    }

    /**
     * Initialize variables (Pre-UI initialization)
     */
    private void initVariables() {
        this.getMyFragmentManager();
        this.hasInternetConnectivity = false;
        this.colorBlack = ContextCompat.getColor(this, R.color.black);
        this.colorRed = ContextCompat.getColor(this, R.color.red);
        this.colorWhite = ContextCompat.getColor(this, R.color.white);
        this.colorGreen = ContextCompat.getColor(this, R.color.ForestGreen);
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
     * Initialize the {@link StackManager} class for managing fragment orders and operations
     */
    private void initStackManager() {
        this.stackManager = new StackManager(Constants.getAllFragments(),
                Constants.AllFragments.HomeFragment,
                true, false);
        try {
            SavedStateObj savedStateObj = (SavedStateObj) MyApplication.getDatabaseInstance().getPersistedObject(SavedStateObj.class);
            if (savedStateObj == null) {
                return;
            }
            LinkedList<Constants.AllFragments> allFrags = savedStateObj.getFragOrder();
            if (allFrags == null) {
                return;
            }
            if (allFrags.size() <= 0) {
                return;
            }
            Constants.AllFragments fragToSave = allFrags.get(allFrags.size() - 1);
            if (fragToSave != Constants.AllFragments.HomeFragment) {
                this.stackManager.appendToTheStack(0, fragToSave);
            }
            MyApplication.getDatabaseInstance().dePersistObject(SavedStateObj.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Setup the first fragment
     */
    private void setupFirstFragment() {
        fm.beginTransaction()
                .add(CONTAINER_FRAGMENT_INT,
                        ((MainActivity.this.getMyFragmentManager()
                                .findFragmentByTag(Constants.AllFragments.HomeFragment.toString()) != null)
                                ? ((HomeFragment) (MainActivity.this.getMyFragmentManager()
                                .findFragmentByTag(Constants.AllFragments.HomeFragment.toString())))
                                : new HomeFragment()),
                        Constants.AllFragments.HomeFragment.toString())
                .commit();
        this.currentFragment = Constants.AllFragments.HomeFragment;
        MainActivity.this.setToolbarTitle(this.currentFragment.getTitle());
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
     * Simple close down method so as to make sure the proper elements are hit
     */
    private void closeActivity() {
        try {
            this.onPause();
            this.onStop();
        } catch (Exception e) {
            //In the event one cascades to the other as a problem
            e.printStackTrace();
        }
        try {
            this.finish();
        } catch (Exception e) {
            //If this triggers, move to onDestroy()
            e.printStackTrace();
            this.onDestroy();
        }
    }

    /**
     * Clear the fragment stack for next login
     */
    private void clearFragmentStack() {
        if (this.stackManager != null) {
            this.stackManager.clearAllStacks();
        }
        try {
            MyApplication.getDatabaseInstance().dePersistObject(SavedStateObj.class);
        } catch (Exception e) {
        }
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
     * On Start
     */
    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.setConnectivityListener(this);
    }

    /**
     * On Stop
     */
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.removeConnectivityListener();
    }

    /**
     * This triggers when a user clicks on a menu item within the nav drawer
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Constants.AllFragments frag = null;
        switch (id) {
            case R.id.nav_id_seeds:
                frag = Constants.AllFragments.SeedsFragment;
                break;
            case R.id.nav_id_members:
                frag = Constants.AllFragments.MembersFragment;
                break;
            case R.id.nav_id_gardens:
                frag = Constants.AllFragments.GardensFragment;
                break;
            case R.id.nav_id_crops:
                frag = Constants.AllFragments.CropsFragment;
                break;
            case R.id.nav_id_home:
                frag = Constants.AllFragments.HomeFragment;
                break;
        }
        try {
            //Close the drawer regardless of a valid position click or not
            ((DrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout))
                    .closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            //This can trigger if a phone call interrupts while the drawer is attempting to close
            e.printStackTrace();
        }
        if (frag != null) {
            try {
                this.switchToFragment(frag);
            } catch (IllegalStateException ile) {
                ile.printStackTrace();
            }
        }
        return true;
    }

    /**
     * See {@link MainActivityListener} for documentation
     */
    @Override
    public Constants.AllFragments getCurrentFragment() {
        if (this.currentFragment == null) {
            this.currentFragment = Constants.AllFragments.HomeFragment;
        }
        return (this.currentFragment);
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

    /**
     * See {@link MainActivityListener} for documentation
     */
    @Override
    public void switchToFragment(@NonNull Constants.AllFragments frag) {
        if (frag == null) {
            frag = Constants.AllFragments.HomeFragment; //Default
        }
        try {
            FragmentTransaction transaction = getMyFragmentManager().beginTransaction();
            this.stackManager.appendToTheStack(frag);
            try {
                Stack<Constants.AllFragments> allFragmentsStack = (Stack<Constants.AllFragments>) stackManager.getStack();
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (frag) {

                case HomeFragment:
                    if (this.currentFragment != frag) {
                        transaction.replace(CONTAINER_FRAGMENT_INT, ((MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString()) != null)
                                        ? ((HomeFragment) (MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString())))
                                        : new HomeFragment()),
                                frag.toString());
                        this.currentFragment = frag;
                        MainActivity.this.setToolbarTitle(frag.getTitle());
                    }
                    break;

                case CropsFragment:
                    if (this.currentFragment != frag) {
                        transaction.replace(CONTAINER_FRAGMENT_INT, ((MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString()) != null)
                                        ? ((CropsFragment) (MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString())))
                                        : new CropsFragment()),
                                frag.toString());
                        this.currentFragment = frag;
                        MainActivity.this.setToolbarTitle(frag.getTitle());
                    }
                    break;

                case GardensFragment:
                    if (this.currentFragment != frag) {
                        transaction.replace(CONTAINER_FRAGMENT_INT, ((MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString()) != null)
                                        ? ((GardensFragment) (MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString())))
                                        : new GardensFragment()),
                                frag.toString());
                        this.currentFragment = frag;
                        MainActivity.this.setToolbarTitle(frag.getTitle());
                    }
                    break;

                case MembersFragment:
                    if (this.currentFragment != frag) {
                        transaction.replace(CONTAINER_FRAGMENT_INT, ((MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString()) != null)
                                        ? ((MembersFragment) (MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString())))
                                        : new MembersFragment()),
                                frag.toString());
                        this.currentFragment = frag;
                        MainActivity.this.setToolbarTitle(frag.getTitle());
                    }
                    break;

                case SeedsFragment:
                    if (this.currentFragment != frag) {
                        transaction.replace(CONTAINER_FRAGMENT_INT, ((MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString()) != null)
                                        ? ((SeedsFragment) (MainActivity.this.getMyFragmentManager()
                                        .findFragmentByTag(frag.toString())))
                                        : new SeedsFragment()),
                                frag.toString());
                        this.currentFragment = frag;
                        MainActivity.this.setToolbarTitle(frag.getTitle());
                    }
                    break;
            }
            if (transaction != null) {
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * See {@link MainActivityListener} for documentation
     */
    @Override
    public void popBack(int numToPopBack) {
        if (numToPopBack <= 0) {
            return;
        }
        if (stackManager == null) {
            return;
        }
        if ((numToPopBack == 1) && (this.stackManager.getStackSize() == 1)) {
            //This means on the home page, back should close app
            this.clearFragmentStack();
            this.finish();
            return;
        }
        if (numToPopBack > this.stackManager.getStackSize()) {
            numToPopBack = (stackManager.getStackSize() - 1);
        }

        if ((numToPopBack == 1) && (this.stackManager.getStackSize() == 1)) {
            //This means on the home page, back should close app
            this.clearFragmentStack();
            this.closeActivity();
            return;
        }
        Constants.AllFragments toSwitchBackTo = (Constants.AllFragments) this.stackManager
                .popTheStack(0, numToPopBack);
        if (toSwitchBackTo != null) {
            this.switchToFragment(toSwitchBackTo);
        } else {
            this.switchToFragment(Constants.AllFragments.HomeFragment);
        }
    }

    //endregion
}
