package me.glats;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Introspected
public class User {
    @NotBlank
    private String name;
    @Min(18)
    private Integer age;
}
