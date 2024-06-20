package pl.dreszer.projekt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "guiders")
public class Guider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guiderId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public Guider(int guiderId, String name, String surname) {
        this.guiderId = guiderId;
        this.name = name;
        this.surname = surname;
    }
}
