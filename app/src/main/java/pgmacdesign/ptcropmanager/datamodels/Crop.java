package pgmacdesign.ptcropmanager.datamodels;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Crop data model
 */
@Entity(tableName = "Crops")
public class Crop {

    public Crop(){}

    //region Vars

    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("en_wikipedia_url")
    private String wikiUrl;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("slug")
    private String slug;
    @SerializedName("parentId")
    private Long parentId;
    @SerializedName("plantings_count")
    private Long plantings_count;
    @SerializedName("creator_id")
    private Long creator_id;
    @ColumnInfo(name = "median_lifespan", index = true)
    @SerializedName("median_lifespan")
    private Long median_lifespan;
    @ColumnInfo(name = "median_days_to_first_harvest", index = true)
    @SerializedName("median_days_to_first_harvest")
    private Long median_days_to_first_harvest;
    @ColumnInfo(name = "median_days_to_last_harvest", index = true)
    @SerializedName("median_days_to_last_harvest")
    private Long median_days_to_last_harvest;

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPlantings_count() {
        return plantings_count;
    }

    public void setPlantings_count(Long plantings_count) {
        this.plantings_count = plantings_count;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public Long getMedian_lifespan() {
        return median_lifespan;
    }

    public void setMedian_lifespan(Long median_lifespan) {
        this.median_lifespan = median_lifespan;
    }

    public Long getMedian_days_to_first_harvest() {
        return median_days_to_first_harvest;
    }

    public void setMedian_days_to_first_harvest(Long median_days_to_first_harvest) {
        this.median_days_to_first_harvest = median_days_to_first_harvest;
    }

    public Long getMedian_days_to_last_harvest() {
        return median_days_to_last_harvest;
    }

    public void setMedian_days_to_last_harvest(Long median_days_to_last_harvest) {
        this.median_days_to_last_harvest = median_days_to_last_harvest;
    }


    //endregion


}
