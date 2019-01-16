package pgmacdesign.ptcropmanager.datamodels;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Photos
 */
@Entity(tableName = "Photos")
public class Photo {

    public Photo(){}

    //region Vars
    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("owner_id")
    @ColumnInfo(name = "owner_id", index = true)
    private Long owner_id;
    @SerializedName("flickr_photo_id")
    private String flickr_photo_id;
    @SerializedName("thumbnail_url")
    private String thumbnail_url;
    @SerializedName("fullsize_url")
    private String fullsize_url;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("title")
    private String title;
    @SerializedName("license_name")
    private String license_name;
    @SerializedName("license_url")
    private String license_url;
    @SerializedName("link_url")
    private String link_url;
    @SerializedName("date_taken")
    private String date_taken;

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getFlickr_photo_id() {
        return flickr_photo_id;
    }

    public void setFlickr_photo_id(String flickr_photo_id) {
        this.flickr_photo_id = flickr_photo_id;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getFullsize_url() {
        return fullsize_url;
    }

    public void setFullsize_url(String fullsize_url) {
        this.fullsize_url = fullsize_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLicense_name() {
        return license_name;
    }

    public void setLicense_name(String license_name) {
        this.license_name = license_name;
    }

    public String getLicense_url() {
        return license_url;
    }

    public void setLicense_url(String license_url) {
        this.license_url = license_url;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    //endregion

}
