package pgmacdesign.ptcropmanager;

import android.os.Bundle;

import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;

import androidx.appcompat.app.AppCompatActivity;
import pgmacdesign.ptcropmanager.misc.MyApplication;

public class MainActivity extends AppCompatActivity implements PGConnectivityReceiver.ConnectivityReceiverListener {

    //region Variables

    //endregion


    //region UI

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
        this.initListener();
    }

    /**
     * Initialize variables (Pre-UI initialization)
     */
    private void initVariables(){

    }

    /**
     * Initialize UI
     */
    private void initUI(){

    }

    /**
     * Initialize Listeners
     */
    private void initListener(){
        MyApplication.setConnectivityListener(this);

    }


    //endregion

    //region Class Functions

    //endregion


    //region Override Functions

    /**
     * Triggers on Network connectivity changing
     * @param isConnected
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected){
            //Has internet, take action

        } else {
            //No internet, take action

        }
    }

    //endregion
}
