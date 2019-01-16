package pgmacdesign.ptcropmanager.datamodels;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Posts
 */
@Entity(tableName = "Posts")
public class Post {

    public Post(){}

    //region Vars
    @SerializedName("id")
    @PrimaryKey
    private Long id;
    @SerializedName("author_id")
    @ColumnInfo(name = "author_id", index = true)
    private Long author_id;
    @SerializedName("subject")
    private String subject;
    @SerializedName("body")
    private String body;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("slug")
    private String slug;

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    //region Utility methods

    /**
     * Get the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type}
     */
    public static Type getType(){
        return new TypeToken<Post>(){}.getType();
    }

    /**
     * Get an arraylist of the Reflection Type utilizing Gson's {@link TypeToken} class
     * @return {@link Type} of a list of of the object
     */
    public static Type getListType(){
        return new TypeToken<ArrayList<Post>>(){}.getType();
    }
    //endregion

}
