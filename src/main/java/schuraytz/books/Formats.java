package schuraytz.books;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formats {

    @SerializedName("text/plain; charset=us-ascii")
    @Expose
    private String text_plaincharsetusascii;
    @SerializedName("text/plain")
    @Expose
    private String text_plain;
    @SerializedName("text/html")
    @Expose
    private String text_html;


    public String getText_plain() {
        return text_plain;
    }

    public String getText_plaincharsetusascii() {
        return text_plaincharsetusascii;
    }

    public String getText_html() {
        return text_html;
    }

}

