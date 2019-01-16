package pgmacdesign.ptcropmanager.datamodels.miscmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Custom configuration. This is being stored in a Realm database for the purpose of utilizing
 * a different storage point and demoing multiple databases within an app
 */
public class AppConfig {

    @SerializedName("cropNames")
    private List<String> cropNames;

    public List<String> getCropNames() {
        return cropNames;
    }

    public void setCropNames(List<String> cropNames) {
        this.cropNames = cropNames;
    }
}
