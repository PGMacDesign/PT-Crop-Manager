package pgmacdesign.ptcropmanager.jetpackcode.photos;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.pgmacdesign.pgmactips.utilities.MiscUtilities;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;

/**
 * This view model manages the photos database and serves as a wrapper for CRUD operations as well
 */
public class PhotoViewModel  extends AndroidViewModel {

    //region Vars
    private PhotosDao photosDao;
    private PhotoRoomDatabase photoDB;
    //Live Data will run on the background thread
    private LiveData<List<Photo>> mListObjects;

    //endregion

    //region Constructors

    public PhotoViewModel(Application application){
        super(application);
        this.photoDB = PhotoRoomDatabase.getInstance(application);
        if(this.photoDB == null){
            return;
        }
        this.photosDao = photoDB.nodeDao();
        this.mListObjects = photosDao.readAll();
    }
    //endregion

    //region CRUD Operation items here

    public void insert(Photo photo){
        new PhotoViewModel.DBCreateAsync(photosDao).execute(photo);
    }

    public void insert(List<Photo> photos){
        if(!MiscUtilities.isListNullOrEmpty(photos)){
            new PhotoViewModel.DBCreateAsync(photosDao).execute(photos.toArray(new Photo[photos.size()]));
        }
    }

    public LiveData<List<Photo>> getAllPhotos(){
        return this.mListObjects;
    }

    public LiveData<Photo> getPhoto(Photo photo){
        return (photo == null) ? null : getPhoto(photo.getId());
    }

    public LiveData<Photo> getPhoto(Long photoId){
        return photosDao.getPhoto(photoId);
    }


    public void deletePhoto(Photo photoPOJO){
        new PhotoViewModel.DBDeleteAsync(photosDao).execute(photoPOJO);
    }

    public void deletePhoto(Long photoId){
        try {
            deletePhoto(getPhoto(photoId).getValue());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updatePhoto(Photo photoToUpdate){
        if(photoToUpdate != null){
            new PhotoViewModel.DBUpdateAsync(photosDao).execute(photoToUpdate);
        }
    }


    //endregion

    //region Async Tasks
    private static class DBCreateAsync extends AsyncTask<Photo, Void, Void> {
        PhotosDao photosDao;

        public DBCreateAsync(PhotosDao photosDao){
            this.photosDao = photosDao;
        }

        @Override
        protected Void doInBackground(Photo... photoPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(photoPOJOS)) {
                for (Photo n : photoPOJOS) {
                    if (n != null) {
                        try {
                            this.photosDao.insert(n);
                        } catch (SQLiteConstraintException e){
                            //This will trigger if unique Constraint is triggered. no reason to log it
                        }
                    }
                }
            }
            return null;
        }
    }

    private static class DBUpdateAsync extends AsyncTask<Photo, Void, Void> {
        PhotosDao photosDao;

        public DBUpdateAsync(PhotosDao photosDao){
            this.photosDao = photosDao;
        }

        @Override
        protected Void doInBackground(Photo... photoPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(photoPOJOS)) {
                for (Photo n : photoPOJOS) {
                    if (n != null) {
                        this.photosDao.update(n);
                    }
                }
            }
            return null;
        }
    }


    private static class DBDeleteAsync extends AsyncTask<Photo, Void, Void> {
        PhotosDao photosDao;

        public DBDeleteAsync(PhotosDao photosDao){
            this.photosDao = photosDao;
        }

        @Override
        protected Void doInBackground(Photo... photoPOJOS) {
            if(!MiscUtilities.isArrayNullOrEmpty(photoPOJOS)) {
                for (Photo n : photoPOJOS) {
                    if (n != null) {
                        this.photosDao.delete(n);
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
