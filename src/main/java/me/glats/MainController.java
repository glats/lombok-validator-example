package me.glats;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;

@Controller("/main")
public class MainController {

    @Post(uri = "/user", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
    public String user(@Body @Valid User user) {
        return String.format("%s %s", user.getName(), user.getAge());
    }

    @Post(uri = "/pet", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
    public String pet(@Body @Valid Pet pet) {
        return String.format("%s %s", pet.getName(), pet.getAge());
    }
}