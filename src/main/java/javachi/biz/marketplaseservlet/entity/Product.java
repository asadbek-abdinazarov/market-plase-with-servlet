package javachi.biz.marketplaseservlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product extends BaseEntity {
    private String prodName;
    private String prodType;
    private String prodDesc;
    private String unit;
    private Integer amount;
    private Double prodPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Basket basket;
    @Builder(builderMethodName = "childBuilder")
    public Product(String id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String prodName, String prodType, String prodDesc, String unit, Integer amount, Double prodPrice, Basket basket) {
        super(id, createdAt, updatedAt, deletedAt);
        this.prodName = prodName;
        this.prodType = prodType;
        this.prodDesc = prodDesc;
        this.unit = unit;
        this.amount = amount;
        this.prodPrice = prodPrice;
        this.basket = basket;
    }
}
