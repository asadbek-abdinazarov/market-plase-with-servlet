package javachi.biz.marketplaseservlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Basket extends BaseEntity{

    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    @Builder(builderMethodName = "childBuilder")
    public Basket(String id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String userId, List<Product> products) {
        super(id, createdAt, updatedAt, deletedAt);
        this.userId = userId;
        this.products = products;
    }
}
