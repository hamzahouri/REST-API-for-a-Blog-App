package com.hamza.blog.payload;

import com.hamza.blog.entities.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto model info"
)
public class PostDto {

    private Long id;
    @Schema(description = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 character")
    private String title;
    @NotEmpty
    @Size(min=10,message = "post description should have at least 10 character")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    private Long categoryId;
}
