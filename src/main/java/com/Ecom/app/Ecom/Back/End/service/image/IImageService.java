package com.Ecom.app.Ecom.Back.End.service.image;
import com.Ecom.app.Ecom.Back.End.dto.ImageDto;
import com.Ecom.app.Ecom.Back.End.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {


    void save(List<MultipartFile> files, Long productId) throws IOException;


    Image findById(int id);


    void updateImage(MultipartFile file, int id) throws IOException;



    void deleteImage(int id);
}
