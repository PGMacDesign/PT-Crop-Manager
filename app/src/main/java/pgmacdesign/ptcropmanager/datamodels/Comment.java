package pgmacdesign.ptcropmanager.datamodels;


import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Comments
 */
@Entity(tableName = "Comments")
public class Comment {

    public Comment(){}

    //region Vars
    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("post_id")
    @ColumnInfo(name = "post_id", index = true)
    private Long post_id;
    @SerializedName("author_id")
    @ColumnInfo(name = "author_id", index = true)
    private Long author_id;
    @SerializedName("body")
    private String body;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    //endregion

}
