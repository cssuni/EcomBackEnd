package com.Ecom.app.Ecom.Back.End.dto;

import lombok.Data;

// we dont want return everything of an image.
// so, we created a wrapper class that contains necessary information which
// need to be sent to front end/ to the user.
@Data
public class ImageDto {

    private Long id;
    private String name;
    private String type;
    private String downloadUrl;
}
