package com.example.transport.entities.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        indexes = {
            @Index(
                    name = "stop_name_idx",
                    columnList = "name"
            )
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String lat;
    private String lon;
    @ManyToMany(mappedBy = "stops", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Route> routes;
}
