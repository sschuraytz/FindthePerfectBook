package schuraytz.GutdendexBooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class Formats {

    @SerializedName("text/plain; charset=us-ascii")
    @Expose
    private URL text_plaincharset_us_ascii;
    @SerializedName("text/plain")
    @Expose
    private URL text_plain;
    @SerializedName("text/html")
    @Expose
    private URL text_html;
    @SerializedName("text/plain; charset=iso-8859-1")
    @Expose
    private URL text_plaincharset_iso_8859;


    public URL getText_plaincharset_us_ascii() {
        return text_plaincharset_us_ascii;
    }

    public URL getText_plain() {
        return text_plain;
    }

    public URL getText_html() {
        return text_html;
    }

    public URL getText_plaincharset_iso_8859() {
        return text_plaincharset_iso_8859;
    }
}

