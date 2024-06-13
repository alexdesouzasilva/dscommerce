package br.com.devsenior.dscommerce.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    // Caso não seja passado o fuso horário, será usado como padrão GMT de Londres
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;

    //Muitos pedidos para um cliente
    @ManyToOne // irá incluir na tabela tb_order o campo client_id
    @JoinColumn(name = "client_id") // relacionamento com a tabela User, client_id será o ID.
    private User client;

    //Mapeado por order, atribuot presente classe que está sendo feito o relacionamento Payment
    //cascade: como o campos filho se comportará caso o campo pai for excluído.
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order") // items -> id -> order
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }


    public Order(Long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    // Acessando produtos através do pedido

    public List<Product> getProducts() {
        return items.stream().map(x -> x.geProduct()).toList();
    }
    
}
