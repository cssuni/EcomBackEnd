package com.Ecom.app.Ecom.Back.End.dto;

import com.Ecom.app.Ecom.Back.End.model.Category;
import com.Ecom.app.Ecom.Back.End.model.Image;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
//    private List<ImageDto> images;

}
