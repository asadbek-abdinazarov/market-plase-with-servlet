package javachi.biz.marketplaseservlet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity {
    private String prodName;
    private String prodType;
    private String prodDesc;
    private String unit;
    private Integer amount;
    private Double prodPrice;
}
