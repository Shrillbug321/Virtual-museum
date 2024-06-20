package pl.dreszer.projekt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor

@Embeddable
public class Dimension {
    private float width;
    private float height;

    public Dimension(Float width, Float height) {
        this.width = width;
        this.height = height;
    }
}