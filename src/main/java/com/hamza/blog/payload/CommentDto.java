package com.hamza.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "comment should not be empty")
    @Size(min = 10)
    private String body;

}
