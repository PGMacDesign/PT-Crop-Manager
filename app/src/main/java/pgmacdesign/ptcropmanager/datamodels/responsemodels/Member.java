package pgmacdesign.ptcropmanager.datamodels.responsemodels;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Members
 */
@Entity(tableName = "Members")
public class Member {

    public Member(){}

    //region Vars
    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("login_name")
    private String login_name;
    @SerializedName("slug")
    private String slug;
    @SerializedName("location")
    private String location;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("bio")
    private String bio;


    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    //endregion

    //region Utility methods

    /**
     * Get the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type}
     */
    public static Type getType(){
        return new TypeToken<Member>(){}.getType();
    }

    /**
     * Get an arraylist of the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type} of a list of of the object
     */
    public static Type getListType(){
        return new TypeToken<ArrayList<Member>>(){}.getType();
    }
    //endregion

}

