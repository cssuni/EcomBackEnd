package com.Ecom.app.Ecom.Back.End.service.image;


import com.Ecom.app.Ecom.Back.End.model.Image;
import com.Ecom.app.Ecom.Back.End.model.Product;
import com.Ecom.app.Ecom.Back.End.repository.ImageRepository;
import com.Ecom.app.Ecom.Back.End.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    public void save(MultipartFile file, Long id)  throws IOException {

        Product product = productService.getProductById(id);

        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        image.setType(file.getContentType());
        image.setProduct(product);

        imageRepository.save(image);
    }

    public Image findById(int id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
