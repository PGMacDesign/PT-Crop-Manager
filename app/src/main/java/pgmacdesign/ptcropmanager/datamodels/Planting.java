package pgmacdesign.ptcropmanager.datamodels;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Planting (individual plant attached to a user) object
 */
@Entity(tableName = "Plantings")
public class Planting {

    public Planting(){}

    //region Vars

    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("garden_id")
    private Long garden_id;
    @ColumnInfo(name = "crop_id", index = true)
//    @ForeignKey(entity = Crop.class)
    @SerializedName("crop_id")
    private Long crop_id;
    @SerializedName("planted_at")
    private String planted_at;
    @SerializedName("quantity")
    private Long quantity;
    @SerializedName("description")
    private String description;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("slug")
    private String slug;
    @SerializedName("sunniness")
    private String sunniness;
    @SerializedName("planted_from")
    private String planted_from;
    @ColumnInfo(name = "owner_id", index = true)
    @SerializedName("owner_id")
    private Long owner_id;
    @SerializedName("finished")
    private Boolean finished;
    @SerializedName("finished_at")
    private String finished_at;
    @SerializedName("lifespan")
    private Long lifespan;
    @SerializedName("days_to_first_harvest")
    private Long days_to_first_harvest;
    @SerializedName("days_to_last_harvest")
    private Long days_to_last_harvest;
    @SerializedName("parent_seed_id")
    @ColumnInfo(name = "parent_seed_id", index = true)
    private Long parent_seed_id;

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGarden_id() {
        return garden_id;
    }

    public void setGarden_id(Long garden_id) {
        this.garden_id = garden_id;
    }

    public Long getCrop_id() {
        return crop_id;
    }

    public void setCrop_id(Long crop_id) {
        this.crop_id = crop_id;
    }

    public String getPlanted_at() {
        return planted_at;
    }

    public void setPlanted_at(String planted_at) {
        this.planted_at = planted_at;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSunniness() {
        return sunniness;
    }

    public void setSunniness(String sunniness) {
        this.sunniness = sunniness;
    }

    public String getPlanted_from() {
        return planted_from;
    }

    public void setPlanted_from(String planted_from) {
        this.planted_from = planted_from;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
    }

    public Long getLifespan() {
        return lifespan;
    }

    public void setLifespan(Long lifespan) {
        this.lifespan = lifespan;
    }

    public Long getDays_to_first_harvest() {
        return days_to_first_harvest;
    }

    public void setDays_to_first_harvest(Long days_to_first_harvest) {
        this.days_to_first_harvest = days_to_first_harvest;
    }

    public Long getDays_to_last_harvest() {
        return days_to_last_harvest;
    }

    public void setDays_to_last_harvest(Long days_to_last_harvest) {
        this.days_to_last_harvest = days_to_last_harvest;
    }

    public Long getParent_seed_id() {
        return parent_seed_id;
    }

    public void setParent_seed_id(Long parent_seed_id) {
        this.parent_seed_id = parent_seed_id;
    }

    //endregion
}
