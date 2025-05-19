package com.project.logibase.logibase.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_details")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class UserDetail {
    @Id
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
