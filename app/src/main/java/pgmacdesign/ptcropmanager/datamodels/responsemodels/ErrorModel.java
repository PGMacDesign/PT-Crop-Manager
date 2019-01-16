package pgmacdesign.ptcropmanager.datamodels.responsemodels;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ErrorModel {
    //region Vars
    @SerializedName("status")
    private String status; //IE 404
    @SerializedName("error")
    private String error; //IE Not Found

    //endregion

    //region Getters and Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    //endregion

    //region Utility methods

    /**
     * Get the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type}
     */
    public static Type getType(){
        return new TypeToken<ErrorModel>(){}.getType();
    }

    /**
     * Get an arraylist of the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type} of a list of of the object
     */
    public static Type getListType(){
        return new TypeToken<ArrayList<ErrorModel>>(){}.getType();
    }
    //endregion
}
