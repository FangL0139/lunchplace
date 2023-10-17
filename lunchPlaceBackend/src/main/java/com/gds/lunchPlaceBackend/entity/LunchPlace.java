package com.gds.lunchPlaceBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lunchplace")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placeName;
    private String postcode;

}
