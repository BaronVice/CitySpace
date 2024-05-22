package com.example.transport.entities.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(
        indexes = {
                @Index(
                        name = "route_name_idx",
                        columnList = "name"
                )
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = REFRESH)
    @JoinTable(
            name = "route_stop",
            joinColumns = @JoinColumn(
                    name = "route_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "FK_ROUTE_STOP_ID",
                            foreignKeyDefinition = "foreign key (route_id) references route(id) on delete cascade"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "stop_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "FK_STOP_ROUTE_ID",
                            foreignKeyDefinition = "foreign key (stop_id) references stop(id) on delete cascade"
                    )
            ),
            indexes = {
                    @Index(name = "route_id_idx", columnList = "route_id"),
                    @Index(name = "stop_id_idx", columnList = "stop_id")
            }
    )
    private List<Stop> stops;
}
