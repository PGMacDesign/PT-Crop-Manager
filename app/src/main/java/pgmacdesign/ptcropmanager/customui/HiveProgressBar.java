package pgmacdesign.ptcropmanager.customui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.comix.overwatch.HiveProgressView;

import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import pgmacdesign.ptcropmanager.R;

/**
 * Custom Indeterminite Progressbar for loading while web calls are being made
 */
public class HiveProgressBar  extends ProgressDialog {

    /**
     * Listener for use in dismissing, bit not being used currently
     */
    public static interface ProgressBarUtilListener{
        public void userHitBackButton();
        public void dialogDismissed();
        public void dialogHasBeenUpForXSeconds(long numberOfSeconds);
    }

    /**
     * Static builder method for use of creating the dialog
     * @param context {@link Context}
     * @param cancelable boolean, if true, can be canceled by user with back button, else, cannot
     * @param listener {@link HiveProgressBar.OnDismissListener}
     * @return {@link this}
     */
    public static HiveProgressBar buildHiveDialog(Context context, boolean cancelable,
                                                  @Nullable ProgressBarUtilListener listener){
        HiveProgressBar customAlertDialog = new HiveProgressBar(context);

        if(listener != null){
            customAlertDialog.setListener(listener);
        }
        customAlertDialog.setCancelable(cancelable);
        Window window = customAlertDialog.getWindow();
        int transparent = ContextCompat.getColor(context, R.color.Transparent);
        ColorDrawable colorDrawable = new ColorDrawable(transparent);
        window.setBackgroundDrawable(colorDrawable);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(lp);
        return customAlertDialog;
    }

    private HiveProgressBar(Context context) {
        super(context);
    }

    private com.comix.overwatch.HiveProgressView hive_progress_view;
    private boolean stopSVG = false;
    private Timer timer;
    private ProgressBarUtilListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.svg_progress_bar);
        this.hive_progress_view =  (HiveProgressView) this.findViewById(
                R.id.hive_progress_view);

    }

    @Override
    public void onBackPressed() {
        if(listener != null) {
            listener.userHitBackButton();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void show() {
        super.show();
        stopSVG = false;
        //startSVGAnimation();
        startCircleProgressAnimation();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        try {
            stopSVG = true;
            if(listener != null){
                listener.dialogDismissed();
            }
            if(timer != null) {
                timer.cancel();
            }
        } catch (Exception e){}
    }

    private void setListener(ProgressBarUtilListener listener){
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            this.getWindow().setLayout(width, height);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startCircleProgressAnimation(){
        this.hive_progress_view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        try {
            timer.cancel();
        } catch (Exception e){}
        super.onStop();
    }
}



