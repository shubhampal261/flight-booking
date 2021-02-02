package com.arshad.monolith.booking.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int flight;

    @JoinColumn(name="flight", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Flight.class, fetch = FetchType.LAZY)
    private Flight flightInfo;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User userInfo;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int numberOfSeats;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
}
