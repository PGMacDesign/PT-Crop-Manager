package pgmacdesign.ptcropmanager.customui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.pgmacdesign.pgmactips.utilities.ImageUtilities;

import androidx.core.content.ContextCompat;
import pgmacdesign.ptcropmanager.R;

public class ZoomableImageDialog extends Dialog {

    //region Vars
    private Context context;
    private String imageUrl;
    private int backupImageResource;
    //endregion

    //region UI
    private ImageView zoomable_image_dialog_close_iv;
    private PhotoView zoomable_image_dialog_iv;
    //endregion

    //region Constructor
    public ZoomableImageDialog(Context context, String imageURL) {
        super(context);
        this.context = context;
        this.imageUrl = imageURL;
        this.backupImageResource = R.mipmap.default_picture;
    }
    //endregion

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.zoomable_image_dialog);
        try {
            int semiTransparent = ContextCompat.getColor(context, R.color.Semi_Transparent3);
            ColorDrawable colorDrawable = new ColorDrawable(semiTransparent);
            getWindow().setBackgroundDrawable(colorDrawable);
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            lp.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setAttributes(lp);
        } catch (Exception e){
            e.printStackTrace();
        }
        this.setCancelable(false);
        this.zoomable_image_dialog_iv = this.findViewById(R.id.zoomable_image_dialog_iv);
        this.zoomable_image_dialog_close_iv = this.findViewById(R.id.zoomable_image_dialog_close_iv);
        this.zoomable_image_dialog_close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZoomableImageDialog.this.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        ZoomableImageDialog.this.dismiss();
    }

    @Override
    public void show() {
        super.show();
        try {
            ImageUtilities.setImageWithPicasso(this.imageUrl, this.zoomable_image_dialog_iv,
                    this.backupImageResource);
        } catch (Exception e){
            this.zoomable_image_dialog_iv.setImageResource(backupImageResource);
        }
    }
    //endregion
}
