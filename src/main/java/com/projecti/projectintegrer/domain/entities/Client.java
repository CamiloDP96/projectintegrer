package com.projecti.projectintegrer.domain.entities;

import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {

    @Id
    @SequenceGenerator(
        name = "client_id_sequence",
        sequenceName = "client_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "client_id_sequence"
    )
    private Integer id;
    private String name;
    private String passwordHash;
    private List<UserRoleEnum> roles;


    public Client(String name, String passwordHash){
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public final boolean equals (Object o){
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public void addRole(UserRoleEnum role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
}
