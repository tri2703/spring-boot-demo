package ute.tri.controllers.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/profile")
    public String profile(Model model) {
        // Create a list of personal information
        List<Info> profile = new ArrayList<>();
        profile.add(new Info("Full Name", "Minh Trí"));
        profile.add(new Info("Nickname", "MinhTri"));
        profile.add(new Info("Email", "tri2703@gmail.com"));
        profile.add(new Info("Website", "https://iotstar.vn"));

        // Add the list to the model
        model.addAttribute("profile", profile);
        return "profile"; // Returns the template profile.html
    }
}

// Info class for holding profile information
class Info {
    private String key;
    private String value;

    public Info(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
