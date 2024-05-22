package com.example.transport.entities.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        indexes = {
                @Index(
                        name = "heading_idx",
                        columnList = "heading"
                )
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String uniqueid;
    private String timestamp;
    private int bus_id;
    private int heading;
    private int speed;
    private double lon;
    private double lat;
    private int direction;
    private String gosnum;
    private String bortnum;
    private int probeg;
}
