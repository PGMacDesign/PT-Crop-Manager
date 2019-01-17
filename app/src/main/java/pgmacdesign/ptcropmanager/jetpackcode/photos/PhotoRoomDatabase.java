package pgmacdesign.ptcropmanager.jetpackcode.photos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import pgmacdesign.ptcropmanager.datamodels.responsemodels.Photo;

/**
 * Photo Database
 */
@Database(
        entities = {Photo.class},
        version = 1,
        exportSchema = true
)
public abstract class PhotoRoomDatabase  extends RoomDatabase {

    /**
     * DAO == "Data Access Object"
     */
    public abstract PhotosDao nodeDao();

    //Singleton Instance of DB
    private static volatile PhotoRoomDatabase photoRoomDatabase;

    static PhotoRoomDatabase getInstance(final Context context){
        if(PhotoRoomDatabase.photoRoomDatabase == null){
            if(context == null){
                return null;
            }
            synchronized (PhotoRoomDatabase.class){
                if(photoRoomDatabase == null){
                    photoRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            PhotoRoomDatabase.class, "photos_database")
                            .build();
                }
            }
        }
        return PhotoRoomDatabase.photoRoomDatabase;
    }

}

