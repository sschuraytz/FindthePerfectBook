package schuraytz.books;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_NY {

    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("list_name_encoded")
    @Expose
    private String listNameEncoded;
    @SerializedName("bestsellers_date")
    @Expose
    private String bestsellersDate;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("published_date_description")
    @Expose
    private String publishedDateDescription;
    @SerializedName("next_published_date")
    @Expose
    private String nextPublishedDate;
    @SerializedName("previous_published_date")
    @Expose
    private String previousPublishedDate;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("normal_list_ends_at")
    @Expose
    private Integer normalListEndsAt;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("books")
    @Expose
    private List<Book_NY> books = null;
    @SerializedName("corrections")
    @Expose
    private List<Object> corrections = null;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListNameEncoded() {
        return listNameEncoded;
    }

    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

    public String getBestsellersDate() {
        return bestsellersDate;
    }

    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublishedDateDescription() {
        return publishedDateDescription;
    }

    public void setPublishedDateDescription(String publishedDateDescription) {
        this.publishedDateDescription = publishedDateDescription;
    }

    public String getNextPublishedDate() {
        return nextPublishedDate;
    }

    public void setNextPublishedDate(String nextPublishedDate) {
        this.nextPublishedDate = nextPublishedDate;
    }

    public String getPreviousPublishedDate() {
        return previousPublishedDate;
    }

    public void setPreviousPublishedDate(String previousPublishedDate) {
        this.previousPublishedDate = previousPublishedDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getNormalListEndsAt() {
        return normalListEndsAt;
    }

    public void setNormalListEndsAt(Integer normalListEndsAt) {
        this.normalListEndsAt = normalListEndsAt;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Book_NY> getBooks() {
        return books;
    }

    public void setBooks(List<Book_NY> books) {
        this.books = books;
    }

    public List<Object> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<Object> corrections) {
        this.corrections = corrections;
    }

}
