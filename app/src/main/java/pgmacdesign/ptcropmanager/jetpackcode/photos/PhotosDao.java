package pgmacdesign.ptcropmanager.jetpackcode.photos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;

/**
 * Interface for managing photos into and out of the DB
 */
@Dao
public interface PhotosDao {

    //CRUD Methods:

    //region Create
    @Insert
    void insert(Photo photo);

    //endregion

    //region Read
    @Query("SELECT * FROM photos WHERE id IS :id")
    LiveData<Photo> getPhoto(Long id);
    @Query("SELECT * FROM photos")
    LiveData<List<Photo>> readAll();

    //endregion

    //region Update
    @Update
    void update(Photo photo);

    //endregion

    //region Delete
    @Delete
    Integer delete(Photo photo);

    //endregion


}
