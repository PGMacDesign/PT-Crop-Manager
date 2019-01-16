package pgmacdesign.ptcropmanager.datamodels;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Seeds
 */
@Entity(tableName = "Seeds")
public class Seed {

    public Seed(){}

    //region Vars
    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("owner_id")
    @ColumnInfo(name = "owner_id", index = true)
    private Long owner_id;
    @SerializedName("crop_id")
    @ColumnInfo(name = "crop_id", index = true)
    private Long crop_id;
    @SerializedName("quantity")
    private Long quantity;
    @SerializedName("description")
    private String description;
    @SerializedName("plantBefore")
    private String plantBefore;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("tradable_to")
    private String tradable_to;
    @SerializedName("slug")
    private String slug;
    @SerializedName("days_until_maturity_min")
    private Long days_until_maturity_min;
    @SerializedName("days_until_maturity_max")
    private Long days_until_maturity_max;
    @SerializedName("organic")
    private String organic;
    @SerializedName("gmo")
    private String gmo;
    @SerializedName("heirloom")
    private String heirloom;
    @SerializedName("finished")
    private Boolean finished;
    @SerializedName("finished_at")
    private String finished_at;
    @SerializedName("parent_planting_id")
    private Long parent_planting_id;

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

    public Long getCrop_id() {
        return crop_id;
    }

    public void setCrop_id(Long crop_id) {
        this.crop_id = crop_id;
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

    public String getPlantBefore() {
        return plantBefore;
    }

    public void setPlantBefore(String plantBefore) {
        this.plantBefore = plantBefore;
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

    public String getTradable_to() {
        return tradable_to;
    }

    public void setTradable_to(String tradable_to) {
        this.tradable_to = tradable_to;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getDays_until_maturity_min() {
        return days_until_maturity_min;
    }

    public void setDays_until_maturity_min(Long days_until_maturity_min) {
        this.days_until_maturity_min = days_until_maturity_min;
    }

    public Long getDays_until_maturity_max() {
        return days_until_maturity_max;
    }

    public void setDays_until_maturity_max(Long days_until_maturity_max) {
        this.days_until_maturity_max = days_until_maturity_max;
    }

    public String getOrganic() {
        return organic;
    }

    public void setOrganic(String organic) {
        this.organic = organic;
    }

    public String getGmo() {
        return gmo;
    }

    public void setGmo(String gmo) {
        this.gmo = gmo;
    }

    public String getHeirloom() {
        return heirloom;
    }

    public void setHeirloom(String heirloom) {
        this.heirloom = heirloom;
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

    public Long getParent_planting_id() {
        return parent_planting_id;
    }

    public void setParent_planting_id(Long parent_planting_id) {
        this.parent_planting_id = parent_planting_id;
    }

    //endregion






}
