package pgmacdesign.ptcropmanager.jetpackcode.crops;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.pgmacdesign.pgmactips.utilities.MiscUtilities;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Crop;

/**
 * This view model manages the crops database and serves as a wrapper for CRUD operations as well
 */
public class CropViewModel extends AndroidViewModel {

    //region Vars
    private CropsDao cropsDao;
    private CropRoomDatabase cropDB;
    //Live Data will run on the background thread
    private LiveData<List<Crop>> mListObjects;

    //endregion

    //region Constructors

    public CropViewModel(Application application){
        super(application);
        this.cropDB = CropRoomDatabase.getInstance(application);
        if(this.cropDB == null){
            return;
        }
        this.cropsDao = cropDB.nodeDao();
        this.mListObjects = cropsDao.readAll();
    }
    //endregion

    //region CRUD Operation items here

    public void insert(Crop crop){
        new DBCreateAsync(cropsDao).execute(crop);
    }

    public void insert(List<Crop> crops){
        if(!MiscUtilities.isListNullOrEmpty(crops)){
            new DBCreateAsync(cropsDao).execute(crops.toArray(new Crop[crops.size()]));
        }
    }

    public LiveData<List<Crop>> getAllCrops(){
        return this.mListObjects;
    }

    public LiveData<Crop> getCrop(Crop crop){
        return (crop == null) ? null : getCrop(crop.getId());
    }

    public LiveData<Crop> getCrop(Long cropId){
        return cropsDao.getCrop(cropId);
    }

    public LiveData<Crop> getCrop(String slug){
        return cropsDao.getCrop(slug);
    }

    public void deleteCrop(Crop cropPOJO){
        new DBDeleteAsync(cropsDao).execute(cropPOJO);
    }

    public void deleteCrop(Long cropId){
        try {
            deleteCrop(getCrop(cropId).getValue());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteCrop(String slug){
        try {
            deleteCrop(getCrop(slug).getValue());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateCrop(Crop cropToUpdate){
        if(cropToUpdate != null){
            new DBUpdateAsync(cropsDao).execute(cropToUpdate);
        }
    }


    //endregion

    //region Async Tasks
    private static class DBCreateAsync extends AsyncTask<Crop, Void, Void> {
        CropsDao cropsDao;

        public DBCreateAsync(CropsDao cropsDao){
            this.cropsDao = cropsDao;
        }

        @Override
        protected Void doInBackground(Crop... cropPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(cropPOJOS)) {
                for (Crop n : cropPOJOS) {
                    if (n != null) {
                        try {
                            this.cropsDao.insert(n);
                        } catch (SQLiteConstraintException e){
                            //This will trigger if unique Constraint is triggered. no reason to log it
                        }
                    }
                }
            }
            return null;
        }
    }

    private static class DBUpdateAsync extends AsyncTask<Crop, Void, Void> {
        CropsDao cropsDao;

        public DBUpdateAsync(CropsDao cropsDao){
            this.cropsDao = cropsDao;
        }

        @Override
        protected Void doInBackground(Crop... cropPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(cropPOJOS)) {
                for (Crop n : cropPOJOS) {
                    if (n != null) {
                        this.cropsDao.update(n);
                    }
                }
            }
            return null;
        }
    }


    private static class DBDeleteAsync extends AsyncTask<Crop, Void, Void> {
        CropsDao cropsDao;

        public DBDeleteAsync(CropsDao cropsDao){
            this.cropsDao = cropsDao;
        }

        @Override
        protected Void doInBackground(Crop... cropPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(cropPOJOS)) {
                for (Crop n : cropPOJOS) {
                    if (n != null) {
                        this.cropsDao.delete(n);
                    }
                }
            }
            return null;
        }
    }

    //endregion

    //region Override Functions

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    //endregion

    
}
