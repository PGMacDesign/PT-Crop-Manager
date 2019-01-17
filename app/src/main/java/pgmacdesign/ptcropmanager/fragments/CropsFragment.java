package pgmacdesign.ptcropmanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.pgmacdesign.pgmactips.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmactips.utilities.L;
import com.pgmacdesign.pgmactips.utilities.MiscUtilities;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.adapters.AdapterCropsRecyclerview;
import pgmacdesign.ptcropmanager.customui.ZoomableImageDialog;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Crop;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;
import pgmacdesign.ptcropmanager.misc.Constants;
import pgmacdesign.ptcropmanager.networking.APICalls;

public class CropsFragment extends ParentFragment  {

    //region Static Vars
    private static final int LAYOUT_RES_ID = R.layout.crops_fragment;
    private static final List<String> SPINNER_OPTIONS = Arrays.asList("Crops", "Photos");
    //endregion

    //region Instance Variables

    private OnTaskCompleteListener cropClickListener, onPhotoClickListener;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private AdapterCropsRecyclerview cropsAdapter, photosAdapter;
    private RecyclerView.OnScrollListener cropsScrollListener, photosScrollListener;
    private SwipeRefreshLayout.OnRefreshListener cropsRefreshListener, photosRefreshListener;
    private AdapterView.OnItemSelectedListener spinnerClickListener;
    private List<Crop> mListCrops;
    private List<Photo> mListPhotos;
    private int cropsLastPageNumber, photosLastPageNumber,
            cropsRecyclerviewPosition, photosRecyclerviewPosition;
    private boolean isConnectedToTheInternet;
    private ZoomableImageDialog zoomableImageDialog;

    //endregion

    //region Instance UI Variables

    private TextView crops_fragment_top_tv;
    private NiceSpinner crops_fragment_spinner;
    private SwipeRefreshLayout crops_fragment_swiperefresh_crops,
            crops_fragment_swiperefresh_photos;
    private RecyclerView crops_fragment_recyclerview_crops,
            crops_fragment_recyclerview_photos;

    //endregion

    //region Initialization

