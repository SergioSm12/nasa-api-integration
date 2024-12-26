package com.sergio.nasa_api.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserInteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String url;

    private String httpMethod;

    private String email;

    private LocalDateTime timestamp;

    private String remoteAddress;

}
