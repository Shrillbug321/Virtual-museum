package pl.dreszer.projekt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "museums")
public class Museum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int museumId;
    private String name;
    private String place;

    public Museum(int museumId, String name, String place) {
        this.museumId = museumId;
        this.name = name;
        this.place = place;
    }
}
