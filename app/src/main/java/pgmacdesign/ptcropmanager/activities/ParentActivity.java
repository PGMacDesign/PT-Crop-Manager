package pgmacdesign.ptcropmanager.activities;

import com.pgmacdesign.pgmactips.broadcastreceivers.PGConnectivityReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.misc.MyApplication;

public abstract class ParentActivity extends AppCompatActivity implements PGConnectivityReceiver.ConnectivityReceiverListener {

    int colorBlack;
    int colorRed;
    int colorWhite;
    int colorGreen;
    boolean hasInternetConnectivity;


    void initParentClass(){
        this.colorBlack = ContextCompat.getColor(this, R.color.black);
        this.colorRed = ContextCompat.getColor(this, R.color.red);
        this.colorWhite = ContextCompat.getColor(this, R.color.white);
        this.colorGreen = ContextCompat.getColor(this, R.color.ForestGreen);
        this.hasInternetConnectivity = false;
    }

    @Override
    protected void onStart() {
        try {
            super.onStart();
        } catch (IllegalStateException ile){
            //Can trigger on bad rotation management
        }
        MyApplication.setConnectivityListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.removeConnectivityListener();
    }


}
