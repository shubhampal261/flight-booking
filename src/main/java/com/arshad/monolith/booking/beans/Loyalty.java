package com.arshad.monolith.booking.beans;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loyalty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User userInfo;

    private double points;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
}
