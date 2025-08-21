package app.netlify.rotaract.Web_Structure.defaultURL;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "https://rotaract-parque-barigui.netlify.app/")
public class defaultURLrequest {
    public defaultURLrequest() {}

    @GetMapping
    public String defaultURL() {
        return "API is running, \nplease visit https://rotaract-parque-barigui.netlify.app/ for more information";
    }

}