    public CropsFragment(){
        this.setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.initVariables();
        View view = inflater.inflate(LAYOUT_RES_ID, container, false);
        this.initUI(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getCrops(++cropsLastPageNumber);
        this.getPhotos(++photosLastPageNumber);
    }

    /**
     * Initialize all variables
     */
    private void initVariables(){
        this.isConnectedToTheInternet = true;
        this.cropsLastPageNumber = this.photosLastPageNumber =
                this.cropsRecyclerviewPosition = this.photosRecyclerviewPosition = 0;
        this.onPhotoClickListener = new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                switch (customTag){
                    case Constants.TAG_PHOTO:
                        Photo p = (Photo) result;
                        if(p != null){
                            String fullSizeURL = p.getFullsize_url();
                            if(!StringUtilities.isNullOrEmpty(fullSizeURL)){
                                if(CropsFragment.this.zoomableImageDialog != null){
                                    CropsFragment.this.zoomableImageDialog.dismiss();
                                }
                                CropsFragment.this.zoomableImageDialog = new ZoomableImageDialog(
                                        context, fullSizeURL);
                                CropsFragment.this.zoomableImageDialog.show();
                            }
                        }
                        break;
                }
            }
        };
        this.cropClickListener = new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                switch (customTag){
                    case Constants.TAG_CROP:
                        Crop crop = (Crop) result;
                        // TODO: open up new activity from crop result
                        break;
                }
            }
        };
        this.staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        this.linearLayoutManager = new LinearLayoutManager(context);
        this.initLists();
        this.cropsAdapter = new AdapterCropsRecyclerview(context,
                AdapterCropsRecyclerview.AdapterCropsType.Crop, this.cropClickListener);
        this.photosAdapter = new AdapterCropsRecyclerview(context,
                AdapterCropsRecyclerview.AdapterCropsType.Photos, this.onPhotoClickListener);
        this.cropsRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CropsFragment.this.crops_fragment_swiperefresh_crops.setRefreshing(false);
                CropsFragment.this.cropsLastPageNumber = 0;
                CropsFragment.this.mListCrops = new ArrayList<>();
                getCrops(++cropsLastPageNumber);
            }
        };
        this.photosRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CropsFragment.this.crops_fragment_swiperefresh_photos.setRefreshing(false);
                CropsFragment.this.photosLastPageNumber = 0;
                CropsFragment.this.mListPhotos = new ArrayList<>();
                getPhotos(++photosLastPageNumber);
            }
        };
        this.cropsScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int size = linearLayoutManager.getItemCount();
                int pos = linearLayoutManager.findFirstVisibleItemPosition();
                if(pos >= (size - 6)){
                    getCrops(++cropsLastPageNumber);
                }
            }
        };
        this.photosScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int size = staggeredGridLayoutManager.getItemCount();
                int[] poses = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(null);
                int pos = (poses.length > 0) ? poses[poses.length -1] : 0;
                if(pos >= (size - 8)){
                    getPhotos(++photosLastPageNumber);
                }
            }
        };
        this.spinnerClickListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        //Crops
                        CropsFragment.this.crops_fragment_swiperefresh_crops.setVisibility(View.VISIBLE);
                        CropsFragment.this.crops_fragment_swiperefresh_photos.setVisibility(View.GONE);
                        break;

                    case 1:
                        //Photos
                        CropsFragment.this.crops_fragment_swiperefresh_crops.setVisibility(View.GONE);
                        CropsFragment.this.crops_fragment_swiperefresh_photos.setVisibility(View.VISIBLE);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
    }

    /**
     * Initialize the UI Elements
     * @param view
     */
    private void initUI(View view){

        this.crops_fragment_top_tv = (TextView) view.findViewById(
                R.id.crops_fragment_top_tv);
        this.crops_fragment_spinner = (NiceSpinner) view.findViewById(
                R.id.crops_fragment_spinner);
        this.crops_fragment_swiperefresh_crops = (SwipeRefreshLayout) view.findViewById(
                R.id.crops_fragment_swiperefresh_crops);
        this.crops_fragment_swiperefresh_photos = (SwipeRefreshLayout) view.findViewById(
                R.id.crops_fragment_swiperefresh_photos);
        this.crops_fragment_recyclerview_crops = (RecyclerView) view.findViewById(
                R.id.crops_fragment_recyclerview_crops);
        this.crops_fragment_recyclerview_photos = (RecyclerView) view.findViewById(
                R.id.crops_fragment_recyclerview_photos);

        this.crops_fragment_swiperefresh_photos.setOnRefreshListener(this.photosRefreshListener);
        this.crops_fragment_swiperefresh_crops.setOnRefreshListener(this.cropsRefreshListener);

        this.crops_fragment_recyclerview_photos.setLayoutManager(this.staggeredGridLayoutManager);
        this.crops_fragment_recyclerview_crops.setLayoutManager(this.linearLayoutManager);
        this.crops_fragment_recyclerview_photos.setAdapter(this.photosAdapter);
        this.crops_fragment_recyclerview_crops.setAdapter(this.cropsAdapter);
        this.crops_fragment_recyclerview_photos.addOnScrollListener(this.photosScrollListener);
        this.crops_fragment_recyclerview_crops.addOnScrollListener(this.cropsScrollListener);

        this.crops_fragment_spinner.attachDataSource(SPINNER_OPTIONS);
        this.crops_fragment_spinner.setOnItemSelectedListener(this.spinnerClickListener);

        //Defaults:
        this.crops_fragment_swiperefresh_crops.setVisibility(View.VISIBLE);
        this.crops_fragment_swiperefresh_photos.setVisibility(View.GONE);
        this.crops_fragment_top_tv.setVisibility(View.GONE);
        this.crops_fragment_spinner.setSelectedIndex(0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Quick initializer to prevent constant null checks
     */
    private void initLists(){
        if(this.mListPhotos == null){
            this.mListPhotos = new ArrayList<>();
        }
        if(this.mListCrops == null){
            this.mListCrops = new ArrayList<>();
        }
    }

    //endregion

    //region Override Methods

    @Override
    public void connectedToTheInternet(boolean isConnected) {
        this.isConnectedToTheInternet = isConnected;
        if(!isConnected){
            this.crops_fragment_top_tv.setVisibility(View.VISIBLE);
            // TODO: 1/17/2019 switch here on which is loading
        } else {
            this.crops_fragment_top_tv.setVisibility(View.GONE);
            // TODO: 1/17/2019 switch here on which is loading
        }
    }

    //endregion

    //region Class Methods

    /**
     * Retrieve photos from the server
     * @param pageNum Page to view
     */
    private void getPhotos(int pageNum){
        if(pageNum <= 1){
            pageNum = 1;
            this.mListPhotos = new ArrayList<>();
        }
        this.listener.showOrHideLoadingAnimation(true);
        APICalls.getPhotos(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                listener.showOrHideLoadingAnimation(false);
                if(customTag == Constants.TAG_LIST_OF_PHOTOS){
                    loadPhotos((List<Photo>) result);
                } else {
                    L.toast(context, context.getString(R.string.no_photos));
                }
            }
        }, pageNum);
    }

    /**
     * Load the photos retrieved from the server
     * @param photos Photos from API call. If null or empty, does not update list
     */
    private void loadPhotos(List<Photo> photos){
        if(!MiscUtilities.isListNullOrEmpty(photos)){
            this.initLists();
            this.mListPhotos.addAll(photos);
            this.photosAdapter.setPhotos(this.mListPhotos);
        }
    }

    /**
     * Get the crops list from the server
     * @param pageNum Page number to view
     */
    private void getCrops(int pageNum){
        if(pageNum <= 1){
            pageNum = 1;
            this.mListCrops = new ArrayList<>();
        }
        this.listener.showOrHideLoadingAnimation(true);
        APICalls.getCrops(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                listener.showOrHideLoadingAnimation(false);
                if(customTag == Constants.TAG_LIST_OF_CROPS){
                    loadCrops((List<Crop>) result);
                } else {
                    L.toast(context, context.getString(R.string.no_crops));
                }
            }
        }, pageNum);
    }

    /**
     * Load the Crops retrieved from the server
     * @param crops Crops from API call. If null or empty, does not update list
     */
    private void loadCrops(List<Crop> crops){
        if(!MiscUtilities.isListNullOrEmpty(crops)){
            this.initLists();
            this.mListCrops.addAll(crops);
            this.cropsAdapter.setCrops(this.mListCrops);
        }
    }


    //endregion
}
