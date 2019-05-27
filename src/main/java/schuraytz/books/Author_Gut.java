
package schuraytz.books;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author_Gut {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birth_year")
    @Expose
    private Integer birthYear;
    @SerializedName("death_year")
    @Expose
    private Integer deathYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

}
