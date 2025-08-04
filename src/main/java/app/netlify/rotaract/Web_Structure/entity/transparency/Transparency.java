package app.netlify.rotaract.Web_Structure.entity.transparency;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transparency {
    private String title;
    private String description;
    private String documentUrl;
    private String publishDate;

    public Transparency() {
    }

    public Transparency(String title, String description, String documentUrl, String publishDate) {
        this.title = title;
        this.description = description;
        this.documentUrl = documentUrl;
        this.publishDate = publishDate;
    }

    public Transparency(String title, String description, String documentUrl) {
        this.title = title;
        this.description = description;
        this.documentUrl = documentUrl;
    }

}
