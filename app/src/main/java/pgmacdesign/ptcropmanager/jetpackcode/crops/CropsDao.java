package pgmacdesign.ptcropmanager.jetpackcode.crops;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Crop;

/**
 * Crops DAO Interface. Reference docs here:
 * https://developer.android.com/reference/android/arch/persistence/room/Query
 */
@Dao
public interface CropsDao {

    //CRUD Methods:

    //region Create
    @Insert
    void insert(Crop crop);

    //endregion

    //region Read
    @Query("SELECT * FROM crops WHERE id IS :id")
    LiveData<Crop> getCrop(Long id);
    @Query("SELECT * FROM crops WHERE slug IS :slug")
    LiveData<Crop> getCrop(String slug);
    @Query("SELECT * FROM crops")
    LiveData<List<Crop>> readAll();
    @Query("SELECT * FROM crops WHERE slug IS :slug")
    LiveData<List<Crop>> getCropBySlug(String slug);

    //endregion

    //region Update
    @Update
    void update(Crop crop);

    //endregion

    //region Delete
    @Delete
    Integer delete(Crop crop);

    //endregion
    
}
