package javachi.biz.marketplaseservlet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "verify_email")
public class VerifyEmailEntity extends BaseEntity {
    private String email;
    private String verifyCode;

    @Builder(builderMethodName = "childBuilder")
    public VerifyEmailEntity(String email, String verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }
}
