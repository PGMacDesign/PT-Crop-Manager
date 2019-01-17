package pgmacdesign.ptcropmanager.jetpackcode;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Crops Room Database Abstract class for extending and access
 */
public abstract class CropRoomDatabase extends RoomDatabase {

    /**
     * DAO == "Data Access Object"
     */
    public abstract CropsDao nodeDao();

    //Singleton Instance of DB
    private static volatile CropRoomDatabase cropRoomDatabase;

    static CropRoomDatabase getInstance(final Context context){
        if(CropRoomDatabase.cropRoomDatabase == null){
            if(context == null){
                return null;
            }
            synchronized (CropRoomDatabase.class){
                if(cropRoomDatabase == null){
                    cropRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            CropRoomDatabase.class, "crops_database")
                            .build();
                }
            }
        }
        return CropRoomDatabase.cropRoomDatabase;
    }
    
}
