
package schuraytz.GutdendexBooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_Gut {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<Author_Gut> authors = null;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = null;
    @SerializedName("bookshelves")
    @Expose
    private List<String> bookshelves = null;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("copyright")
    @Expose
    private Boolean copyright;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
/*    @SerializedName("formats")
    @Expose
    private Formats formats;*/
    @SerializedName("formats")
    @Expose
    private Formats formats;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author_Gut> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author_Gut> authors) {
        this.authors = authors;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Formats getFormats() {
        return formats;
    }

    public void setFormats(Formats formats) {
        this.formats = formats;
    }
/*
    public Formats getFormats() {
        return formats;
    }

    public void setFormats(Formats formats) {
        this.formats = formats;
    }
*/

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

}
