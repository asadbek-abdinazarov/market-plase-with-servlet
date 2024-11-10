package javachi.biz.marketplaseservlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth_user")
public class AuthUser extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "authUser")
    private Basket basket;

    public enum Status{
        ACTIVE,
        INACTIVE,
        BLOCKED,
    }
    @Builder(builderMethodName = "childBuilder")
    public AuthUser(String id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String firstName, String lastName, String email, String password, String role, Status status) {
        super(id, createdAt, updatedAt, deletedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
