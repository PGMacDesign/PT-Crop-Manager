package pgmacdesign.ptcropmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgmacdesign.pgmactips.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmactips.utilities.ImageUtilities;
import com.pgmacdesign.pgmactips.utilities.L;
import com.pgmacdesign.pgmactips.utilities.MiscUtilities;
import com.pgmacdesign.pgmactips.utilities.NumberUtilities;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;
import com.pgmacdesign.pgmactips.utilities.TextFieldUtilities;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Crop;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;
import pgmacdesign.ptcropmanager.misc.Constants;

/**
 * Multipurpose recyclerview that works with both {@link Crop} and {@link Photo} objects
 */
public class AdapterCropsRecyclerview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region Enums
    /**
     * Enum for Adapter Type
     */
    public enum AdapterCropsType {
        Crop(0, R.layout.adapter_crops),
        Photos(1, R.layout.adapter_photos);

        int viewType;
        int layoutId;
        AdapterCropsType(int x, int layoutId){
            this.viewType = x;
            this.layoutId = layoutId;
        }

        static int getLayoutId(int viewType){
            switch (viewType){
                case 1:
                    return Photos.layoutId;
                default:
                case 0:
                    return Crop.layoutId;

            }
        }
    }
    //endregion

    //region vars
    private AdapterCropsType type;
    private Context context;
    private OnTaskCompleteListener listener;
    private LayoutInflater mInflater;
    private int COLOR_BLACK;
    private int backupImageResourceId;

    private List<Crop> mListCrops;
    private List<Photo> mListPhotos;

    //endregion

    //region Constructor

    /**
     * Constructor
     * @param context Context
     * @param adapterCropsType {@link AdapterCropsRecyclerview.AdapterCropsType}
     * @param listener {@link OnTaskCompleteListener}
     */
    public AdapterCropsRecyclerview(@NonNull Context context,
                                    @NonNull AdapterCropsType adapterCropsType,
                                    @Nullable OnTaskCompleteListener listener) {
        this.context = context;
        this.type = adapterCropsType;
        this.mInflater = LayoutInflater.from(context);
        this.COLOR_BLACK = ContextCompat.getColor(context, com.pgmacdesign.pgmactips.R.color.black);
        this.backupImageResourceId = R.mipmap.default_picture;
        this.listener = listener;
    }

    //endregion

    //region Override Methods

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(AdapterCropsType.getLayoutId(viewType), parent, false);
        switch (viewType){
            case 1:
                return new PhotoHolder(view);

            default:
            case 0:
                return new CropHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(this.type == null){
            return;
        }
        //If this check is not performed, can throw ArrayIndexOutOfBounds Exception
        if(position < 0 || position >= getItemCount() || getItemCount() == 0){
            return;
        }
        switch (this.type){
            case Photos:
                PhotoHolder holder1 = (PhotoHolder) holder;
                final Photo photo = this.mListPhotos.get(position);
                String thumbnailUrl = photo.getThumbnail_url();
                if(StringUtilities.isNullOrEmpty(thumbnailUrl)){
                    holder1.adapter_photos_iv.setImageResource(backupImageResourceId);
                } else {
                    ImageUtilities.setImageWithPicasso(thumbnailUrl, holder1.adapter_photos_iv,
                            this.backupImageResourceId);
                }
                if(this.listener != null){
                    holder1.adapter_photos_root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onTaskComplete(photo, Constants.TAG_PHOTO);
                        }
                    });
                } else {
                    holder1.adapter_photos_root.setOnClickListener(null);
                }

                break;

            case Crop:
                CropHolder holder2 = (CropHolder) holder;
                final Crop crop = this.mListCrops.get(position);
                String name = crop.getName();
                Long daysToFirstHarvest = crop.getMedian_days_to_first_harvest();
                Long daysToLastHarvest = crop.getMedian_days_to_last_harvest();
                Long daysLifespan = crop.getMedian_lifespan();
                String wikiURL = crop.getWikiUrl();

                //Crop Name
                if(!StringUtilities.isNullOrEmpty(name)){
                    holder2.adapter_crops_name_tv.setText(name);
                } else {
                    holder2.adapter_crops_name_tv.setText("");
                }

                //3 Linear Layout Tabs with Numbers
                if(NumberUtilities.getLong(daysToFirstHarvest) > 0){
                    String s = "Days Until First Harvest:<br><font color='#006400'>" + daysToFirstHarvest + "</font>";
                    TextFieldUtilities.setTextWithHtml(holder2.adapter_crops_ll_tv1, s);
                } else {
                    holder2.adapter_crops_ll_tv1.setText("");
                }
                if(NumberUtilities.getLong(daysToLastHarvest) > 0){
                    String s = "Days Until Last Harvest:<br><font color='#8B4513'>" + daysToLastHarvest + "</font>";
                    TextFieldUtilities.setTextWithHtml(holder2.adapter_crops_ll_tv2, s);
                } else {
                    holder2.adapter_crops_ll_tv2.setText("");
                }
                if(NumberUtilities.getLong(daysLifespan) > 0){
                    String s = "Average Lifespan (Days):<br><font color='#87CEEB'>" + daysLifespan + "</font>";
                    TextFieldUtilities.setTextWithHtml(holder2.adapter_crops_ll_tv3, s);
                } else {
                    holder2.adapter_crops_ll_tv3.setText("");
                }

                //Wiki URL
                if(!StringUtilities.isNullOrEmpty(wikiURL)){
                    String s = "Wiki URL: " + wikiURL;
                    TextFieldUtilities.setTextWithHtml(holder2.adapter_crops_bottom_tv, s);
                } else {
                    holder2.adapter_crops_bottom_tv.setText("");
                }

                //Click Listeners
                if(this.listener != null){
                    holder2.adapter_crops_root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AdapterCropsRecyclerview.this.listener.onTaskComplete(crop, Constants.TAG_CROP);
                        }
                    });
                } else {
                    holder2.adapter_crops_root.setOnClickListener(null);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(this.type != null) {
            switch (this.type){
                case Crop:
                    return (MiscUtilities.isListNullOrEmpty(mListCrops) ? 0 : mListCrops.size());

                case Photos:
                    return (MiscUtilities.isListNullOrEmpty(mListPhotos) ? 0 : mListPhotos.size());
            }
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(this.type != null) {
            return this.type.viewType;
        }
        return super.getItemViewType(position);
    }

    //endregion

    //region Setters

    /**
     * Set the list of crops and notify the adapter that the set has changed
     * @param crops
     */
    public void setCrops(List<Crop> crops){
        this.mListCrops = crops;
        this.notifyDataSetChanged();
    }

    /**
     * Set the list of photos and notify the adapter that the set has changed
     * @param photos
     */
    public void setPhotos(List<Photo> photos){
        L.m("Set photos within adapter, size == " + (MiscUtilities.isListNullOrEmpty(photos) ? 0 : photos.size()));
        this.mListPhotos = photos;
        this.notifyDataSetChanged();
    }

    //endregion

    //region View Holders

    /**
     * Used for {@link Crop} crops
     */
    private class CropHolder extends RecyclerView.ViewHolder {

        private TextView adapter_crops_ll_tv1, adapter_crops_ll_tv2, adapter_crops_ll_tv3,
                adapter_crops_name_tv, adapter_crops_bottom_tv;
        private RelativeLayout adapter_crops_root;
        private LinearLayout adapter_crops_ll;
        private View separator;

        public CropHolder(View itemView) {
            super(itemView);
            this.adapter_crops_ll = itemView.findViewById(R.id.adapter_crops_ll);
            this.adapter_crops_root = itemView.findViewById(R.id.adapter_crops_root);
            this.adapter_crops_ll_tv1 = itemView.findViewById(R.id.adapter_crops_ll_tv1);
            this.adapter_crops_ll_tv2 = itemView.findViewById(R.id.adapter_crops_ll_tv2);
            this.adapter_crops_ll_tv3 = itemView.findViewById(R.id.adapter_crops_ll_tv3);
            this.adapter_crops_name_tv = itemView.findViewById(R.id.adapter_crops_name_tv);
            this.adapter_crops_bottom_tv = itemView.findViewById(R.id.adapter_crops_bottom_tv);
            this.separator = itemView.findViewById(R.id.separator);
        }
    }

    /**
     * Used for {@link Photo} photos
     */
    private class PhotoHolder extends RecyclerView.ViewHolder {
        private RelativeLayout adapter_photos_root;
        private ImageView adapter_photos_iv;

        public PhotoHolder(View itemView) {
            super(itemView);
            this.adapter_photos_iv = itemView.findViewById(R.id.adapter_photos_iv);
            this.adapter_photos_root = itemView.findViewById(R.id.adapter_photos_root);
        }
    }

    //endregion

}
