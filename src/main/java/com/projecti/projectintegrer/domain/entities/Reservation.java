package com.projecti.projectintegrer.domain.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Reservation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reservation {
    @Id
    @SequenceGenerator(
        name = "Reservation_id_sequence",
        sequenceName = "Reservation_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "Reservation_id_sequence"
    )
    private Integer id;
    private Integer quantityPeople;
    private LocalDate checkIn;
    private LocalDate checkOut;

    @Enumerated(EnumType.STRING)
    private StatusEnum state;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @Override
    public final boolean equals (Object o){
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Reservation reservation = (Reservation) o;
        return getId() != null && Objects.equals(getId(), reservation.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
