package com.Ecom.app.Ecom.Back.End.request;

import com.Ecom.app.Ecom.Back.End.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddImage {

    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    private int product;

}
