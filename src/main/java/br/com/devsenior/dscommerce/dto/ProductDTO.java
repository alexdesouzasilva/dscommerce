package br.com.devsenior.dscommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.devsenior.dscommerce.entities.Category;
import br.com.devsenior.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    

    /*
     * O Validation deverá ser adicionado nas entidades DTO,
     * pois são essas classes que farão a serialização do Json,
     * ou seja, são elas que recebem as informações preenchidas pelo usuário 
     * e que são enviadas no corpo da requisição.
     */

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    @NotNull(message = "Campo requerido")
    @Positive(message = "O preço deve ser positivo")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve haver no mínimo uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        for (Category c : product.getCategories()) {
            categories.add(new CategoryDTO(c));
        }
    }

    

}
