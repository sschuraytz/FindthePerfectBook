package schuraytz.GutdendexBooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formats {

    @SerializedName("text/plain; charset=us-ascii")
    @Expose
    private String text_plaincharset_us_ascii;
    @SerializedName("text/plain")
    @Expose
    private String text_plain;
    @SerializedName("text/html")
    @Expose
    private String text_html;
    @SerializedName("text/plain; charset=iso-8859-1")
    @Expose
    private String text_plaincharset_iso_8859;


    public String getText_plaincharset_us_ascii() {
        return text_plaincharset_us_ascii;
    }

    public String getText_plain() {
        return text_plain;
    }

    public String getText_html() {
        return text_html;
    }

    public String getText_plaincharset_iso_8859() {
        return text_plaincharset_iso_8859;
    }
}

