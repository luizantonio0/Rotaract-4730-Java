package app.netlify.rotaract.Web_Structure.entity.project;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Project {
    private String name;
    private String description;
    private String status;
    private String image;

    public Project() {
    }

    public Project(String name, String description, String status, String image) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.image = image;
    }

    public Project(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}