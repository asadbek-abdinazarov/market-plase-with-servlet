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
public class Basket extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser authUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "basket")
    private List<Product> products;

    @Builder(builderMethodName = "childBuilder")
    public Basket(String id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, AuthUser authUser, List<Product> products) {
        super(id, createdAt, updatedAt, deletedAt);
        this.authUser = authUser;
        this.products = products;
    }
}
