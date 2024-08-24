package br.com.devsenior.dscommerce.dto;

import br.com.devsenior.dscommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;


    public ProductMinDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

}
