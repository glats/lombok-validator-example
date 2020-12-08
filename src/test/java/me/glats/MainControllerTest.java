package me.glats;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class MainControllerTest {

    @Inject
    @Client("/main")
    RxHttpClient client;

    @Test
    public void testUser() throws Exception {
        HttpRequest<?> request = HttpRequest.POST("/user", this.getUser()).contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);
        assertEquals(HttpStatus.OK, response.status());
        assertNotNull(response.getBody());

    }

    private MultipartBody getUser() {

        return MultipartBody.builder()
                .addPart("name", "jhon")
                .addPart("age", "12")
                .build();
    }

    @Test
    public void testPet() throws Exception {

        HttpRequest<?> request = HttpRequest.POST("/pet", this.getPet()).contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> client.toBlocking().exchange(request, String.class));
        assertEquals(400, e.getStatus().getCode());

    }

    private MultipartBody getPet() {

        return MultipartBody.builder()
                .addPart("name", "pikachu")
                .addPart("age", "12")
                .build();
    }
}
