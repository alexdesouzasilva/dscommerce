package br.com.devsenior.dscommerce.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrdemItemPK {

    /*
     * Classe criada com o objetivo de realizar o relacionamento muitos para muitos 
     * entre Order e Product. Esta classe será utilizada pela Classe OrderItem, que representa 
     * o relacionamento entre Order e Product, contendo outros atributos.
     */


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
}
