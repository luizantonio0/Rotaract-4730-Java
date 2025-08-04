package app.netlify.rotaract.Web_Structure.entity.member.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Setter
@Getter
public class Member {
    private String name;
    private String email;
    private String description;
    private String image;

    public Member() {
    }
    public Member(String name, String email, String description, String image) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.image = image;
    }
    public Member(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Yes it worked" + super.toString();
    }
}
