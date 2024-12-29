package com.Ecom.app.Ecom.Back.End.controller;


import com.Ecom.app.Ecom.Back.End.model.Image;
import com.Ecom.app.Ecom.Back.End.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.Ecom.app.Ecom.Back.End.responce.ApiResponse;


import java.io.IOException;

@RestController
@RequestMapping("${api.prefix}/Images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/addImage")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam MultipartFile file, @RequestParam Long productId) throws IOException {
        try{
            imageService.save(file, productId);
            return ResponseEntity.ok(new ApiResponse("Image Saved Successfully", null));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getImageById(@PathVariable int id) {
        Image image = imageService.findById(id);
        Resource imageResource = new ByteArrayResource(image.getData());
        String type = image.getType();

        HttpHeaders headers = new HttpHeaders();
        // Setting the Type in the header
        headers.setContentType(MediaType.parseMediaType(type)); // Change to the appropriate image type if needed
       // returning the type and the image data
        return ResponseEntity.ok() .headers(headers) .body(imageResource);
    }



}