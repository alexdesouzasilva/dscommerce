package br.com.devsenior.dscommerce.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT") // Definindo campo de texto (TEXTO LONGO), para ser criado como um Blob, por exemplo
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category", // Nome da tabela criada para o relacionamento Product vs Category
        joinColumns = @JoinColumn(name = "product_id"), // id da tabela/classe em questão 
        inverseJoinColumns = @JoinColumn(name = "category_id")) // id da tabela/classe que fará o relacionamento.
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product") // // items -> id -> product
    private Set<OrderItem> items = new HashSet<>();

    public List<Order> getOrders() {
        return items.stream().map(x -> x.getOrder()).toList();
    }

}
